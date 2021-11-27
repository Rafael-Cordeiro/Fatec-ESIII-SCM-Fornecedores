package com.temperosoft.scmfornecedores.controller;

import java.security.InvalidParameterException;
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
import com.temperosoft.scmfornecedores.domain.Fornecivel;
import com.temperosoft.scmfornecedores.domain.enums.TipoFornecimentoEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoFornecimento;

@RestController
@RequestMapping("fornecivel")
@CrossOrigin(origins="*")
public class FornecivelController {

	@Autowired
	public Facade facade;
	
	@GetMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<List<Fornecivel>> findAllForneciveis(@RequestParam("tipo") String tipoFornecimento) {
		List<Fornecivel> fs;
		
		try {
			Fornecivel f = new Fornecivel();
			TipoFornecimento tf = new TipoFornecimento();
			tf.setDescricao(TipoFornecimentoEnum.atLiteral(tipoFornecimento).getLiteral());
			f.setTipoFornecimento(tf);
			
			fs = facade.findAll(f, "CONSULTAR_FORNECIVEIS_POR_TIPO");
			
			if (fs.isEmpty()) throw new InvalidParameterException();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		return ResponseEntity.ok(fs);
	}
	
}
