package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.AtivarProduto;
import com.temperosoft.scmfornecedores.core.business.strategy.DefinirEntidadeAtiva;
import com.temperosoft.scmfornecedores.core.business.strategy.DesativarProduto;
import com.temperosoft.scmfornecedores.core.business.strategy.FindAllProdutosAtivos;
import com.temperosoft.scmfornecedores.core.business.strategy.FindProdutos;
import com.temperosoft.scmfornecedores.core.business.strategy.GerarCodigoProduto;
import com.temperosoft.scmfornecedores.core.business.strategy.PersistirProduto;
import com.temperosoft.scmfornecedores.domain.Produto;

@Configuration
public class ProdutoNavigation {

	@Autowired
	private FindProdutos findProdutos;
	
	@Autowired
	private GerarCodigoProduto gerarCodigoProduto;
	
	@Autowired
	private DefinirEntidadeAtiva definirEntidadeAtiva;
	
	@Autowired
	private PersistirProduto persistirProduto;
	
	@Autowired
	private DesativarProduto desativarProduto;
	
	@Autowired
	private AtivarProduto ativarProduto;
	
	@Autowired
	private FindAllProdutosAtivos findAllProdutosAtivos;
	
	@Bean(name="PERSISTIR_PRODUTOS_SALVAR")
	public Navigation<Produto> persistirProdutoSalvar() {
		return new NavigationBuilder<Produto>()
				.next(gerarCodigoProduto)
				.next(definirEntidadeAtiva)
				.next(persistirProduto)
				.build();
	}
	
	@Bean(name="PERSISTIR_PRODUTO_ATUALIZAR")
	public Navigation<Produto> persistirProdutoAtualizar() {
		return new NavigationBuilder<Produto>()
				.next(persistirProduto)
				.build();
	}
	
	@Bean(name="CONSULTAR_PRODUTOS")
	public Navigation<Produto> consultarProdutos() {
		return new NavigationBuilder<Produto>()
				.next(findProdutos)
				.build();
	}
	
	@Bean(name="DESATIVAR_PRODUTO")
	public Navigation<Produto> desativarFornecedor() {
		return new NavigationBuilder<Produto>()
				.next(desativarProduto)
				.build();
	}
	
	@Bean(name="ATIVAR_PRODUTO")
	public Navigation<Produto> ativarProduto() {
		return new NavigationBuilder<Produto>()
				.next(ativarProduto)
				.build();
	}
	
	@Bean(name="CONSULTAR_PRODUTOS_ATIVOS")
	public Navigation<Produto> consultarProdutosAtivos() {
		return new NavigationBuilder<Produto>()
				.next(findAllProdutosAtivos)
				.build();
	}
	
}
