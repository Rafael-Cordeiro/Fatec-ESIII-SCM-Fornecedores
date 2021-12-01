package com.temperosoft.scmfornecedores.domain.enums;

import java.security.InvalidParameterException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCadastroEnum {
	RASCUNHO("RASCUNHO","R"),
	ATIVO("ATIVO","A"),
	INATIVO("INATIVO","I"),
	UNDEFINED("","");
	
	private String literal;
	private String symbol;
	
	public static TipoCadastroEnum atSymbol(String symbol) {
		
		if (symbol.replace(" ", "").isEmpty()) return TipoCadastroEnum.UNDEFINED;
		
		switch(symbol.charAt(0)) {
		case 'R':
			return TipoCadastroEnum.RASCUNHO;
		case 'A':
			return TipoCadastroEnum.ATIVO;
		case 'I':
			return TipoCadastroEnum.INATIVO;
		default:
			throw new InvalidParameterException("Argumento de consulta de TipoCadastroEnum inválido");
		}
	}
	
	public static TipoCadastroEnum atLiteral(String literal) {
		
		if (literal.replace(" ", "").isEmpty()) return TipoCadastroEnum.UNDEFINED;
		
		if(literal.equals(TipoCadastroEnum.RASCUNHO.getLiteral())) return TipoCadastroEnum.RASCUNHO;
		if(literal.equals(TipoCadastroEnum.ATIVO.getLiteral())) return TipoCadastroEnum.ATIVO;
		if(literal.equals(TipoCadastroEnum.INATIVO.getLiteral())) return TipoCadastroEnum.INATIVO;
		throw new InvalidParameterException("Argumento de consulta de TipoCadastroEnum inválido");
	}

}
