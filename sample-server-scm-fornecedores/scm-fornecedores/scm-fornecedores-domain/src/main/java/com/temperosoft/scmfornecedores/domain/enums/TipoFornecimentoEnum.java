package com.temperosoft.scmfornecedores.domain.enums;

import java.security.InvalidParameterException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoFornecimentoEnum {
	PRODUTO("PRODUTO","P"),
	SERVICO("SERVICO","S");
	
	private String literal;
	private String symbol;
	
	public static TipoFornecimentoEnum atSymbol(String arg) {
		
		switch(arg.charAt(0)) {
		case 'P':
			return TipoFornecimentoEnum.PRODUTO;
		case 'S':
			return TipoFornecimentoEnum.SERVICO;
		default:
			throw new InvalidParameterException("Argumento de consulta de TipoFornecimentoEnum inválido");
		}
	}
	
	public static TipoFornecimentoEnum atLiteral(String literal) {
		
		if(literal.equals(TipoFornecimentoEnum.PRODUTO.getLiteral())) return TipoFornecimentoEnum.PRODUTO;
		if(literal.equals(TipoFornecimentoEnum.SERVICO.getLiteral())) return TipoFornecimentoEnum.SERVICO;
		throw new InvalidParameterException("Argumento de consulta de TipoFornecimentoEnum inválido");
	}
	
}
