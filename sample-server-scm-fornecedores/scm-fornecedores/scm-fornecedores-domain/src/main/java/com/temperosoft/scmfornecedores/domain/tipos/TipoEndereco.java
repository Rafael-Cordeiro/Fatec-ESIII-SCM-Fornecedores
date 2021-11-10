package com.temperosoft.scmfornecedores.domain.tipos;

import com.temperosoft.scmfornecedores.domain.enums.TipoEnderecoEnum;

public class TipoEndereco extends Tipo {

	public void setDescricao(String descricao) {
		
		super.setDescricao(TipoEnderecoEnum.atLiteral(descricao).getLiteral());
	}
}
