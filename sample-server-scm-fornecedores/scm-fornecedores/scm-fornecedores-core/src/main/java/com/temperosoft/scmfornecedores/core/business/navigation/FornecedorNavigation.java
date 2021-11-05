package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.FindAllFornecedores;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@Configuration
public class FornecedorNavigation {

	@Autowired
	private FindAllFornecedores findAllFornecedores;
	
	@Bean(name = "CONSULTAR_FORNECEDORES")
	public Navigation<Fornecedor> consultarFornecedores() {
		return new NavigationBuilder<Fornecedor>()
				.next(findAllFornecedores)
				.build();
	}
}
