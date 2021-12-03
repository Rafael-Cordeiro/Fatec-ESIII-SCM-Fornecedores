package com.temperosoft.scmfornecedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.temperosoft.scmfornecedores.core.facade.Facade;
import com.temperosoft.scmfornecedores.domain.Estado;
import com.temperosoft.scmfornecedores.domain.Pais;

@RestController
@RequestMapping("estado")
@CrossOrigin(origins="*")
public class EstadoController {

	@Autowired
	private Facade facade;
	
	@GetMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<List<Estado>> findAllEstados() {
		
		Estado e = new Estado();
		
		List<Estado> es = facade.findAll(e, "CONSULTAR_ESTADOS");
		
		if (es.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(es);
	}
	
	@GetMapping(value="/find-estados-by-pais-id", produces="application/json")
	public @ResponseBody ResponseEntity<List<Estado>> findEstadosByPais(@RequestParam(name="id") Long id) {
		
		Estado e = new Estado();
		e.setPais(new Pais());
		e.getPais().setId(id);
		
		List<Estado> es = facade.findAll(e, "CONSULTAR_ESTADOS_POR_PAIS");
		
		if (es.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(es);
	}
	
}
