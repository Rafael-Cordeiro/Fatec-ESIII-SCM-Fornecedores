package com.temperosoft.scmfornecedores.core.business.strategy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.ServicoDAO;
import com.temperosoft.scmfornecedores.domain.Servico;

@Component
public class FindAllServicos implements IStrategy<Servico> {

	private Logger logger = LoggerFactory.getLogger(FindAllServicos.class);
	
	@Autowired
	private ServicoDAO servicoDAO;
	
	@Override
	public void process(Servico aEntity, INavigationCase<Servico> aCase) {
		
		try {
			List<Servico> servicos = servicoDAO.findAll();
			
			aCase.getResult().addEntity(servicos);			
			logger.info("Listagem de serviços realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Erro ao consultar serviços", e);
		}
		
	}

}
