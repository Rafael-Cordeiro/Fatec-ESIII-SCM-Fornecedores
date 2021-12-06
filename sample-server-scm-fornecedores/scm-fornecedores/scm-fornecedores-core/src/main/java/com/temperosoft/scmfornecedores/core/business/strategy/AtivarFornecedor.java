package com.temperosoft.scmfornecedores.core.business.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.FornecedorDAO;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@Component
public class AtivarFornecedor implements IStrategy<Fornecedor> {

	private Logger logger = LoggerFactory.getLogger(AtivarFornecedor.class);
	
	@Autowired
	private FornecedorDAO fornecedorDAO;
	
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			fornecedorDAO.activate(aEntity);
			logger.info("Fornecedor {} ativado", aEntity.getCodigo());
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Erro ao ativar fornecedor", AtivarFornecedor.class, aCase, e);
		}
	}

}
