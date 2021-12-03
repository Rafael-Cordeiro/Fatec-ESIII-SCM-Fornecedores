package com.temperosoft.scmfornecedores.core.business.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.strategy.FindAllAuditoria;
import com.temperosoft.scmfornecedores.domain.application.Auditoria;

@Configuration
public class AuditoriaNavigation {

	@Autowired
	private FindAllAuditoria findAllAuditoria;
	
	@Bean(name = "CONSULTAR_AUDITORIA")
	public Navigation<Auditoria> consultarAuditoria() {
		return new NavigationBuilder<Auditoria>()
				.next(findAllAuditoria)
				.build();
	}
	
}
