package com.temperosoft.scmfornecedores.core.business.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.AbstractDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public abstract class AbstractDAO<T extends AbstractDomain> implements IDAO<T> {
	
	protected String table;
	protected String idTable;
	protected String alias;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

}
