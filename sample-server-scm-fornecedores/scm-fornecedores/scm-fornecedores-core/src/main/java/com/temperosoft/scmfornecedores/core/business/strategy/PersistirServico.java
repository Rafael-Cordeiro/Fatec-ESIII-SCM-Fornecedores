package com.temperosoft.scmfornecedores.core.business.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.ServicoDAO;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.Servico;

@Component
public class PersistirServico implements IStrategy<Servico> {
	
	private Logger logger = LoggerFactory.getLogger(PersistirServico.class);
	
	@Autowired
	private ServicoDAO servicoDAO;

	@Override
	public void process(Servico aEntity, INavigationCase<Servico> aCase) {
		
		try {
			Long row = 1l;
			
			if (aEntity.getId() == null)
				aEntity.setId(servicoDAO.create(aEntity));
			else
				row = servicoDAO.update(aEntity);
			
			logger.info("{} servicos persistidos com sucesso.",row);
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Erro ao persistir servico", PersistirServico.class, aCase, e);			
		}
		
	}

}
