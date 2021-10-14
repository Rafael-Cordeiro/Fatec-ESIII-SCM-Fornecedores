package com.temperosoft.scmfornecedores.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Estado extends AbstractDomain {

	private String codigo;
	private String descricao;
	private Pais pais;
	
}
