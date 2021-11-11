package com.temperosoft.scmfornecedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.temperosoft.scmfornecedores.core.exception.InvalidStrategyConditionException;
import com.temperosoft.scmfornecedores.core.facade.Facade;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {
	
	@Autowired
	public Facade facade;
	
	@GetMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<List<Fornecedor>> findAllFornecedores() {
		
		Fornecedor f = new Fornecedor();
		
		List<Fornecedor> fs = facade.findAll(f, "CONSULTAR_FORNECEDORES");
		
		if (fs.isEmpty())
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		return ResponseEntity.ok(fs);
	}
	
	@GetMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<Fornecedor> findFornecedorById(@PathVariable("id") Long id) {
		
		Fornecedor f = new Fornecedor();
		f.setId(id);
		
		f = facade.findById(f, "CONSULTAR_FORNECEDOR_POR_ID");
		
		if (f == null)
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		return ResponseEntity.ok(f);
	}
	
	@PostMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<String> createFornecedor(@RequestBody Fornecedor fornecedor) {
		
		try {
			facade.persistsEntity(fornecedor, "PERSISTE_FORNECEDOR");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<String> updateFornecedor(@PathVariable("id") Long id, @RequestBody Fornecedor fornecedor) {
		
		fornecedor.setId(id);
		
		try {
			facade.persistsEntity(fornecedor, "PERSISTE_FORNECEDOR");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

}
