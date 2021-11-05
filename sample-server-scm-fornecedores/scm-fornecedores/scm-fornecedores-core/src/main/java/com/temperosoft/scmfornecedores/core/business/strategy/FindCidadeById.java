package com.temperosoft.scmfornecedores.core.business.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.CidadeDAO;
import com.temperosoft.scmfornecedores.domain.Cidade;

@Component
public class FindCidadeById implements IStrategy<Cidade> {
	
	private Logger logger = LoggerFactory.getLogger(FindCidadeByCodigo.class);
	
	@Autowired
	private CidadeDAO cidadeDAO;

	@Override
	public void process(Cidade aEntity, INavigationCase<Cidade> aCase) {
		
		try {
			Long id = aEntity.getId();
			
			Cidade cidade = cidadeDAO.findById(id);
			aCase.getResult().addEntity(cidade);
			
			logger.info("Consulta da cidade de id " + id + " realizada com sucesso");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
