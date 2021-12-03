package com.temperosoft.scmfornecedores.core.business.strategy;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.AuditoriaDAO;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.application.Auditoria;

@Component
public class FindAllAuditoria implements IStrategy<Auditoria> {
	
	@Autowired
	private AuditoriaDAO auditoriaDAO;
	
	private Logger logger = LoggerFactory.getLogger(FindAllAuditoria.class);

	@Override
	public void process(Auditoria aEntity, INavigationCase<Auditoria> aCase) {
		
		try {
			
			List<Auditoria> auditoria = auditoriaDAO.findAll();
			aCase.getResult().addEntity(auditoria);
			
			logger.info("{} registros de auditoria consultados.", auditoria.size());						
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Erro ao consultar auditoria", FindAllAuditoria.class, aCase, e);
		}
		
	}

}
