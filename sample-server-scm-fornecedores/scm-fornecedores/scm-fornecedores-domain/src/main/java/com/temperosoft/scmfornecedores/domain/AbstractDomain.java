package com.temperosoft.scmfornecedores.domain;

import com.dvsmedeiros.bce.domain.AbstractDomainEntity;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDomain extends AbstractDomainEntity {

	private Long id;
	private TipoCadastro tipoCadastro;
	
}
