package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.FooStrategy;
import com.temperosoft.scmfornecedores.domain.Pais;

@Configuration
public class ScmFornecedoresNavigation {

	@Autowired
	private FooStrategy testeBanco;
	
	@Bean(name = "FOO")
	public Navigation<Pais> foo() {
		return new NavigationBuilder<Pais>()
				.next(testeBanco)
				.build();
	}

}
