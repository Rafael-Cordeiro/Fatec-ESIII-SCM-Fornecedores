package com.temperosoft.scmfornecedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.temperosoft.scmfornecedores.core.facade.Facade;
import com.temperosoft.scmfornecedores.domain.Pais;

@RestController
@RequestMapping("pais")
@CrossOrigin(origins="*")
public class PaisController {
	
	@Autowired
	private Facade facade;

	@GetMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<List<Pais>> getPaises() {
		
		Pais c = new Pais();
		
		List<Pais> cs = facade.findAll(c, "CONSULTAR_PAISES");
		
		if (cs.isEmpty())
			return ResponseEntity.unprocessableEntity().build();
		return ResponseEntity.ok(cs);
	}
	
}
