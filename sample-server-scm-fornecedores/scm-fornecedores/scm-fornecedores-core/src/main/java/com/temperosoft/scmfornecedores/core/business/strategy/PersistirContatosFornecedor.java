package com.temperosoft.scmfornecedores.core.business.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.ContatoDAO;
import com.temperosoft.scmfornecedores.domain.Contato;
import com.temperosoft.scmfornecedores.domain.Fornecedor;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

@Component
public class PersistirContatosFornecedor implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(PersistirContatosFornecedor.class);
	
	@Autowired
	private ContatoDAO contatoDAO;

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			int rows = 0;
			
			if (aEntity.getContatos().size() > 0) {
				for (Contato contato : aEntity.getContatos()) {
					if (contato.getId() == null) {
						contato.setTipoCadastro(new TipoCadastro());
						contato.getTipoCadastro().setDescricao(TipoCadastroEnum.ATIVO.getLiteral());
						contato.setId(contatoDAO.create(contato, aEntity.getId()));						
					} else
						contatoDAO.update(contato);
					rows++;
				}				
				logger.info(rows + " contatos relacionados ao fornecedor " + aEntity.getCodigo() + " persistidos com sucesso.");
			}
			
		} catch (Exception e) {
			logger.error("Erro ao cadastrar contatos relacionados ao fornecedor " + aEntity.getCodigo(), e);
		}
		
	}

}
