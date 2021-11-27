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

@Component
public class FindProdutos implements IStrategy<Produto> {
	
	private Logger logger = LoggerFactory.getLogger(FindProdutos.class);
	
	@Autowired
	private ProdutoDAO fornecivelDAO;

	@Override
	public void process(Produto aEntity, INavigationCase<Produto> aCase) {
		
		try {
			List<Produto> produtos = fornecivelDAO.findAll();
			aCase.getResult().addEntity(produtos);
			
			logger.info("Listagem de produtos realizada");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

}
