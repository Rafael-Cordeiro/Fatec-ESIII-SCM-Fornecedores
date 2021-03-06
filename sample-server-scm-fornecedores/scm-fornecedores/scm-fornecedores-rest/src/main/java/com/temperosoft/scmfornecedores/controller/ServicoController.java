package com.temperosoft.scmfornecedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.temperosoft.scmfornecedores.core.exception.InvalidStrategyConditionException;
import com.temperosoft.scmfornecedores.core.facade.command.CommandContext;
import com.temperosoft.scmfornecedores.domain.Servico;

@RestController
@RequestMapping("servico")
@CrossOrigin(origins="*")
public class ServicoController {

	@Autowired
	private CommandContext cmdCtx;
	
	@GetMapping(name="", produces="application/json")
	public @ResponseBody ResponseEntity<List<Servico>> findAllServico() {
		
		Servico s = new Servico();
		
		@SuppressWarnings("unchecked")
		List<Servico> ss = (List<Servico>) cmdCtx.getCommand("FIND_ALL").execute(s, "CONSULTAR_SERVICOS");
		
		if (ss.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(ss);
		
	}
	
	@PostMapping(name="", produces="application/json")
	public @ResponseBody ResponseEntity<String> createServico(@RequestBody Servico servico) {
		
		try {
			cmdCtx.getCommand("PERSISTS_ENTITY").execute(servico, "PERSISTIR_SERVICO_SALVAR");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<String> updateServico(@PathVariable("id") Long id, @RequestBody Servico servico) {
		
		servico.setId(id);
		
		try {
			cmdCtx.getCommand("PERSISTS_ENTITY").execute(servico, "PERSISTIR_SERVICO_ATUALIZAR");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<String> inactivateServico(@PathVariable("id") Long id) {
		
		Servico s = new Servico();
		s.setId(id);
		
		try {
			cmdCtx.getCommand("PERSISTS_ENTITY").execute(s, "EXCLUIR_SERVICO");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}

		return ResponseEntity.ok().build();
		
	}
	
}
