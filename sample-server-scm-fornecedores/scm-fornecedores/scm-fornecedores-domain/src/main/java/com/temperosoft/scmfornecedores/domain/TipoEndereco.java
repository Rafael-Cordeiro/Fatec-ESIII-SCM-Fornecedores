package com.temperosoft.scmfornecedores.domain;

import com.google.gson.Gson;
import com.temperosoft.scmfornecedores.domain.enums.TipoEnderecoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoEndereco extends Tipo {
	

	public void setDescricao(TipoEnderecoEnum te) {
		super.setDescricao(te.toString());
	}
	
	public static void main(String... args) {
		TipoEndereco te = new TipoEndereco();

		te.setId(1l);
		te.setDescricao(TipoEnderecoEnum.EDIFICIO);
		
		Gson g = new Gson();
		
		System.out.println(g.toJson(te));
	}
}
