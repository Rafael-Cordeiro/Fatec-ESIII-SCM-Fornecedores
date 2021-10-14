package com.temperosoft.scmfornecedores.domain;

import com.temperosoft.scmfornecedores.domain.tipos.Tipo;
import com.temperosoft.scmfornecedores.domain.tipos.TipoEndereco;
import com.temperosoft.scmfornecedores.domain.tipos.TipoLogradouro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco extends Tipo {

	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String complemento;
	private TipoEndereco tipoEndereco;
	private TipoLogradouro tipoLogradouro;
	
}
