package com.temperosoft.scmfornecedores.core.facade.command;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;

@Component
@Getter
public class CommandContext {
	
	private static HashMap<String, ICommand> INSTANCE;
	
	@Autowired
	@Getter(value = AccessLevel.NONE)
	private FindAllCommand findAllCommand;
	
	@Autowired
	@Getter(value = AccessLevel.NONE)
	private FindByIdCommand findByIdCommand;
	
	@Autowired
	@Getter(value = AccessLevel.NONE)
	private PersistsEntityCommand persistsEntityCommand;
	
	@Autowired
	@Getter(value = AccessLevel.NONE)
	private InactivateEntityCommand inactivateEntityCommand;
	
	@Autowired
	@Getter(value = AccessLevel.NONE)
	private ActivateEntityCommand activateEntityCommand;
	
	@Autowired
	@Getter(value = AccessLevel.NONE)
	private DeleteEntityCommand deleteEntityCommand;
	
	private synchronized HashMap<String, ICommand> getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HashMap<String, ICommand>();			
			INSTANCE.put("FIND_ALL", findAllCommand);
			INSTANCE.put("FIND_BY_ID", findByIdCommand);
			INSTANCE.put("PERSISTS_ENTITY", persistsEntityCommand);
			INSTANCE.put("INACTIVATE_ENTITY", inactivateEntityCommand);
			INSTANCE.put("ACTIVATE_ENTITY", activateEntityCommand);
		}
		return INSTANCE;
	}
	
	public synchronized ICommand getCommands(String key) {
		Map<String, ICommand> commands = getInstance();
		return commands.get(key);
	}
	
}
