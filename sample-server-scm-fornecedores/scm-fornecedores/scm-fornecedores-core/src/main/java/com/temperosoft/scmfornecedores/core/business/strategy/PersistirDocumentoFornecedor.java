package com.temperosoft.scmfornecedores.core.business.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.DocumentoDAO;
import com.temperosoft.scmfornecedores.domain.Documento;
import com.temperosoft.scmfornecedores.domain.Fornecedor;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

@Component
public class PersistirDocumentoFornecedor implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(PersistirDocumentoFornecedor.class);
	
	@Autowired
	private DocumentoDAO documentoDAO;

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			int rows = 0;
			
			if (aEntity.getDocumentos().size() > 0) {
				for (Documento documento :  aEntity.getDocumentos()) {
					if (documento.getId() == null) {
						documento.setTipoCadastro(new TipoCadastro());
						documento.getTipoCadastro().setDescricao(TipoCadastroEnum.ATIVO.getLiteral());
						documento.setId(documentoDAO.create(documento, aEntity.getId()));
					}
					else
						documentoDAO.update(documento);
					rows++;
				}
				logger.info(rows + " documentos relacionados ao fornecedor " + aEntity.getCodigo() + " persistidos com sucesso.");
			}
			
		} catch (Exception e) {
			logger.error("Ocorreu um erro ao persistir os documentos do fornecedor " + aEntity.getCodigo(), e);
		}
		
	}

}
