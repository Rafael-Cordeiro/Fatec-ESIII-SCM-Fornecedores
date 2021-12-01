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

@Component
public class ValidarCNPJUnico implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(ValidarCNPJUnico.class);
	
	@Autowired
	private DocumentoDAO documentoDAO;

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			Documento documento = new Documento();
			
			for (Documento doc : aEntity.getDocumentos())
				if (doc.getTipoDocumento().getDescricao().equals("cnpj"))
					documento = doc;
			
			if (documentoDAO.verificarExistenciaDocumento("cnpj", documento.getCodigo()))
				aCase.suspendExecution("CNPJ já existe no sistema");
			
			logger.info("CNPJ {} consultado", documento);
		} catch (Exception e) {
			logger.error("Erro ao consultar existência de CNPJ",e);
		}
		
	}

}
