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
public class PersistirFornecedor implements IStrategy<Fornecedor> {
	
    private Logger logger = LoggerFactory.getLogger(PersistirFornecedor.class);
    
    @Autowired
    private FornecedorDAO fornecedorDAO;

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			Long row = 1l;
			
			if(aEntity.getId() == null)
				aEntity.setId(fornecedorDAO.create(aEntity));
			else
				row = fornecedorDAO.update(aEntity);
			
			logger.info("{} fornecedor(es) persistido(s) com sucesso.", row);			
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Erro ao persistir fornecedor", PersistirFornecedor.class, aCase, e);
		}
	}

}
