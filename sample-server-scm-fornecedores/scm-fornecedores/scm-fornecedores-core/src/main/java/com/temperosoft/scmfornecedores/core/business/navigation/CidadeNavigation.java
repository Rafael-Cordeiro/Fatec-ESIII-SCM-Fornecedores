package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.FindAllCidades;
import com.temperosoft.scmfornecedores.core.business.strategy.FindCidadeByCodigo;
import com.temperosoft.scmfornecedores.core.business.strategy.FindCidadeById;
import com.temperosoft.scmfornecedores.core.business.strategy.FindCidadesByEstado;
import com.temperosoft.scmfornecedores.domain.Cidade;

@Configuration
public class CidadeNavigation {
	
	@Autowired
	private FindAllCidades findAllCidades;
	
	@Autowired
	private FindCidadeByCodigo findCidadeByCodigo;	
	
	@Autowired
	private FindCidadesByEstado findCidadesByEstado;
	
	@Autowired
	private FindCidadeById findCidadeById;
	
	@Bean(name = "CONSULTAR_CIDADES")
	public Navigation<Cidade> consultarCidades() {
		return new NavigationBuilder<Cidade>()
				.next(findAllCidades)
				.build();
	}

	@Bean(name = "CONSULTAR_CIDADE_POR_CODIGO")
	public Navigation<Cidade> consultarCidadePorCodigo() {
		return new NavigationBuilder<Cidade>()
				.next(findCidadeByCodigo)
				.build();
	}
	
	@Bean(name = "FIND_CIDADES_BY_ESTADO")
	public Navigation<Cidade> consultarCidadesPorEstado() {
		return new NavigationBuilder<Cidade>()
				.next(findCidadesByEstado)
				.build();
	}
	
	@Bean(name = "CONSULTAR_CIDADE_POR_ID")
	public Navigation<Cidade> consultarCidadePorId() {
		return new NavigationBuilder<Cidade>()
				.next(findCidadeById)
				.build();
	}
	
}
