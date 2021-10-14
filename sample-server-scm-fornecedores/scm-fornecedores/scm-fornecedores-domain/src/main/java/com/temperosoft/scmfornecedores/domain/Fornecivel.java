package com.temperosoft.scmfornecedores.domain;

import com.temperosoft.scmfornecedores.domain.tipos.TipoFornecimento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fornecivel extends AbstractDomain {
	
	private String codigo;
	private String descricao;
	private String dataInicio;
	private TipoFornecimento tipoFornecimento;
	
}
