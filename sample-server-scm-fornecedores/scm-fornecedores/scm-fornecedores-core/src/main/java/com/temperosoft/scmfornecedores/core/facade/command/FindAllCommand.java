package com.temperosoft.scmfornecedores.core.facade.command;

import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.AbstractDomain;

@Component
public class FindAllCommand extends AbstractCommand {

	@Override
	public Object execute(AbstractDomain aEntity, String businessCaseName) {
		return facade.findAll(aEntity, businessCaseName);
	}
	
}
