package com.temperosoft.scmfornecedores.domain.enums;

import java.security.InvalidParameterException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoEnderecoEnum {
	GALPAO("GALPAO","G"),
	SALA_COMERCIAL("SALA_COMERCIAL","S"),
	EDIFICIO("EDIFICIO","E");
	
	private String literal;
	private String symbol;
	
	public static TipoEnderecoEnum atSymbol(String arg) {
		
		switch(arg.charAt(0)) {
		case 'G':
			return TipoEnderecoEnum.GALPAO;
		case 'S':
			return TipoEnderecoEnum.SALA_COMERCIAL;
		case 'E':
			return TipoEnderecoEnum.EDIFICIO;
		default:
			throw new InvalidParameterException("Argumento de consulta de TipoEnderecoEnum inválido");
		}
	}
	
	public static TipoEnderecoEnum atLiteral(String literal) {

		if(literal.equals(TipoEnderecoEnum.GALPAO.getLiteral())) return TipoEnderecoEnum.GALPAO;
		if(literal.equals(TipoEnderecoEnum.SALA_COMERCIAL.getLiteral())) return TipoEnderecoEnum.SALA_COMERCIAL;
		if(literal.equals(TipoEnderecoEnum.EDIFICIO.getLiteral())) return TipoEnderecoEnum.EDIFICIO;
		throw new InvalidParameterException("Argumento de consulta de TipoEnderecoEnum inválido");
	}
	
}
