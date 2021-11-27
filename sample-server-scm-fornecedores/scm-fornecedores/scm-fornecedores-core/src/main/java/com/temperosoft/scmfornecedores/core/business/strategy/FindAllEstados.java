package com.temperosoft.scmfornecedores.core.business.strategy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.EstadoDAO;
import com.temperosoft.scmfornecedores.domain.Estado;

@Component
public class FindAllEstados implements IStrategy<Estado> {

	private Logger logger = LoggerFactory.getLogger(FindAllEstados.class);
	
	@Autowired
	private EstadoDAO estadoDAO;
	
	public void process(Estado aEntity, INavigationCase<Estado> aCase) {
		
		try {
			List<Estado> estados = estadoDAO.findAll();
			aCase.getResult().addEntity(estados);
			
			logger.info("Consulta de estados realizada com sucesso");			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
