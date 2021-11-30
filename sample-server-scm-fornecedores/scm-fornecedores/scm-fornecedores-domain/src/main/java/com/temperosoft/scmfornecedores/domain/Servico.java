package com.temperosoft.scmfornecedores.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Servico extends AbstractDomain {
	
	private String codigo;
	private String descricao;
	private Date dataInicio;
	private Fornecedor fornecedor;

}
