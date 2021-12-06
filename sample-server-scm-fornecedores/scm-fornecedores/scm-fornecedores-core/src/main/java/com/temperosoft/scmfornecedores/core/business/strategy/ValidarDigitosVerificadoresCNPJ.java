package com.temperosoft.scmfornecedores.core.business.strategy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@Component
public class ValidarDigitosVerificadoresCNPJ implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(ValidarDigitosVerificadoresCNPJ.class);

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {
			String cnpj = aEntity.getDocumentos().get(0).getCodigo();

			cnpj = cnpj.replace("/", "");
			cnpj = cnpj.replace(".", "");
			cnpj = cnpj.replace("-", "");
			
			int[] digitos = new int[15];
			List<Integer> arraySoma = new ArrayList<Integer>();
			
			for (int i = 0; i < cnpj.length(); i++) {
				digitos[i] = cnpj.charAt(i) - 48;
			}
			
			arraySoma.add(digitos[0] * 5);
			arraySoma.add(digitos[1] * 4);
			arraySoma.add(digitos[2] * 3);
			arraySoma.add(digitos[3] * 2);
			arraySoma.add(digitos[4] * 9);
			arraySoma.add(digitos[5] * 8);
			arraySoma.add(digitos[6] * 7);
			arraySoma.add(digitos[7] * 6);
			arraySoma.add(digitos[8] * 5);
			arraySoma.add(digitos[9] * 4);
			arraySoma.add(digitos[10] * 3);
			arraySoma.add(digitos[11] * 2);
			
			int soma1 = arraySoma.stream().reduce(0, Integer::sum);
			int resto1 = soma1 % 11;
			digitos[12] = (resto1 < 2) ? 0 : 11 - resto1;
			arraySoma = new ArrayList<Integer>();
			
			arraySoma.add(digitos[0] * 6);
			arraySoma.add(digitos[1] * 5);
			arraySoma.add(digitos[2] * 4);
			arraySoma.add(digitos[3] * 3);
			arraySoma.add(digitos[4] * 2);
			arraySoma.add(digitos[5] * 9);
			arraySoma.add(digitos[6] * 8);
			arraySoma.add(digitos[7] * 7);
			arraySoma.add(digitos[8] * 6);
			arraySoma.add(digitos[9] * 5);
			arraySoma.add(digitos[10] * 4);
			arraySoma.add(digitos[11] * 3);
			arraySoma.add(digitos[12] * 2);
			
			int soma2 = arraySoma.stream().reduce(0, Integer::sum);
			int resto2 = soma2 % 11;
			digitos[13] = (resto2 < 2) ? 0 : 11 - resto2;
			
			
			if (cnpj.charAt(cnpj.length()-2) != digitos[12] + 48)
				aCase.suspendExecution("CNPJ inválido");
			
			if (cnpj.charAt(cnpj.length()-1) != digitos[13] + 48)
				aCase.suspendExecution("CNPJ inválido");
			
			logger.info("Validação de CNPJ {}", aEntity.getDocumentos().get(0).getCodigo());
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Erro ao validar CNPJ", ValidarDigitosVerificadoresCNPJ.class, aCase, e);
		}
		
	}

}
