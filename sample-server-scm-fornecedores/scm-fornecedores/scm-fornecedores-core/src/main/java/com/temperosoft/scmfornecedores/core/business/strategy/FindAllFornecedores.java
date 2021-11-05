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

@Component
public class FindAllFornecedores implements IStrategy<Fornecedor> {

	private Logger logger = LoggerFactory.getLogger(FindAllCidades.class);
	
	@Autowired
	private FornecedorDAO fornecedorDAO;
	
	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			List<Fornecedor> fornecedores = fornecedorDAO.findAll();
			aCase.getResult().addEntity(fornecedores);
			
			logger.info("Listagem de fornecedores realizada");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
