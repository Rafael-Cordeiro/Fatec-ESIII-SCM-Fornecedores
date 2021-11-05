package com.temperosoft.scmfornecedores.domain.tipos;

import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;

public class TipoCadastro extends Tipo {
	
	public void setDescricao(TipoCadastroEnum tipoCadastroEnum) {
		super.setDescricao(tipoCadastroEnum.toString());
	}
	
	public TipoCadastroEnum getDescricaoEmum() throws Exception {
		if(super.getDescricao().equals("RASCUNHO"))
			return TipoCadastroEnum.RASCUNHO;
		if(super.getDescricao().equals("ATIVO"))
			return TipoCadastroEnum.ATIVO;
		if(super.getDescricao().equals("INATIVO"))
			return TipoCadastroEnum.INATIVO;
		else
			throw new Exception("TipoCadastro registrado é invalido");
	}
	
}
