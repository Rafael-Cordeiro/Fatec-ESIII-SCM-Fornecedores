package com.temperosoft.scmfornecedores.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contato extends AbstractDomain {

	private String codigo;
	private String nome;
	private String email;
	private String departamento;
	private Telefone telefone;
	
}
