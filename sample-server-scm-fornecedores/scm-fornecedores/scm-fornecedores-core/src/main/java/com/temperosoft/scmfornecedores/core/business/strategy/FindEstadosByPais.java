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
public class FindEstadosByPais implements IStrategy<Estado> {

	private Logger logger = LoggerFactory.getLogger(FindEstadosByPais.class);
	
	@Autowired
	private EstadoDAO estadoDAO;
	
	@Override
	public void process(Estado aEntity, INavigationCase<Estado> aCase) {
		
		try {
			Long paiId = aEntity.getPais().getId();
			
			List<Estado> estados = estadoDAO.findByPais(paiId);
			aCase.getResult().addEntity(estados);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}	
	}
	
}
