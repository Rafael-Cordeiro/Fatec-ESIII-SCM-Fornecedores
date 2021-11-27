package com.temperosoft.scmfornecedores.domain;

import java.util.GregorianCalendar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Servico extends AbstractDomain {
	
	private String codigo;
	private String descricao;
	private GregorianCalendar dataInicio;
	private Fornecedor fornecedor;

}
