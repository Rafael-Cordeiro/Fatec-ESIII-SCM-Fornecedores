package com.temperosoft.scmfornecedores.domain.tipos;

import com.temperosoft.scmfornecedores.domain.enums.TipoEnderecoEnum;

public class TipoEndereco extends Tipo {

	public void setDescricao(TipoEnderecoEnum tipoEnderecoEnum) {
		super.setDescricao(tipoEnderecoEnum.toString());
	}
	
	public TipoEnderecoEnum getDescricaoEmum() throws Exception {
		if(super.getDescricao().equals("GALPAO"))
			return TipoEnderecoEnum.GALPAO;
		if(super.getDescricao().equals("SALA_COMERCIAL"))
			return TipoEnderecoEnum.SALA_COMERCIAL;
		if(super.getDescricao().equals("EDIFICIO"))
			return TipoEnderecoEnum.EDIFICIO;
		else
			throw new Exception("TipoEndereco registrado é invalido");
	}
	
}
