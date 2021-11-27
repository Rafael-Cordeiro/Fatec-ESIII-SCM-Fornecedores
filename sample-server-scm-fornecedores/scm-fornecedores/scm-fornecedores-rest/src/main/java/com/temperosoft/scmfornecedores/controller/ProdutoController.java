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

import com.temperosoft.scmfornecedores.core.facade.Facade;
import com.temperosoft.scmfornecedores.domain.Produto;

@RestController
@RequestMapping("produto")
@CrossOrigin(origins="*")
public class ProdutoController {

	@Autowired
	public Facade facade;
	
	@GetMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<List<Produto>> findAllProdutos() {
		List<Produto> ps;
		
		Produto p = new Produto();
		ps = facade.findAll(p, "CONSULTAR_PRODUTOS");
		
		if (ps.isEmpty())
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		return ResponseEntity.ok(ps);
	}
	
}
