package com.temperosoft.scmfornecedores.core.business.dao;

import java.util.List;

import com.temperosoft.scmfornecedores.domain.AbstractDomain;

public interface IDAO<T extends AbstractDomain> {
	
	public Long create(T aEntity);
	
	public List<T> findAll();
	
	public T findById(Long id);
}
