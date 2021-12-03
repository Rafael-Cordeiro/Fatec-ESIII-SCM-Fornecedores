package com.temperosoft.scmfornecedores.core.business.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.business.dao.ContatoDAO;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.Contato;
import com.temperosoft.scmfornecedores.domain.Fornecedor;

@Component
public class PersistirContatosFornecedor implements IStrategy<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(PersistirContatosFornecedor.class);
	
	@Autowired
	private ContatoDAO contatoDAO;

	@Override
	public void process(Fornecedor aEntity, INavigationCase<Fornecedor> aCase) {
		
		try {			
			List<Contato> contatosFormulario = aEntity.getContatos();
			
			List<Contato> contatosRegistrados = contatoDAO.findByFornecedorId(aEntity.getId());
			
			List<Contato> contatosSemelhantes = new ArrayList<Contato>();
			
			contatosRegistrados.forEach(contatoRegistrado -> {
				Predicate<Contato> contatoSemelhantePredicate = c -> c.getId().equals(contatoRegistrado.getId());
				try {
					Optional<Contato> optContato = contatosFormulario.stream().filter(contatoSemelhantePredicate).findAny();
					if (optContato.isPresent()) {
						if (Boolean.FALSE.equals(compare(optContato.get(), contatoRegistrado))); {
							Long rows = contatoDAO.update(optContato.get());
							logger.info("{} contato(s) do fornecedor {} atualizado(s) com sucesso", rows, aEntity.getCodigo());
						}
						contatosSemelhantes.add(optContato.get());
					}
				} catch (NullPointerException e) {
					logger.info("Novo contato encontrado");
				} catch (DataAccessException e) {
					Log.suspendExecutionThenLogException("Erro ao persistir contato(s)", PersistirContatosFornecedor.class, aCase, e);
				} catch (Exception e) {
					Log.suspendExecutionThenLogException("Erro ao persistir contato(s)", PersistirContatosFornecedor.class, aCase, e);
				}
			});

			contatosSemelhantes.forEach(contatoSemelhante -> {
				contatosFormulario.removeIf(c -> compareCode(c, contatoSemelhante));
				contatosRegistrados.removeIf(c -> compareCode(c, contatoSemelhante));
			});
			
			if (Boolean.FALSE.equals(contatosFormulario.isEmpty())) {
				contatosFormulario.forEach(contato -> {
					try {
						contato.setId(contatoDAO.create(contato, aEntity.getId()));
						logger.info("Contato do fornecedor {} persistido com sucesso", aEntity.getCodigo());
					} catch (Exception e) {
						Log.suspendExecutionThenLogException("Erro ao persistir contato", PersistirContatosFornecedor.class, aCase, e);
					}
				});
			}
			if (Boolean.FALSE.equals(contatosRegistrados.isEmpty())) {
				contatosRegistrados.forEach(contato -> {
					try {
						Long rows = contatoDAO.delete("", contato.getId());
						logger.info("{} contato(s) do fornecedor {} excluidos(s) com sucesso", rows, aEntity.getCodigo());
					} catch (Exception e) {
						Log.suspendExecutionThenLogException("Erro ao excluir contato", PersistirContatosFornecedor.class, aCase, e);
					}
					
				});
			}
			
		} catch (Exception e) {
			Log.suspendExecutionThenLogException("Erro ao cadastrar contatos relacionados ao fornecedor " + aEntity.getCodigo(), PersistirContatosFornecedor.class, aCase, e);
		}
		
	}
	
    private Boolean compare(Contato c1, Contato c2) {

        return c1.getNome().equals(c2.getNome()) 
                && c1.getEmail().equals(c2.getEmail())
                && c1.getDepartamento().equals(c2.getDepartamento())
                && c1.getTelefone().getDdi().equals(c2.getTelefone().getDdi())
                && c1.getTelefone().getDdd().equals(c2.getTelefone().getDdd())
                && c1.getTelefone().getNumero().equals(c2.getTelefone().getNumero())
                && c1.getTelefone().getRamal().equals(c2.getTelefone().getRamal())
                && c1.getTelefone().getTipoTelefone().getDescricao().equals(c2.getTelefone().getTipoTelefone().getDescricao());
    }
    
    private Boolean compareCode(Contato c1, Contato c2) {
    	
    	return c1.getCodigo().equals(c2.getCodigo());
    }

}
