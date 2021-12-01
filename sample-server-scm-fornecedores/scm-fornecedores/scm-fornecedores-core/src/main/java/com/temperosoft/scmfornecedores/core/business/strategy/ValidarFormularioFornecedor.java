package com.temperosoft.scmfornecedores.core.business.strategy;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.Endereco;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@Component
public class ValidarFormularioFornecedor implements IStrategy<Fornecedor> {

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		StringBuilder msg = new StringBuilder();
		
		try {
			
			if (aEntity.getRazaoSocial() == null || aEntity.getRazaoSocial().equals(""))
				erroPreenchimentoObrigatorio("Razão Social", msg);
			
			if (aEntity.getNomeFantasia() == null || aEntity.getNomeFantasia().equals(""))
				erroPreenchimentoObrigatorio("Nome Fantasia", msg);
			
			aEntity.getDocumentos().forEach(doc -> {
				if (doc.getCodigo() == null || doc.getCodigo().equals(""))
					erroPreenchimentoObrigatorio(doc.getTipoDocumento().getDescricao(), msg);
			});
			
			Endereco endereco = aEntity.getEndereco();
			
			if (endereco.getLogradouro() == null || endereco.getLogradouro().equals(""))
				erroPreenchimentoObrigatorio("Logradouro", msg);
			
			if (endereco.getTipoLogradouro().getDescricao() == null || endereco.getTipoLogradouro().getDescricao().contentEquals(""))
				erroPreenchimentoObrigatorio("Tipo de Logradouro", msg);
				
			if (endereco.getNumero() == null || endereco.getNumero().equals(""))
				erroPreenchimentoObrigatorio("Número", msg);
			
			if (endereco.getBairro() == null || endereco.getBairro().equals(""))
				erroPreenchimentoObrigatorio("Bairro", msg);
			
			if (endereco.getTipoEndereco().getDescricao() == null || endereco.getTipoEndereco().getDescricao().equals(""))
				erroPreenchimentoObrigatorio("Tipo de Endereço", msg);
			
			if (aEntity.getContatos().isEmpty())
				erroListaVazia("contato", msg);
			else {
				aEntity.getContatos().forEach(contato -> {
					
					if (contato.getNome() == null || contato.getNome().equals(""))
						erroPreenchimentoObrigatorio("nome do contato", msg);
					
					if (contato.getEmail() == null || contato.getEmail().equals(""))
						erroPreenchimentoObrigatorio("email do contato", msg);
					
					if (contato.getDepartamento() == null || contato.getDepartamento().equals(""))
						erroPreenchimentoObrigatorio("departamento do contato", msg);
					
					if (contato.getTelefone().getDdi() == null || contato.getTelefone().getDdi().equals(""))
						erroPreenchimentoObrigatorio("ddi do telefone do contato", msg);
					
					if (contato.getTelefone().getDdd() == null || contato.getTelefone().getDdd().equals(""))
						erroPreenchimentoObrigatorio("ddd do telefone do contato", msg);
					
					if (contato.getTelefone().getNumero() == null || contato.getTelefone().getNumero().equals(""))
						erroPreenchimentoObrigatorio("número do telefone do contato", msg);
					
					if (contato.getTelefone().getTipoTelefone().getDescricao() == null || contato.getTelefone().getTipoTelefone().getDescricao().equals(""))
						erroPreenchimentoObrigatorio("tipo de telefone do contato", msg);
					
				});
			}
			
			if (aEntity.getProdutos().isEmpty())
				erroListaVazia("produto", msg);
			
			if (!msg.toString().isEmpty())
				aCase.suspendExecution(msg.toString());
			
			msg.delete(0, msg.length());
		} catch (Exception e) {
			Log.suspendExecutionThenLogException(msg.toString(), ValidarFormularioFornecedor.class, aCase, e);
		}
		
	}
	
    private void erroPreenchimentoObrigatorio(String campo, StringBuilder msg) {
        msg.append("O preenchimento de ").append(campo).append(" é obrigatório.").append(System.lineSeparator());
    }
    
    private void erroListaVazia(String lista, StringBuilder msg) {
    	msg.append("Nenhum ").append(lista).append(" foi adicionado").append(System.lineSeparator());
    }

}
