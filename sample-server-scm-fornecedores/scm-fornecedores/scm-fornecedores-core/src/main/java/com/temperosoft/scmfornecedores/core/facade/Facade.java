package com.temperosoft.scmfornecedores.core.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.core.controller.impl.Navigator;
import com.temperosoft.scmfornecedores.domain.AbstractDomain;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Facade implements IFacade {
	
	@Autowired
	private Navigator navigator;

	@Transactional
	public <T extends AbstractDomain> List<T> testeBanco(T aEntity ,String businessCaseName) {
		BusinessCase<T> aCase = new BusinessCaseBuilder().withName(businessCaseName);
		navigator.run(aEntity, aCase);
		
		Optional<List<T>> optionalList = aCase.getResult().getEntity();
		
		return optionalList.isPresent() ? optionalList.get() : new ArrayList<>();
	}
}
