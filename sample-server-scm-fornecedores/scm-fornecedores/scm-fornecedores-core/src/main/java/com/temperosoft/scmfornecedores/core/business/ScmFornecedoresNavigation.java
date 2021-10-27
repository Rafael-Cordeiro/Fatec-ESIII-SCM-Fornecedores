package com.temperosoft.scmfornecedores.core.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.impl.CidadesStrategy;
import com.temperosoft.scmfornecedores.core.business.impl.FooStrategy;
import com.temperosoft.scmfornecedores.domain.Cidade;
import com.temperosoft.scmfornecedores.domain.Pais;

@Configuration
public class ScmFornecedoresNavigation {

	@Autowired
	private FooStrategy testeBanco;
	
	@Autowired
	private CidadesStrategy cidadesStrategy;

	@Bean(name = "FOO")
	public Navigation<Pais> foo() {
		return new NavigationBuilder<Pais>()
				.next(testeBanco)
				.build();
	}
	
	@Bean(name = "CONSULTAR_CIDADES")
	public Navigation<Cidade> consultarCidades() {
		return new NavigationBuilder<Cidade>()
				.next(cidadesStrategy)
				.build();
	}

}
