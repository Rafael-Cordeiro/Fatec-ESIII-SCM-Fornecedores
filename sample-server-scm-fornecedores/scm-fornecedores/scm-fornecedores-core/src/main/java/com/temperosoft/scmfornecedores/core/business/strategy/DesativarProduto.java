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
public class DesativarProduto implements IStrategy<Produto> {

	private Logger logger = LoggerFactory.getLogger(DesativarProduto.class);
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Override
	public void process(Produto aEntity, INavigationCase<Produto> aCase) {
			
		try {
			produtoDAO.inactivate(aEntity);
			logger.info("Produto {} inativado", aEntity.getCodigo());
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Erro ao desativar produto", DesativarProduto.class, aCase, e);
		}
		
	}

}
