package com.temperosoft.scmfornecedores.domain;

import com.temperosoft.scmfornecedores.domain.tipos.TipoTelefone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Telefone extends AbstractDomain {

	private String ddd;
	private String ddi;
	private String numero;
	private String ramal;
	private TipoTelefone tipoTelefone;
	
}
