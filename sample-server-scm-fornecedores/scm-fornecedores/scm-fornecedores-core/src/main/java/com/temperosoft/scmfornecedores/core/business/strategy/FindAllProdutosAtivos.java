package com.temperosoft.scmfornecedores.core.business.strategy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.ProdutoDAO;
import com.temperosoft.scmfornecedores.domain.Produto;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

@Component
public class FindAllProdutosAtivos implements IStrategy<Produto> {
	
	private Logger logger = LoggerFactory.getLogger(FindAllProdutosAtivos.class);
	
	@Autowired
	private ProdutoDAO produtoDAO;

	@Override
	public void process(Produto aEntity, INavigationCase<Produto> aCase) {
		
		try {
			
			TipoCadastro tipoCadastro = new TipoCadastro();
			
			tipoCadastro.setDescricao(TipoCadastroEnum.ATIVO.getLiteral());
			
			List<Produto> produtos = produtoDAO.findByTipoCadastro(tipoCadastro);
			
			aCase.getResult().addEntity(produtos);
			
			logger.info("{} produtos ativos consultados", produtos.size());
		} catch (Exception e) {
			logger.error("Erro ao consultar produtos ativos", e);
		}
		
	}

}
