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
import com.temperosoft.scmfornecedores.core.facade.command.ICommand;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@RestController
@RequestMapping("fornecedor")
@CrossOrigin(origins="*")
public class FornecedorController {
	
	@Autowired
	private CommandContext cmdCtx;
	
	@GetMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<List<Fornecedor>> findAllFornecedores() {
		
		Fornecedor f = new Fornecedor();
		
		ICommand cmd = cmdCtx.getCommand("FIND_ALL");

		@SuppressWarnings("unchecked")
		List<Fornecedor> fs = (List<Fornecedor>) cmd.execute(f, "CONSULTAR_FORNECEDORES");
		
		if (fs.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(fs);
	}
	
	@GetMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<Fornecedor> findFornecedorById(@PathVariable("id") Long id) {
		
		Fornecedor f = new Fornecedor();
		f.setId(id);
		
		ICommand cmd = cmdCtx.getCommand("FIND_BY_ID");
		
		f = (Fornecedor) cmd.execute(f, "CONSULTAR_FORNECEDOR_POR_ID");
		
		if (f == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(f);
	}
	
	@PostMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<String> createFornecedor(@RequestBody Fornecedor fornecedor) {
		
		ICommand cmd = cmdCtx.getCommand("PERSISTS_ENTITY");
		
		try {
			cmd.execute(fornecedor, "PERSISTIR_FORNECEDOR_SALVAR");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<String> updateFornecedor(@PathVariable("id") Long id, @RequestBody Fornecedor fornecedor) {
		
		fornecedor.setId(id);
		
		ICommand cmd = cmdCtx.getCommand("PERSISTS_ENTITY");
		
		try {
			cmd.execute(fornecedor, "PERSISTIR_FORNECEDOR_ATUALIZAR");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<String> inactivateFornecedor(@PathVariable("id") Long id) {
		
		Fornecedor f = new Fornecedor();
		f.setId(id);
		
		ICommand cmd = cmdCtx.getCommand("INACTIVATE_ENTITY");
		
		try {
			cmd.execute(f, "DESATIVAR_FORNECEDOR");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

		return ResponseEntity.ok().build();
		
	}
	
	@PostMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<String> activateFornecedor(@PathVariable("id") Long id) {
		
		Fornecedor f = new Fornecedor();
		f.setId(id);
		
		ICommand cmd = cmdCtx.getCommand("ACTIVATE_ENTITY");
		
		try {
			cmd.execute(f, "ATIVAR_FORNECEDOR");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value="ativos", produces="application/json")
	public @ResponseBody ResponseEntity<List<Fornecedor>> findAllFornecedoresAtivos() {
		
		Fornecedor f = new Fornecedor();
		
		ICommand cmd = cmdCtx.getCommand("FIND_ALL");

		@SuppressWarnings("unchecked")
		List<Fornecedor> fs = (List<Fornecedor>) cmd.execute(f, "CONSULTAR_FORNECEDORES_ATIVOS");
		
		if (fs.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(fs);
	}

}
