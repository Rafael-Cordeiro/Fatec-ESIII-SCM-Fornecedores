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
public class ExcluirServico implements IStrategy<Servico> {
	
	private Logger logger = LoggerFactory.getLogger(ExcluirServico.class);
	
	@Autowired
	private ServicoDAO servicoDAO;

	@Override
	public void process(Servico aEntity, INavigationCase<Servico> aCase) {
		
		try {
			Long row = servicoDAO.delete("", aEntity.getId());
			logger.info("{} fornecedor(es) desativado(s)", row);
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Erro ao excluir serviço", ExcluirServico.class, aCase, e);
		}
		
	}

}
