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
	
	public static TipoFornecimentoEnum getTipoFornecimentoEnum(String arg) {
		
		switch(arg.charAt(0)) {
		case 'P':
			return TipoFornecimentoEnum.PRODUTOS;
		case 'S':
			return TipoFornecimentoEnum.SERVICOS;
		default:
			throw new InvalidParameterException("Argumento de consulta de TipoFornecimentoEnum inválido");
		}
	}
	
}
