package com.temperosoft.scmfornecedores.core.business.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.FornecedorDAO;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@Component
public class FindFornecedorById implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(FindFornecedorById.class);
	
	@Autowired
	private FornecedorDAO fornecedorDAO;
	
	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			Long id = aEntity.getId();
			
			aEntity = fornecedorDAO.findById(id);
			aCase.getResult().addEntity(aEntity);
			
			logger.info("Consulta de fornecedor de código " + aEntity.getCodigo() + " realizada com sucesso");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
