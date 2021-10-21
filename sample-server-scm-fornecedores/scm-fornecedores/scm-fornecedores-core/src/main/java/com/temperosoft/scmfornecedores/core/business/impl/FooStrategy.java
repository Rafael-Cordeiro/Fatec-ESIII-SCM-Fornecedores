package com.temperosoft.scmfornecedores.core.business.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.dao.impl.FooDao;
import com.temperosoft.scmfornecedores.domain.Pais;

@Component
public class FooStrategy implements IStrategy<Pais> {
	
	private Logger logger = LoggerFactory.getLogger(FooDao.class);
	
	@Autowired
	private FooDao fooDao;

	@Override
	public void process(Pais aEntity, INavigationCase<Pais> aCase) {
		
		try {
			List<Pais> cursos = fooDao.test();
			aCase.getResult().addEntity(cursos);
			
			logger.info("Listagem de cursos realizada");
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
	}

}