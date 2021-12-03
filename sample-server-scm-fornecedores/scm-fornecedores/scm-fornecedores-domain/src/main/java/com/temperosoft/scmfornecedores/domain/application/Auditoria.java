package com.temperosoft.scmfornecedores.domain.application;

import com.temperosoft.scmfornecedores.domain.AbstractDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auditoria extends AbstractDomain {
	
	private String dtHora;
	private String evento;
	private String tabela;
	private String owner;
	private String coluna;
	private String oldValue;
	private String newValue;
	private String identificador;
	private String bdUser;
	
}
