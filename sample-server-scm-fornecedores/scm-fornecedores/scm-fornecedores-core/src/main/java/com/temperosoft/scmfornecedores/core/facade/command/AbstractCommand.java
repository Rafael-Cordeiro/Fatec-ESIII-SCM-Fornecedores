package com.temperosoft.scmfornecedores.core.facade.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.core.facade.Facade;

@Component
public abstract class AbstractCommand implements ICommand {
	
	@Autowired
	public Facade facade;

}
