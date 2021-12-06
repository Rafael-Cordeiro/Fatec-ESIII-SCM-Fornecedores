package com.temperosoft.scmfornecedores.core.business.strategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.Produto;

@Component
public class GerarCodigoProduto implements IStrategy<Produto> {

	private Logger logger = LoggerFactory.getLogger(GerarCodigoProduto.class);
	
	@Override
	public void process(Produto aEntity, INavigationCase<Produto> aCase) {
		
		try {
			StringBuilder code = new StringBuilder();
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
			code.append("PRO-").append(LocalDateTime.now().format(pattern));
			
			aEntity.setCodigo(code.toString());
			
			logger.info("Código de produto gerado com sucesso ({}).", aEntity.getCodigo());
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Ocorreu um erro ao gerar código para fornecedor", GerarCodigoProduto.class, aCase, e);
		}
		
	}

}
