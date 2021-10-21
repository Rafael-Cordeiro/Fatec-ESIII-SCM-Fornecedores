package com.temperosoft.scmfornecedores.domain.tipos;

import com.dvsmedeiros.bce.domain.IEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Tipo implements IEntity {
	
	private String descricao;

}
