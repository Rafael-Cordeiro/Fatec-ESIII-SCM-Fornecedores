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
import com.temperosoft.scmfornecedores.domain.Produto;

@RestController
@RequestMapping("produto")
@CrossOrigin(origins="*")
public class ProdutoController {

	@Autowired
	private CommandContext cmdCtx;
	
	@GetMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<List<Produto>> findAllProdutos() {
		
		Produto p = new Produto();
		
		ICommand cmd = cmdCtx.getCommand("FIND_ALL");
		
		@SuppressWarnings("unchecked")
		List<Produto> ps = (List<Produto>) cmd.execute(p, "CONSULTAR_PRODUTOS");
		
		if (ps.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(ps);
	}
	
	@PostMapping(value="", produces="application/json")
	public @ResponseBody ResponseEntity<String> createProduto(@RequestBody Produto produto) {
		
		ICommand cmd = cmdCtx.getCommand("PERSISTS_ENTITY");
		
		try {
			cmd.execute(produto, "PERSISTIR_PRODUTOS_SALVAR");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<String> updateProduto(@PathVariable("id") Long id, @RequestBody Produto produto) {
		
		produto.setId(id);
		
		ICommand cmd = cmdCtx.getCommand("PERSISTS_ENTITY");
		
		try {
			cmd.execute(produto, "PERSISTIR_PRODUTO_ATUALIZAR");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
		return ResponseEntity.ok().build();		
	}
	
	@DeleteMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<String> inactivateProduto(@PathVariable("id") Long id) {
		
		Produto p = new Produto();
		p.setId(id);

		ICommand cmd = cmdCtx.getCommand("INACTIVATE_ENTITY");
		
		try {
			cmd.execute(p, "DESATIVAR_PRODUTO");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<String> activateProduto(@PathVariable("id") Long id) {
		
		Produto p = new Produto();
		p.setId(id);

		ICommand cmd = cmdCtx.getCommand("ACTIVATE_ENTITY");
		
		try {
			cmd.execute(p, "ATIVAR_PRODUTO");
		} catch (InvalidStrategyConditionException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value="ativos", produces="application/json")
	public @ResponseBody ResponseEntity<List<Produto>> findAllProdutosAtivos() {
		
		Produto p = new Produto();
		
		ICommand cmd = cmdCtx.getCommand("FIND_ALL");
		
		@SuppressWarnings("unchecked")
		List<Produto> ps = (List<Produto>) cmd.execute(p, "CONSULTAR_PRODUTOS_ATIVOS");
		
		if (ps.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(ps);
	}
	
}
