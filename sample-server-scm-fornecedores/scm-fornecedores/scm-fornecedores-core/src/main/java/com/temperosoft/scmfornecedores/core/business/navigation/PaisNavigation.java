package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.FindAllPaises;
import com.temperosoft.scmfornecedores.domain.Pais;

@Configuration
public class PaisNavigation {
	
	@Autowired
	private FindAllPaises findAllPaises;
	
	@Bean(name="CONSULTAR_PAISES")
	public Navigation<Pais> consultarPaises() {
		return new NavigationBuilder<Pais>()
				.next(findAllPaises)
				.build();
	}
}
