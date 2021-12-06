package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.Pais;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

@Component
public class PaisDAO extends AbstractDAO<Pais> {

	public PaisDAO() {
		table = "PAISES";
		idTable = "pai_id";
		alias = "pai1";
	}

	@Override
	public List<Pais> findAll() {
		String sql = "SELECT * FROM PAISES ORDER BY pai_id";
		return getJdbcTemplate().query(sql, new PaisRowMapper());
	}

	@Override
	public Pais findById(Long id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM PAISES ")
		.append("WHERE pai_id = '").append(id).append("' ")
		.append("ORDER BY pai_id");
		
		return getJdbcTemplate().queryForObject(sql.toString(), new PaisRowMapper());
	}

	@Override
	public Long create(Pais aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Pais aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	class PaisRowMapper implements RowMapper<Pais> {
		
		@Override
		public Pais mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Pais pais = new BeanPropertyRowMapper<>(Pais.class).mapRow(rs,rowNum);
			pais.setId(rs.getLong("pai_id"));
			pais.setCodigo(rs.getString("pai_codigo"));
			pais.setDescricao(rs.getString("pai_descricao"));
			
			TipoCadastro tipoCadastro = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastro.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("pai_tipo_cadastro")).getLiteral());
			
			pais.setTipoCadastro(tipoCadastro);
			
			return pais;
		}
	}
	
}
