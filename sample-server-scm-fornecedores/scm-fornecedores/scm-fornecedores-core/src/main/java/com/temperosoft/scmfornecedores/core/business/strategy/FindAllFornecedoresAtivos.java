package com.temperosoft.scmfornecedores.core.business.strategy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.FornecedorDAO;
import com.temperosoft.scmfornecedores.domain.Fornecedor;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

@Component
public class FindAllFornecedoresAtivos implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(FindAllFornecedoresAtivos.class);
	
	@Autowired
	private FornecedorDAO fornecedorDAO;

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			
			TipoCadastro tipoCadastro = new TipoCadastro();
			
			tipoCadastro.setDescricao(TipoCadastroEnum.ATIVO.getLiteral());
			
			List<Fornecedor> fornecedores = fornecedorDAO.findByTipoCadastro(tipoCadastro);
			
			aCase.getResult().addEntity(fornecedores);
			
			logger.info("{} fornecedores ativos consultados", fornecedores.size());
		} catch (Exception e) {
			logger.error("Erro ao consultar fornecedores ativos", e);
		}
		
	}

}
