package com.temperosoft.scmfornecedores.core.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.google.common.base.Strings;
import com.temperosoft.scmfornecedores.domain.ScmFornecedores;

@Component
public class ScmFornecedoresStrategy implements IStrategy<ScmFornecedores> {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("genericDAO")
	GenericDAO<ScmFornecedores> dao;
	
	@Override
	public void process(ScmFornecedores aEntity, INavigationCase<ScmFornecedores> aCase) {
		
		if(aEntity == null || Strings.isNullOrEmpty(aEntity.getCode())) {
			aCase.suspendExecution("code is required");
			return;
		}
		if(!dao.find(ScmFornecedores.class, aEntity.getCode()).isEmpty()) {
			aCase.suspendExecution("code: " + aEntity.getCode() + " alredy exists");
			return;
		}
		logger.info("code " + aEntity.getCode() + " is present and not exists");
	}
}
