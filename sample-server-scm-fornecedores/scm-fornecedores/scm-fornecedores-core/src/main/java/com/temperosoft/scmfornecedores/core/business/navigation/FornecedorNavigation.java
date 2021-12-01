package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.AtivarFornecedor;
import com.temperosoft.scmfornecedores.core.business.strategy.DefinirEntidadeAtiva;
import com.temperosoft.scmfornecedores.core.business.strategy.DesativarFornecedor;
import com.temperosoft.scmfornecedores.core.business.strategy.FindAllFornecedores;
import com.temperosoft.scmfornecedores.core.business.strategy.FindAllFornecedoresAtivos;
import com.temperosoft.scmfornecedores.core.business.strategy.FindFornecedorById;
import com.temperosoft.scmfornecedores.core.business.strategy.GerarCodigoFornecedor;
import com.temperosoft.scmfornecedores.core.business.strategy.PersistirContatosFornecedor;
import com.temperosoft.scmfornecedores.core.business.strategy.PersistirDocumentoFornecedor;
import com.temperosoft.scmfornecedores.core.business.strategy.PersistirFornecedor;
import com.temperosoft.scmfornecedores.core.business.strategy.PersistirRelacoesFornecedorProduto;
import com.temperosoft.scmfornecedores.core.business.strategy.ValidarCNPJUnico;
import com.temperosoft.scmfornecedores.core.business.strategy.ValidarFormularioFornecedor;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@Configuration
public class FornecedorNavigation {
	
	@Autowired
	private FindAllFornecedores findAllFornecedores;
	
	@Autowired
	private FindFornecedorById findFornecedorById;
	
	@Autowired
	private PersistirFornecedor persistirFornecedor;
	
	@Autowired
	private DesativarFornecedor desativarFornecedor;
	
	@Autowired
	private DefinirEntidadeAtiva definirEntidadeAtiva;
	
	@Autowired
	private PersistirRelacoesFornecedorProduto persistirRelacoesFornecedorProduto;
	
	@Autowired
	private PersistirContatosFornecedor persistirContatosFornecedor;
	
	@Autowired
	private GerarCodigoFornecedor gerarCodigoFornecedor;
	
	@Autowired
	private PersistirDocumentoFornecedor persistirDocumentoFornecedor;
	
	@Autowired
	private AtivarFornecedor ativarFornecedor;
	
	@Autowired
	private ValidarFormularioFornecedor validarFormularioFornecedor;
	
	@Autowired
	private ValidarCNPJUnico validarCNPJUnico;
	
	@Autowired
	private FindAllFornecedoresAtivos findAllFornecedoresAtivos;
	
	@Bean(name="PERSISTE_FORNECEDOR_SALVAR")
	public Navigation<Fornecedor> persistirFornecedorSalvar() {
		return new NavigationBuilder<Fornecedor>()
				.next(validarFormularioFornecedor)
				.next(validarCNPJUnico)
				.next(gerarCodigoFornecedor)
				.next(definirEntidadeAtiva)
				.next(persistirFornecedor)
				.next(persistirRelacoesFornecedorProduto)
				.next(persistirContatosFornecedor)
				.next(persistirDocumentoFornecedor)
				.build();
	}
	
	@Bean(name="PERSISTE_FORNECEDOR_ATUALIZAR")
	public Navigation<Fornecedor> persistirFornecedorAtualizar() {
		return new NavigationBuilder<Fornecedor>()
				.next(validarFormularioFornecedor)
				.next(validarCNPJUnico)
				.next(persistirFornecedor)
				.next(persistirRelacoesFornecedorProduto)
				.next(persistirContatosFornecedor)
				.next(persistirDocumentoFornecedor)
				.build();
	}
	
	@Bean(name="CONSULTAR_FORNECEDORES")
	public Navigation<Fornecedor> consultarFornecedores() {
		return new NavigationBuilder<Fornecedor>()
				.next(findAllFornecedores)
				.build();
	}
	
	@Bean(name="CONSULTAR_FORNECEDOR_POR_ID")
	public Navigation<Fornecedor> consultarFornecedorPorId() {
		return new NavigationBuilder<Fornecedor>()
				.next(findFornecedorById)
				.build();
	}
	
	@Bean(name="DESATIVAR_FORNECEDOR")
	public Navigation<Fornecedor> desativarFornecedor() {
		return new NavigationBuilder<Fornecedor>()
				.next(desativarFornecedor)
				.build();
	}
	
	@Bean(name="ATIVAR_FORNECEDOR")
	public Navigation<Fornecedor> ativarFornecedor() {
		return new NavigationBuilder<Fornecedor>()
				.next(ativarFornecedor)
				.build();
	}
	
	@Bean(name="CONSULTAR_FORNECEDORES_ATIVOS")
	public Navigation<Fornecedor> consultarFornecedoresAtivos() {
		return new NavigationBuilder<Fornecedor>()
				.next(findAllFornecedoresAtivos)
				.build();
	}
	
}
