package com.temperosoft.scmfornecedores.domain.tipos;

import com.temperosoft.scmfornecedores.domain.enums.TipoFornecimentoEnum;

public class TipoFornecimento extends Tipo {

	public void setDescricao(TipoFornecimentoEnum tipoFornecimentoEnum) {
		super.setDescricao(tipoFornecimentoEnum.toString());
	}
	
}
