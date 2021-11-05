package com.temperosoft.scmfornecedores.domain.tipos;

import com.temperosoft.scmfornecedores.domain.enums.TipoFornecimentoEnum;

public class TipoFornecimento extends Tipo {

	public void setDescricao(TipoFornecimentoEnum tipoFornecimentoEnum) {
		super.setDescricao(tipoFornecimentoEnum.toString());
	}
	
	public TipoFornecimentoEnum getDescricaoEmum() throws Exception {
		if(super.getDescricao().equals("PRODUTOS"))
			return TipoFornecimentoEnum.PRODUTOS;
		if(super.getDescricao().equals("SERVICOS"))
			return TipoFornecimentoEnum.SERVICOS;
		else
			throw new Exception("TipoFornecimento registrado é invalido");
	}
	
}
