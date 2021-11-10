package com.temperosoft.scmfornecedores.domain.tipos;

import com.temperosoft.scmfornecedores.domain.enums.TipoFornecimentoEnum;

public class TipoFornecimento extends Tipo {

	public void setDescricao(String descricao) {
		
		super.setDescricao(TipoFornecimentoEnum.atLiteral(descricao).getLiteral());
	}
	
}
