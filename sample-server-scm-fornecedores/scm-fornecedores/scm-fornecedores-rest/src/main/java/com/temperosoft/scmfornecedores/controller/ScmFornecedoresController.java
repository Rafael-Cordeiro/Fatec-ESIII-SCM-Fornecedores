package com.temperosoft.scmfornecedores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dvsmedeiros.rest.rest.controller.DomainSpecificEntityController;
import com.temperosoft.scmfornecedores.domain.ScmFornecedores;

@Controller
@RequestMapping("scmfornecedores")
public class ScmFornecedoresController extends DomainSpecificEntityController<ScmFornecedores> {

	public ScmFornecedoresController() {
		super(ScmFornecedores.class);
	}

}
