package com.temperosoft.scmfornecedores.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fornecedor extends AbstractDomain {

	private String razaoSocial;
	private String nomeFantasia;
	private String codigo;
	private Endereco endereco;
	private List<Contato> contatos;
	private List<Documento> documentos;
	private List<Fornecivel> forneciveis;
	
}
