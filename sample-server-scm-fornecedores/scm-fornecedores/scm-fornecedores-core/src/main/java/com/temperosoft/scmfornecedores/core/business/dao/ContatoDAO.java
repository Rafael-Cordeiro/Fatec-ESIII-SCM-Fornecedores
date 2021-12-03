package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.Contato;
import com.temperosoft.scmfornecedores.domain.Telefone;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;
import com.temperosoft.scmfornecedores.domain.tipos.TipoTelefone;

@Component
public class ContatoDAO extends AbstractDAO<Contato> {
	
	public ContatoDAO() {
        table = "CONTATOS";
        idTable = "con_id";
        alias = "con1";
        trigram = "con";
	}

	@Override
	public List<Contato> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contato findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(Contato aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Contato aEntity) throws DataAccessException, Exception {

		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE ").append(table).append(" SET ")
		.append("con_codigo = ?, ")
		.append("con_ddi = ?, ")
		.append("con_ddd = ?, ")
		.append("con_telefone = ?, ")
		.append("con_ramal = ?, ")
		.append("con_tipo_telefone = ?, ")
		.append("con_nome = ?, ")
		.append("con_email = ?, ")
		.append("con_departamento = ? ")
		.append("WHERE con_id = ?");
		
		return (long) getJdbcTemplate().update(sql.toString(),
				aEntity.getCodigo(),
				aEntity.getTelefone().getDdi(),
				aEntity.getTelefone().getDdd(),
				aEntity.getTelefone().getNumero(),
				aEntity.getTelefone().getRamal(),
				aEntity.getTelefone().getTipoTelefone().getDescricao(),
				aEntity.getNome(),
				aEntity.getEmail(),
				aEntity.getDepartamento(),
				aEntity.getId()
		);
	}

	@Override
	public Long delete(String status, Long id) throws DataAccessException, Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM ").append(table)
		.append(" WHERE ").append(idTable).append(" = ").append(id);
		
		return (long) getJdbcTemplate().update(sql.toString());
	}
	
	public Long create(Contato aEntity, Long forId) throws DataAccessException, Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO ").append(table).append(" (")
		.append("con_codigo, ")
		.append("con_ddi, ")
		.append("con_ddd, ")
		.append("con_telefone, ")
		.append("con_ramal, ")
		.append("con_tipo_telefone, ")
		.append("con_nome, ")
		.append("con_email, ")
		.append("con_departamento, ")
		.append("con_for_id, ")
		.append("con_tipo_cadastro")
		.append(") VALUES ")
		.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, aEntity.getCodigo());
				ps.setString(2, aEntity.getTelefone().getDdi());
				ps.setString(3, aEntity.getTelefone().getDdd());
				ps.setString(4, aEntity.getTelefone().getNumero());
				ps.setString(5, aEntity.getTelefone().getRamal());
				ps.setString(6, aEntity.getTelefone().getTipoTelefone().getDescricao());
				ps.setString(7, aEntity.getNome());
				ps.setString(8, aEntity.getEmail());
				ps.setString(9, aEntity.getDepartamento());
				ps.setLong(10, forId);
				ps.setString(11, TipoCadastroEnum.ATIVO.getSymbol());
				
				return ps;
			}
		}, keyHolder);
		
		Integer id = (Integer) keyHolder.getKeys().get("con_id");
		return (Long) id.longValue();
	}
	
	public List<Contato> findByFornecedorId(Long forId) throws DataAccessException, Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ").append(table)
		.append(" WHERE con_for_id = ").append((forId == null) ? 0 : forId);
		
		return getJdbcTemplate().query(sql.toString(), new ContatoRowMapper());
	}
	
	class ContatoRowMapper implements RowMapper<Contato> {

		@Override
		public Contato mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Contato contato = new BeanPropertyRowMapper<>(Contato.class).mapRow(rs, rowNum);
			contato.setId(rs.getLong("con_id"));
			
			contato.setCodigo(rs.getString("con_codigo"));			
			contato.setNome(rs.getString("con_nome"));
			
			Telefone telefone = new BeanPropertyRowMapper<>(Telefone.class).mapRow(rs, rowNum);
			telefone.setDdi(rs.getString("con_ddi"));
			telefone.setDdd(rs.getString("con_ddd"));
			telefone.setNumero(rs.getString("con_telefone"));
			telefone.setRamal(rs.getString("con_ramal"));
			
			TipoTelefone tipoTelefone = new BeanPropertyRowMapper<>(TipoTelefone.class).mapRow(rs, rowNum);
			tipoTelefone.setDescricao(rs.getString("con_tipo_telefone"));
			
			telefone.setTipoTelefone(tipoTelefone);
			
			contato.setTelefone(telefone);
			
			contato.setEmail(rs.getString("con_email"));
			contato.setDepartamento(rs.getString("con_departamento"));

			TipoCadastro tipoCadastro = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastro.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("con_tipo_cadastro")).getLiteral());
			
			contato.setTipoCadastro(tipoCadastro);
			
			return contato;
		}
		
	}

}
