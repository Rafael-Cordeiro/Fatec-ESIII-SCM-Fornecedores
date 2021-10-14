package com.temperosoft.scmfornecedores.domain.tipos;

import com.temperosoft.scmfornecedores.domain.AbstractDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Tipo extends AbstractDomain {
	
	private String descricao;

}
