package com.temperosoft.scmfornecedores.core.business.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.FornecedorDAO;
import com.temperosoft.scmfornecedores.domain.Fornecedor;
import com.temperosoft.scmfornecedores.domain.Produto;

@Component
public class PersistirRelacoesFornecedorProduto implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(PersistirRelacoesFornecedorProduto.class);
	
	@Autowired
	private FornecedorDAO fornecedorDAO;

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			
			int rows = 0;
			
			if (aEntity.getProdutos().size() > 0) {
				fornecedorDAO.deleteProdutoRelations(aEntity.getId());
				for (Produto produto : aEntity.getProdutos()) {
					fornecedorDAO.createProdutoRelation(aEntity.getId(), produto.getId());
					rows++;
				}
				logger.info(rows + " produtos foram relacionados ao fornecedor " + aEntity.getCodigo() + " com sucesso");
			}
			
		} catch (Exception e) {
			logger.error("Erro ao relacionar produtos ao fornecedor " + aEntity.getCodigo(), e);
		}
		
	}

}
