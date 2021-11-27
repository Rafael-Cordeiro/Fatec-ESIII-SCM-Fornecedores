package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.FindAllEstados;
import com.temperosoft.scmfornecedores.core.business.strategy.FindEstadosByPais;
import com.temperosoft.scmfornecedores.domain.Estado;

@Configuration
public class EstadoNavigation {

	@Autowired
	private FindAllEstados findAllEstados;
	
	@Autowired
	private FindEstadosByPais findEstadosByPais;
	
	@Bean(name="CONSULTAR_ESTADOS")
	public Navigation<Estado> consultarEstados() {
		return new NavigationBuilder<Estado>()
				.next(findAllEstados)
				.build();
	}
	
	@Bean(name="CONSULTAR_ESTADOS_POR_PAIS")
	public Navigation<Estado> consultarEstadosPorPais() {
		return new NavigationBuilder<Estado>()
				.next(findEstadosByPais)
				.build();
	}
}
