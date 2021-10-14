package com.temperosoft.scmfornecedores.domain;

import com.temperosoft.scmfornecedores.domain.tipos.Tipo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pais extends Tipo {

	private String codigo;
	private String descricao;
	
}
