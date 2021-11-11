package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.Cidade;
import com.temperosoft.scmfornecedores.domain.Estado;
import com.temperosoft.scmfornecedores.domain.Pais;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

@Component
public class CidadeDAO extends AbstractDAO<Cidade> {
	
	public CidadeDAO() {
		table = "CIDADES";
		idTable = "cid_id";
		alias = "cid1";
	}
	
	@Override
	public List<Cidade> findAll() {
		String sql = "SELECT * FROM CIDADES ORDER BY cid_est_id";
		return getJdbcTemplate().query(sql, new CidadeMinRowMapper());
	}
	
	public Cidade findByCodigo(String codigo) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM CIDADES AS cid ")
		.append("INNER JOIN ESTADOS AS est ")
		.append("ON est_id = cid_est_id ")
		.append("INNER JOIN PAISES AS pai ")
		.append("ON pai_id = est_pai_id ")
		.append("WHERE cid_codigo = '").append(codigo).append("'");
		
		return getJdbcTemplate().queryForObject(sql.toString(), new CidadeRowMapper());
	}
	
	@Override
	public Cidade findById(Long id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM CIDADES AS cid ")
		.append("INNER JOIN ESTADOS AS est ")
		.append("ON est_id = cid_est_id ")
		.append("INNER JOIN PAISES AS pai ")
		.append("ON pai_id = est_pai_id ")
		.append("WHERE cid_id = '").append(id).append("'");
		
		return getJdbcTemplate().queryForObject(sql.toString(), new CidadeRowMapper());
	}
	
	public List<Cidade> findByEstado(Long estId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM CIDADES AS cid ")
		.append("INNER JOIN ESTADOS AS est ")
		.append("ON est_id = cid_est_id ")
		.append("INNER JOIN PAISES AS pai ")
		.append("ON pai_id = est_pai_id ")
		.append("WHERE cid_est_id = ").append(estId);
		
		return getJdbcTemplate().query(sql.toString(), new CidadeRowMapper());
	}
	
	@Override
	public Long create(Cidade aEntity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Long update(Cidade aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Long delete(String status, Long id) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	class CidadeRowMapper implements RowMapper<Cidade> {
		
		@Override
		public Cidade mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Cidade cidade = new BeanPropertyRowMapper<>(Cidade.class).mapRow(rs, rowNum);
			cidade.setId(rs.getLong("cid_id"));
			cidade.setCodigo(rs.getString("cid_codigo"));
			cidade.setDescricao(rs.getString("cid_descricao"));
			
			TipoCadastro tipoCadastroCid = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastroCid.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("cid_tipo_cadastro")).getLiteral());

			cidade.setTipoCadastro(tipoCadastroCid);
			
			Estado estado = new BeanPropertyRowMapper<>(Estado.class).mapRow(rs, rowNum);
			estado.setId(rs.getLong("est_id"));
			estado.setCodigo(rs.getString("est_codigo"));
			estado.setDescricao(rs.getString("est_descricao"));
			
			TipoCadastro tipoCadastroEst = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastroEst.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("est_tipo_cadastro")).getLiteral());
			
			estado.setTipoCadastro(tipoCadastroEst);
			
			Pais pais = new BeanPropertyRowMapper<>(Pais.class).mapRow(rs, rowNum);
			pais.setId(rs.getLong("pai_id"));
			pais.setCodigo(rs.getString("pai_codigo"));
			pais.setDescricao(rs.getString("pai_descricao"));
			
			TipoCadastro tipoCadastoPai = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastoPai.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("pai_tipo_cadastro")).getLiteral());
			
			pais.setTipoCadastro(tipoCadastoPai);
			
			estado.setPais(pais);
			
			cidade.setEstado(estado);
			
			return cidade;
		}
	}
	
    class CidadeMinRowMapper implements RowMapper<Cidade> {

        @Override
        public Cidade mapRow(ResultSet rs, int rowNum) throws SQLException {

        	Cidade cidade = new BeanPropertyRowMapper<>(Cidade.class).mapRow(rs, rowNum);
            cidade.setId(rs.getLong("cid_id"));
            cidade.setCodigo(rs.getString("cid_codigo"));
            cidade.setDescricao(rs.getString("cid_descricao"));
            
            TipoCadastro tipoCadastro = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
            tipoCadastro.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("cid_tipo_cadastro")).getLiteral());
            
            cidade.setTipoCadastro(tipoCadastro);

            return cidade;
        }

    }
    
}
