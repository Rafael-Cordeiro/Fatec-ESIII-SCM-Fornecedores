package com.temperosoft.scmfornecedores.domain.tipos;

import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;

public class TipoCadastro extends Tipo {

	public void setDescricao(TipoCadastroEnum tipoCadastroEnum) {
		super.setDescricao(tipoCadastroEnum.toString());
	}
}
