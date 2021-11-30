package com.temperosoft.scmfornecedores.core.business.strategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.Servico;

@Component
public class GerarCodigoServico implements IStrategy<Servico> {
	
	private Logger logger = LoggerFactory.getLogger(GerarCodigoServico.class);

	@Override
	public void process(Servico aEntity, INavigationCase<Servico> aCase) {
		
		try {
			StringBuilder code  = new StringBuilder();
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
			LocalDateTime.now().format(pattern);
			code.append("SER-").append(LocalDateTime.now().format(pattern));
			
			aEntity.setCodigo(code.toString());
			
			logger.info("Código de fornecedor gerado com sucesso (" + aEntity.getCodigo() + ").");
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Ocorreu um erro ao gerar codigo para servico", GerarCodigoFornecedor.class, aCase, e);
		}
		
	}

}
