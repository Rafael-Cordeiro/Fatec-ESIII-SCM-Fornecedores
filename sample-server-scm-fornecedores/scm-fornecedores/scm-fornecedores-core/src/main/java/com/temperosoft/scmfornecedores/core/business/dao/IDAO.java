package com.temperosoft.scmfornecedores.core.business.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.temperosoft.scmfornecedores.domain.AbstractDomain;

public interface IDAO<T extends AbstractDomain> {
	
	public List<T> findAll();
	
	public T findById(Long id);
	
	public Long create(T aEntity) throws DataAccessException, Exception;
	
	public Long update(T aEntity) throws DataAccessException, Exception;
	
	public Long delete(String status, Long id) throws DataAccessException, Exception;
	
}
