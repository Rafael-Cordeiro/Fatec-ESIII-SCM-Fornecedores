package com.temperosoft.scmfornecedores.domain.tipos;

import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;

public class TipoCadastro extends Tipo {
	
	@Override
	public void setDescricao(String descricao) {
		
		super.setDescricao(TipoCadastroEnum.atLiteral(descricao).getLiteral());
	}
	
}
