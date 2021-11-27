package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.Estado;
import com.temperosoft.scmfornecedores.domain.Pais;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

@Component
public class EstadoDAO extends AbstractDAO<Estado> {
	
	@Autowired
	private PaisDAO paisDAO;
	
	public EstadoDAO() {
		table = "ESTADOS";
		idTable = "est_id";
		alias = "est1";
	}

	@Override
	public List<Estado> findAll() {
		String sql = "SELECT *  FROM ESTADOS ORDER BY est_id";
		return getJdbcTemplate().query(sql, new EstadoRowMapper());
	}

	@Override
	public Estado findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Estado> findByPais(Long id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ESTADOS ")
		.append("WHERE est_pai_id = '").append(id).append("' ")
		.append("ORDER BY est_id");
		
		return getJdbcTemplate().query(sql.toString(), new EstadoRowMapper());
	}

	@Override
	public Long create(Estado aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Estado aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long delete(String status, Long id) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	class EstadoRowMapper implements RowMapper<Estado> {
		
		@Override
		public Estado mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Estado estado = new BeanPropertyRowMapper<>(Estado.class).mapRow(rs, rowNum);
			estado.setId(rs.getLong("est_id"));
			estado.setCodigo(rs.getString("est_codigo"));
			estado.setDescricao(rs.getString("est_descricao"));
			
			TipoCadastro tipoCadastroEst = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastroEst.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("est_tipo_cadastro")).getLiteral());
			
			estado.setTipoCadastro(tipoCadastroEst);
			
			Pais pais = new BeanPropertyRowMapper<>(Pais.class).mapRow(rs, rowNum);
			pais = paisDAO.findById(rs.getLong("est_pai_id"));
			
			estado.setPais(pais);
			
			return estado;
		}
	}

}
