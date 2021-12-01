package com.temperosoft.scmfornecedores.core.facade.command;

import com.temperosoft.scmfornecedores.domain.AbstractDomain;

public interface ICommand {
	public Object execute(AbstractDomain aEntity, String businessCaseName);
}
