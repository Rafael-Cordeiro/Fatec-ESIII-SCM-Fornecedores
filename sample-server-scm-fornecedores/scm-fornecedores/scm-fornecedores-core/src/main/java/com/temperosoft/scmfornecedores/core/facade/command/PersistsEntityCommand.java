package com.temperosoft.scmfornecedores.core.facade.command;

import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.AbstractDomain;

@Component
public class PersistsEntityCommand extends AbstractCommand {

	@Override
	public Object execute(AbstractDomain aEntity, String businessCaseName) {
		facade.persistsEntity(aEntity, businessCaseName);
		return null;
	}

}