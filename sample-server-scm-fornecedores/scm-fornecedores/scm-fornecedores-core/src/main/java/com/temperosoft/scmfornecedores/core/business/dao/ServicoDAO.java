package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.Fornecedor;
import com.temperosoft.scmfornecedores.domain.Servico;

@Component
public class ServicoDAO extends AbstractDAO<Servico> {
	
	@Autowired
	private FornecedorDAO fornecedorDAO;

	public ServicoDAO() {
		table = "SERVICOS";
		idTable = "ser_id";
		alias = "ser1";
	}
	
	@Override
	public List<Servico> findAll() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ").append(table);
		
		return getJdbcTemplate().query(sql.toString(), new ServicoRowMapper());
	}

	@Override
	public Servico findById(Long id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ")
		.append(table)
		.append(" AS ")
		.append(alias)
		.append(" WHERE ")
		.append(alias)
		.append(".ser_id = ")
		.append(id);
		
		return getJdbcTemplate().queryForObject(sql.toString(), new ServicoRowMapper());
	}

	@Override
	public Long create(Servico aEntity) throws DataAccessException, Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO ").append(table).append(" (")
		.append("ser_codigo, ")
		.append("ser_descricao, ")
		.append("ser_data_inicio, ")
		.append("ser_for_id ")
		.append(") VALUES (?, ?, ?, ?)");
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, aEntity.getCodigo());
				ps.setString(2, aEntity.getDescricao());
				ps.setDate(3, new java.sql.Date(aEntity.getDataInicio().getTime()));
				ps.setLong(4, aEntity.getFornecedor().getId());
				
				return ps;
			}
			
		}, keyHolder);
		
		Integer id = (Integer) keyHolder.getKeys().get("ser_id");
		return id.longValue();
	}

	@Override
	public Long update(Servico aEntity) throws DataAccessException, Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE ").append(table).append(" SET ")
		.append("ser_codigo = ?, ")
		.append("ser_descricao = ?, ")
		.append("ser_data_inicio = ?, ")
		.append("ser_for_id = ? ")
		.append("WHERE ").append(idTable).append(" = ?");
		
		return (long) getJdbcTemplate().update(sql.toString(),
				aEntity.getCodigo(),
				aEntity.getDescricao(),
				new java.sql.Date(aEntity.getDataInicio().getTime()),
				aEntity.getFornecedor().getId(),
				aEntity.getId()
		);
	}

	@Override
	public Long delete(String status, Long id) throws DataAccessException, Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM ").append(table)
		.append(" WHERE ser_id = ?");
		
		return (long) getJdbcTemplate().update(sql.toString(), id);
	}
	
	
	class ServicoRowMapper implements RowMapper<Servico> {
		
		@Override
		public Servico mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Servico servico = new BeanPropertyRowMapper<>(Servico.class).mapRow(rs, rowNum);
			
			servico.setId(rs.getLong("ser_id"));
			
			servico.setCodigo(rs.getString("ser_codigo"));
			servico.setDescricao(rs.getString("ser_descricao"));
			servico.setDataInicio(rs.getDate("ser_data_inicio"));
			
			Fornecedor fornecedor = fornecedorDAO.findById(rs.getLong("ser_for_id"));
			
			servico.setFornecedor(fornecedor);
			
			return servico;
		}
	}

}
