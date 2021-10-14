package com.temperosoft.scmfornecedores.core.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dvsmedeiros.bce.core.controller.impl.Navigation;
import com.dvsmedeiros.bce.core.controller.impl.NavigationBuilder;
import com.temperosoft.scmfornecedores.core.business.impl.ScmFornecedoresStrategy;
import com.temperosoft.scmfornecedores.domain.ScmFornecedores;

@Configuration
public class ScmFornecedoresNavigation {

	@Autowired
	private ScmFornecedoresStrategy validateCode;

	@Bean(name = "SAVE_SCM_FORNECEDORES")
	public Navigation<ScmFornecedores> generateReport() {
		return new NavigationBuilder<ScmFornecedores>()
				.next(validateCode)
				.build();
	}

}
