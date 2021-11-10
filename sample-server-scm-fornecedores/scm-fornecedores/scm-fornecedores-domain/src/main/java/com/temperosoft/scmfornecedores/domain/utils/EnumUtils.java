package com.temperosoft.scmfornecedores.domain.utils;

import java.security.InvalidParameterException;

import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.enums.TipoEnderecoEnum;
import com.temperosoft.scmfornecedores.domain.enums.TipoFornecimentoEnum;

public class EnumUtils {

	public static TipoCadastroEnum getTipoCadastroEnum(String arg) {
		
		switch(arg.charAt(0)) {
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
	
	public static String getTipoCadastroSymbol(TipoCadastroEnum arg) {
		
		if (arg == TipoCadastroEnum.RASCUNHO) return "R";
		if (arg == TipoCadastroEnum.ATIVO) return "A";
		if (arg == TipoCadastroEnum.INATIVO) return "I";
		throw new InvalidParameterException("Argumento de consulta de símbolo de TipoCadastroEnum inválido");
	}
	
	public static TipoEnderecoEnum getTipoEnderecoEnum(String arg) {
		
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
	
	public static String getTipoEnderecoSymbol(TipoEnderecoEnum arg) {
		
		if (arg == TipoEnderecoEnum.GALPAO) return "G";
		if (arg == TipoEnderecoEnum.SALA_COMERCIAL) return "S";
		if (arg == TipoEnderecoEnum.EDIFICIO) return "E";
		throw new InvalidParameterException("Argumento de consulta de símbolo de TipoEnderecoEnum inválido");
	}
	
	public static TipoFornecimentoEnum getTipoFornecimentoEnum(String arg) {
		
		switch(arg.charAt(0)) {
		case 'P':
			return TipoFornecimentoEnum.PRODUTO;
		case 'S':
			return TipoFornecimentoEnum.SERVICO;
		default:
			throw new InvalidParameterException("Argumento de consulta de TipoFornecimentoEnum inválido");
		}
	}
	
	public static String getTipoFornecimentoSymbol(TipoFornecimentoEnum arg) {
		
		if (arg == TipoFornecimentoEnum.PRODUTO) return "P";
		if (arg == TipoFornecimentoEnum.SERVICO) return "S";
		throw new InvalidParameterException("Argumento de consulta de símbolo de TipoFornecimentoEnum inválido");
	}
	
}
