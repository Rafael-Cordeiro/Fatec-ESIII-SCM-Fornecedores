package com.temperosoft.scmfornecedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.temperosoft.scmfornecedores.core.facade.Facade;
import com.temperosoft.scmfornecedores.domain.Fornecedor;
import com.temperosoft.scmfornecedores.domain.Pais;

@RestController
@RequestMapping("scmfornecedores")
public class ScmFornecedoresController {
	
	@Autowired
	private Facade facade;
	
	@GetMapping(value="/fornecedores-test", produces="application/json")
	public @ResponseBody ResponseEntity<List<Fornecedor>> getFornecedores() {
		
		Fornecedor f = new Fornecedor();
		
		List<Fornecedor> fs = facade.findAll(f, "CONSULTAR_FORNECEDORES");
		
		if(fs.isEmpty())
			return ResponseEntity.unprocessableEntity().build();
		return ResponseEntity.ok(fs);
	}
	
	@GetMapping(value="/foo", produces="application/json")
	public @ResponseBody ResponseEntity<List<Pais>> foo() {
		
		Pais c = new Pais();
		
		List<Pais> cs = facade.findAll(c, "FOO");
		
		if(cs.isEmpty())
			return ResponseEntity.unprocessableEntity().build();
		return ResponseEntity.ok(cs);
	}
	

}
