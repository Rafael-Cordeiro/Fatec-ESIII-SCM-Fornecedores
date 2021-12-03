package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.application.Auditoria;

@Component
public class AuditoriaDAO extends AbstractDAO<Auditoria> {
	
	public AuditoriaDAO() {
        table = "AUDIT_TABLE";
        idTable = "aud_id";
        alias = "aud1";
        trigram = "aud";
	}

	@Override
	public List<Auditoria> findAll() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ").append(table);

		return getJdbcTemplate().query(sql.toString(), new AuditoriaRowMapper());
	}

	@Override
	public Auditoria findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(Auditoria aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Auditoria aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long delete(String status, Long id) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	class AuditoriaRowMapper implements RowMapper<Auditoria> {

		@Override
		public Auditoria mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Auditoria auditoria = new BeanPropertyRowMapper<>(Auditoria.class).mapRow(rs, rowNum);
			
			auditoria.setId(rs.getLong("aud_id"));
			auditoria.setDtHora(rs.getString("aud_dt_hora"));
			auditoria.setEvento(rs.getString("aud_evento"));
			auditoria.setTabela(rs.getString("aud_tabela"));
			auditoria.setOwner(rs.getString("aud_owner"));
			auditoria.setOldValue(rs.getString("aud_old"));
			auditoria.setNewValue(rs.getString("aud_new"));
			auditoria.setIdentificador(rs.getString("aud_identificador"));
			auditoria.setBdUser(rs.getString("aud_bduser"));
			
			return auditoria;
		}
		
		
	}

}
