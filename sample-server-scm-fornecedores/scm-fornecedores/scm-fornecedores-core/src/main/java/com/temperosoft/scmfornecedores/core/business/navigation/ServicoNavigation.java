package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.ExcluirServico;
import com.temperosoft.scmfornecedores.core.business.strategy.FindAllServicos;
import com.temperosoft.scmfornecedores.core.business.strategy.GerarCodigoServico;
import com.temperosoft.scmfornecedores.core.business.strategy.PersistirServico;
import com.temperosoft.scmfornecedores.domain.Servico;

@Configuration
public class ServicoNavigation {
	
	@Autowired
	private FindAllServicos findAllServicos;
	
	@Autowired
	private GerarCodigoServico gerarCodigoServico;
	
	@Autowired
	private PersistirServico persistirServico;
	
	@Autowired
	private ExcluirServico excluirServico;

	@Bean(name="CONSULTAR_SERVICOS")
	public Navigation<Servico> consultarServicos() {
		return new NavigationBuilder<Servico>()
				.next(findAllServicos)
				.build();
	}
	
	@Bean(name="PERSISTIR_SERVICO_SALVAR")
	public Navigation<Servico> persistirServicoSalvar() {
		return new NavigationBuilder<Servico>()
				.next(gerarCodigoServico)
				.next(persistirServico)
				.build();
	}
	
	@Bean(name="PERSISTIR_SERVICO_ATUALIZAR")
	public Navigation<Servico> persistirServicoAtualizar() {
		return new NavigationBuilder<Servico>()
				.next(persistirServico)
				.build();
	}
	
	@Bean(name="EXCLUIR_SERVICO")
	public Navigation<Servico> excluirServico() {
		return new NavigationBuilder<Servico>()
				.next(excluirServico)
				.build();
	}
	
}
