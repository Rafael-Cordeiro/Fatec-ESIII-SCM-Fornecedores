package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.FindForneciveisByTipo;
import com.temperosoft.scmfornecedores.domain.Fornecivel;

@Configuration
public class FornecivelNavigation {

	@Autowired
	private FindForneciveisByTipo findForneciveisByTipo;
	
	@Bean(name="CONSULTAR_FORNECIVEIS_POR_TIPO")
	public Navigation<Fornecivel> consultarForneciveisPorTipo() {
		return new NavigationBuilder<Fornecivel>()
				.next(findForneciveisByTipo)
				.build();
	}
}
