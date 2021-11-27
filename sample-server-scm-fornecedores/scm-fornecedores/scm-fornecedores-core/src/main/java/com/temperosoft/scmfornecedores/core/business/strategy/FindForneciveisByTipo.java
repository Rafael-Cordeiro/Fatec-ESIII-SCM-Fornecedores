package com.temperosoft.scmfornecedores.core.business.strategy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.FornecivelDAO;
import com.temperosoft.scmfornecedores.domain.Fornecivel;

@Component
public class FindForneciveisByTipo implements IStrategy<Fornecivel> {
	
	private Logger logger = LoggerFactory.getLogger(FindForneciveisByTipo.class);
	
	@Autowired
	private FornecivelDAO fornecivelDAO;

	@Override
	public void process(Fornecivel aEntity, INavigationCase<Fornecivel> aCase) {
		
		try {
			List<Fornecivel> forneciveis = fornecivelDAO.findAll(aEntity.getTipoFornecimento());
			aCase.getResult().addEntity(forneciveis);
			
			logger.info("Listagem de fornecíveis do tipo " + aEntity.getTipoFornecimento().getDescricao()  + " realizada");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

}
