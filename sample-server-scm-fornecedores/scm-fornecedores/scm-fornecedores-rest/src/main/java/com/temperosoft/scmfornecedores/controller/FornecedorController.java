package com.temperosoft.scmfornecedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.temperosoft.scmfornecedores.core.facade.Facade;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {
	
	@Autowired
	public Facade facade;
	
	@GetMapping(value="/find-all-fornecedores", produces="application/json")
	public @ResponseBody ResponseEntity<List<Fornecedor>> findAllFornecedores() {
		
		Fornecedor f = new Fornecedor();
		
		List<Fornecedor> fs = facade.findAll(f, "CONSULTAR_FORNECEDORES");
		
		if(fs.isEmpty())
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		return ResponseEntity.ok(fs);
	}

}
