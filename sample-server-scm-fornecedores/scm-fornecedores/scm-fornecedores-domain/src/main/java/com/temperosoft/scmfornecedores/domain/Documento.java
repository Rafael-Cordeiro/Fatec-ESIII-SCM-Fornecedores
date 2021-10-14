package com.temperosoft.scmfornecedores.domain;

import com.temperosoft.scmfornecedores.domain.tipos.TipoDocumento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Documento extends AbstractDomain {
	
	private String codigo;
	private TipoDocumento tipoDocumento;
	
}
