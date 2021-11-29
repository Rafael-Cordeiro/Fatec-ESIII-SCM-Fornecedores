package com.temperosoft.scmfornecedores.core.business.strategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@Component
public class GerarCodigoFornecedor implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(GerarCodigoFornecedor.class);

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			StringBuilder code  = new StringBuilder();
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
			LocalDateTime.now().format(pattern);
			code.append("FOR-").append(LocalDateTime.now().format(pattern));
			
			aEntity.setCodigo(code.toString());
			
			logger.info("Código de fornecedor gerado com sucesso (" + aEntity.getCodigo() + ").");
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Ocorreu um erro ao gerar log para fornecedor", GerarCodigoFornecedor.class, aCase, e);
		}
		
	}

}
