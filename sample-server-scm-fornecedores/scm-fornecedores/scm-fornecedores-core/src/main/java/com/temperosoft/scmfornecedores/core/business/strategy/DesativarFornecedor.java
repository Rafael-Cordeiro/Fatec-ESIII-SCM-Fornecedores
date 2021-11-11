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
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;

@Component
public class DesativarFornecedor implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(DesativarFornecedor.class);
	
	@Autowired
	private FornecedorDAO fornecedorDAO;
	
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			Long row = fornecedorDAO.delete(TipoCadastroEnum.INATIVO.getLiteral(), aEntity.getId());
			logger.info("{} fornecedor(es) desativado(s)", row);
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Erro ao desativar fornecedor", DesativarFornecedor.class, aCase, e);
		}
	}

}
