package com.temperosoft.scmfornecedores.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto extends AbstractDomain {

	private String codigo;
	private String descricao;
	
}
