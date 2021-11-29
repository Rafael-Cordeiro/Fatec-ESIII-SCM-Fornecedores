package com.temperosoft.scmfornecedores.core.business.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.temperosoft.scmfornecedores.core.utils.Log;
import com.temperosoft.scmfornecedores.domain.AbstractDomain;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

@Component
public class DefinirEntidadeAtiva implements IStrategy<AbstractDomain> {
	
	private Logger logger = LoggerFactory.getLogger(DefinirEntidadeAtiva.class);

	@Override
	public void process(AbstractDomain aEntity, INavigationCase<AbstractDomain> aCase) {
		
		try {
			TipoCadastro tipoCadastro = new TipoCadastro();
			tipoCadastro.setDescricao(TipoCadastroEnum.ATIVO.getLiteral());
			aEntity.setTipoCadastro(tipoCadastro);
			logger.info("Tipo Cadastro de entidade definida como ATIVO");
		} catch (Exception e) {
			Log.suspendExecutionThenLogError(e.getMessage(), DefinirEntidadeAtiva.class, aCase);
		}
		
	}

}
