package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.FindProdutos;
import com.temperosoft.scmfornecedores.domain.Produto;

@Configuration
public class ProdutoNavigation {

	@Autowired
	private FindProdutos findProdutos;
	
	@Bean(name="CONSULTAR_PRODUTOS")
	public Navigation<Produto> consultarForneciveisPorTipo() {
		return new NavigationBuilder<Produto>()
				.next(findProdutos)
				.build();
	}
}
