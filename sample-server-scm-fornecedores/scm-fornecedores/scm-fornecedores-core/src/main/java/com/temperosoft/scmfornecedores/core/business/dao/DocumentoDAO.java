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

import com.temperosoft.scmfornecedores.domain.Documento;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;
import com.temperosoft.scmfornecedores.domain.tipos.TipoDocumento;

@Component
public class DocumentoDAO extends AbstractDAO<Documento> {
	
	public DocumentoDAO() {
        table = "DOCUMENTOS";
        idTable = "doc_id";
        alias = "doc1";
        trigram = "doc";
	}

	@Override
	public List<Documento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documento findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(Documento aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Documento aEntity) throws DataAccessException, Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE ").append(table).append(" SET ")
		.append("doc_codigo = ?, ")
		.append("doc_tipo = ? ")
		.append("WHERE doc_id = ?");
		
		return (long) getJdbcTemplate().update(sql.toString(),
				aEntity.getCodigo(),
				aEntity.getTipoDocumento().getDescricao(),
				aEntity.getId()
		);
	}

	@Override
	public Long delete(String status, Long id) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Long create(Documento aEntity, Long forId) throws DataAccessException, Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO ").append(table).append(" (")
		.append("doc_codigo, ")
		.append("doc_tipo, ")
		.append("doc_for_id, ")
		.append("doc_tipo_cadastro")
		.append(") VALUES ")
		.append("(?, ?, ?, ?)");
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, aEntity.getCodigo());
				ps.setString(2, aEntity.getTipoDocumento().getDescricao());
				ps.setLong(3, forId);
				ps.setString(4, TipoCadastroEnum.atLiteral(aEntity.getTipoCadastro().getDescricao()).getSymbol());
				
				return ps;
			}
			
		}, keyHolder);
		
		Integer id = (Integer) keyHolder.getKeys().get("doc_id");
		return (Long) id.longValue();
	}
	
	public List<Documento> findByFornecedorId(Long forId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ").append(table)
		.append(" WHERE doc_for_id = ").append(forId);
		
		return getJdbcTemplate().query(sql.toString(), new DocumentoRowMapper());
	}
	
	public Boolean verificarExistenciaDocumento(String tipo, String codigo, Long forId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT CASE ")
			.append("WHEN EXISTS ( ")
				.append("SELECT doc_id ")
				.append("FROM DOCUMENTOS ")
				.append("WHERE doc_tipo = '").append(tipo).append("' ")
				.append("AND doc_codigo = '").append(codigo).append("' ")
				.append("AND doc_for_id <> '").append(forId).append("' ")
			.append(") ")
			.append("THEN TRUE ")
			.append("ELSE FALSE ")
		.append("END");
		
		
		return getJdbcTemplate().queryForObject(sql.toString(), Boolean.class);
	}
	
	class DocumentoRowMapper implements RowMapper<Documento> {

		@Override
		public Documento mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Documento documento = new BeanPropertyRowMapper<>(Documento.class).mapRow(rs, rowNum);
			documento.setId(rs.getLong("doc_id"));
			documento.setCodigo(rs.getString("doc_codigo"));
			
			TipoDocumento tipoDocumento = new BeanPropertyRowMapper<>(TipoDocumento.class).mapRow(rs, rowNum);
			tipoDocumento.setDescricao(rs.getString("doc_tipo"));
			
			documento.setTipoDocumento(tipoDocumento);
			
			TipoCadastro tipoCadastro = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastro.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("doc_tipo_cadastro")).getLiteral());
			
			documento.setTipoCadastro(tipoCadastro);
			
			return documento;
		}
		
	}

}
