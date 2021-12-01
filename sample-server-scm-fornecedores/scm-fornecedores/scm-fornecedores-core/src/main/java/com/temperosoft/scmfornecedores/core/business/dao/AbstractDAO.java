package com.temperosoft.scmfornecedores.core.business.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.core.utils.ValidationUtils;
import com.temperosoft.scmfornecedores.domain.AbstractDomain;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public abstract class AbstractDAO<T extends AbstractDomain> implements IDAO<T> {
	
	protected String table;
	protected String idTable;
	protected String alias;
	protected String trigram;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public String updateStatusQuery() {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(table)
		.append(" SET ").append(trigram).append("_tipo_cadastro").append(" = ? ")
		.append("WHERE ").append(idTable).append(" = ?");
		return sql.toString();
	}
	
	@Override
	public void inactivate(T aEntity) throws DataAccessException, Exception {
		getJdbcTemplate().update(updateStatusQuery(), TipoCadastroEnum.INATIVO.getSymbol(), ValidationUtils.returnIdOrNull(aEntity));
		
	}

	@Override
	public void activate(T aEntity) throws DataAccessException, Exception {
		getJdbcTemplate().update(updateStatusQuery(), TipoCadastroEnum.ATIVO.getSymbol(), ValidationUtils.returnIdOrNull(aEntity));
		
	}

}
