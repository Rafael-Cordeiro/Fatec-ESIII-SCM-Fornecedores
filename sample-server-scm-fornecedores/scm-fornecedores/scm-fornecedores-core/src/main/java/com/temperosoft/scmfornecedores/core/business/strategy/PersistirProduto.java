package com.temperosoft.scmfornecedores.core.business.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.ProdutoDAO;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.Produto;

@Component
public class PersistirProduto implements IStrategy<Produto> {
	
	private Logger logger = LoggerFactory.getLogger(PersistirProduto.class);

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Override
	public void process(Produto aEntity, INavigationCase<Produto> aCase) {
		
		try {
			Long row = 1l;
			
			if (aEntity.getId() == null)
				aEntity.setId(produtoDAO.create(aEntity));
			else
				row = produtoDAO.update(aEntity);
			
			logger.info("{} produtos persistidos com sucesso.",row);			
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Erro ao persistir produto", PersistirProduto.class, aCase, e);
		}
		
	}

}
