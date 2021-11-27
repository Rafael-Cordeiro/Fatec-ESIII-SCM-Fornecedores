package com.temperosoft.scmfornecedores.core.business.strategy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.PaisDAO;
import com.temperosoft.scmfornecedores.domain.Pais;

@Component
public class FindAllPaises implements IStrategy<Pais> {

	private Logger logger = LoggerFactory.getLogger(FindAllPaises.class);
	
	@Autowired
	private PaisDAO paisDAO;
	
	@Override
	public void process(Pais aEntity, INavigationCase<Pais> aCase) {
		
		try {
			List<Pais> paises = paisDAO.findAll();
			
			aCase.getResult().addEntity(paises);
			logger.info("Consulta de países realizada com sucesso");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}
