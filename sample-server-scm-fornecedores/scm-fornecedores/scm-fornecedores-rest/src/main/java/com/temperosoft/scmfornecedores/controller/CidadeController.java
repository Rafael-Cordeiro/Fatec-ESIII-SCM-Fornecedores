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

import com.temperosoft.scmfornecedores.core.exception.InvalidStrategyConditionException;
import com.temperosoft.scmfornecedores.core.facade.Facade;
import com.temperosoft.scmfornecedores.domain.Cidade;
import com.temperosoft.scmfornecedores.domain.Estado;

@RestController
@RequestMapping("cidade")
@CrossOrigin(origins="*")
public class CidadeController {
	
	@Autowired
	private Facade facade;
	
	@GetMapping(value="/find-cidade-by-codigo", produces="application/json")
	public @ResponseBody ResponseEntity<Cidade> findCidade(@RequestParam("codigo") String codigo) {
		
		Cidade c = new Cidade();
		c.setCodigo(codigo);
		
		try {
			c = facade.findById(c, "CONSULTAR_CIDADE_POR_CODIGO");			
		} catch (InvalidStrategyConditionException e) {
			ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}

		return ResponseEntity.ok(c);
	}
	
	@GetMapping(value="/find-cidade-by-id", produces="application/json")
	public @ResponseBody ResponseEntity<Cidade> findCidadeById(@RequestParam("id") Long id) {
		
		Cidade c = new Cidade();
		c.setId(id);
		
		try {
			c = facade.findById(c, "CONSULTAR_CIDADE_POR_ID");			
		} catch (InvalidStrategyConditionException e) {
			ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}

		return ResponseEntity.ok(c);
	}
	
	@GetMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<List<Cidade>> findAllCidades() {
		
		Cidade c = new Cidade();
		
		List<Cidade> cs = facade.findAll(c, "CONSULTAR_CIDADES");
		
		if(cs.isEmpty())
			return ResponseEntity.unprocessableEntity().build();
		return ResponseEntity.ok(cs);
	}
	
	@GetMapping(value="/find-cidades-by-estado-id", produces="application/json")
	public @ResponseBody ResponseEntity<List<Cidade>> findCidadesByEstado(@RequestParam(name = "id") Long id) {
		
		Cidade c = new Cidade();
		c.setEstado(new Estado());
		c.getEstado().setId(id);
		
		List<Cidade> cs = facade.findAll(c, "FIND_CIDADES_BY_ESTADO");
		
		if(cs.isEmpty())
			return ResponseEntity.unprocessableEntity().build();
		return ResponseEntity.ok(cs);
		
	}

}
