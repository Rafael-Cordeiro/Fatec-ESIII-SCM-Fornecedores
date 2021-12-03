package com.temperosoft.scmfornecedores.core.business.strategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@Component
public class GerarCodigosContatos implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(GerarCodigosContatos.class);

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			StringBuilder code  = new StringBuilder();
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
			code.append("CON-").append(LocalDateTime.now().format(pattern));
			
			IntStream.range(0, aEntity.getContatos().size()).forEach(index -> {
				if (aEntity.getContatos().get(index).getCodigo().isEmpty() || aEntity.getContatos().get(index).getCodigo() == null)
					aEntity.getContatos().get(index).setCodigo(code.toString() + "-" + index);
			});
			
			logger.info("Códigos de contatos relacionados ao fornecedor {} gerados com sucesso.", aEntity.getCodigo());
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Ocorreu um erro ao gerar codigo para fornecedor", GerarCodigoFornecedor.class, aCase, e);
		}		
	}

}
