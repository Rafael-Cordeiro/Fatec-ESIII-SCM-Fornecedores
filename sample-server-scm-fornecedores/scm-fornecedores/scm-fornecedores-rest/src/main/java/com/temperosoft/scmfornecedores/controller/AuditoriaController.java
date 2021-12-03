package com.temperosoft.scmfornecedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.temperosoft.scmfornecedores.core.facade.command.CommandContext;
import com.temperosoft.scmfornecedores.core.facade.command.ICommand;
import com.temperosoft.scmfornecedores.domain.application.Auditoria;

@RestController
@RequestMapping("auditoria")
@CrossOrigin(origins="*")
public class AuditoriaController {

	@Autowired
	private CommandContext cmdCtx;
	
	@GetMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<List<Auditoria>> findAllAuditoria() {
		
		Auditoria a = new Auditoria();
		
		ICommand cmd = cmdCtx.getCommand("FIND_ALL");
		
		@SuppressWarnings("unchecked")
		List<Auditoria> as = (List<Auditoria>) cmd.execute(a, "CONSULTAR_AUDITORIA");
		
		if (as.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(as);
		
	}
	
}
