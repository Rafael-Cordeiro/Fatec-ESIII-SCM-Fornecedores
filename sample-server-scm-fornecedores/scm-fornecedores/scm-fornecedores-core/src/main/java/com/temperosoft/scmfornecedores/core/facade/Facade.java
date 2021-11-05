package com.temperosoft.scmfornecedores.core.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.core.controller.impl.Navigator;
import com.temperosoft.scmfornecedores.core.exception.InvalidStrategyConditionException;
import com.temperosoft.scmfornecedores.domain.AbstractDomain;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Facade implements IFacade {
	
	@Autowired
	private Navigator navigator;

	@Transactional
	public <T extends AbstractDomain> List<T> findAll(T aEntity ,String businessCaseName) {
		BusinessCase<T> aCase = new BusinessCaseBuilder().withName(businessCaseName);
		navigator.run(aEntity, aCase);
		
		Optional<List<T>> optionalList = aCase.getResult().getEntity();
		
		return optionalList.isPresent() ? optionalList.get() : new ArrayList<>();
	}
	
	@Transactional
	public <T extends AbstractDomain> T findById(T aEntity, String businessCaseName) {
		BusinessCase<T> aCase = new BusinessCaseBuilder().withName(businessCaseName);
		navigator.run(aEntity, aCase);
		
		if(aCase.getResult().getMessage() != null)
			throw new InvalidStrategyConditionException(aCase.getResult().getMessage());
		
		Optional<T> optional = aCase.getResult().getEntity();
		
		return optional.isPresent() ? optional.get() : null;
	}
	
}
