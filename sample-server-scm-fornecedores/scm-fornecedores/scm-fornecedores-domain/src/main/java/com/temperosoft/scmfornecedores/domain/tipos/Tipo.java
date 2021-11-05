package com.temperosoft.scmfornecedores.domain.tipos;

import com.dvsmedeiros.bce.domain.IEntity;

public abstract class Tipo implements IEntity {
	
	private String descricao;

	protected String getDescricao() {
		return descricao;
	}

	protected void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
