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

import com.temperosoft.scmfornecedores.domain.Produto;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

@Component
public class ProdutoDAO extends AbstractDAO<Produto> {
	
	public ProdutoDAO() {
        table = "PRODUTOS";
        idTable = "pro_id";
        alias = "pro1";
        trigram = "pro";
	}

	@Override
	public List<Produto> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ").append(table);
		return getJdbcTemplate().query(sql.toString(), new ProdutoRowMapper());
	}

	@Override
	public Produto findById(Long id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ")
		.append(table)
		.append(" AS ")
		.append(alias)
		.append(" WHERE ")
		.append(alias)
		.append(".ser_id = ")
		.append(id);
		
		return getJdbcTemplate().queryForObject(sql.toString(), new ProdutoRowMapper());
	}

	@Override
	public Long create(Produto aEntity) throws DataAccessException, Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO ").append(table).append(" (")
		.append("pro_codigo, ")
		.append("pro_descricao, ")
		.append("pro_tipo_cadastro ")
		.append(") VALUES (?, ?, ?)");
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, aEntity.getCodigo());
				ps.setString(2, aEntity.getDescricao());
				ps.setString(3, TipoCadastroEnum.atLiteral(aEntity.getTipoCadastro().getDescricao()).getSymbol());
				
				return ps;
			}
			
		}, keyHolder);
		
		Integer id = (Integer) keyHolder.getKeys().get("pro_id");
		return id.longValue();
	}

	@Override
	public Long update(Produto aEntity) throws DataAccessException, Exception {

		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE ").append(table).append(" SET ")
		.append("pro_codigo = ?, ")
		.append("pro_descricao = ?, ")
		.append("pro_tipo_cadastro = ? ")
		.append("WHERE ").append(idTable).append(" = ?");
		
		return (long) getJdbcTemplate().update(sql.toString(),
				aEntity.getCodigo(),
				aEntity.getDescricao(),
				TipoCadastroEnum.atLiteral(aEntity.getTipoCadastro().getDescricao()).getSymbol(),
				aEntity.getId()
		);
	}
	
	public List<Produto> findProdutosRelatedWithAFornecedor(Long id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ").append(table).append(" ").append(alias)
		.append(" INNER JOIN FORNECEDORES_PRODUTOS fp1")
		.append(" ON fp1.for_pro_pro_id = ").append(alias).append(".pro_id")
		.append(" WHERE fp1.for_pro_for_id = ").append(id);
		
		return getJdbcTemplate().query(sql.toString(), new ProdutoRowMapper());
		
	}
	
	public List<Produto> findByTipoCadastro(TipoCadastro tipoCadastro) throws DataAccessException, Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ").append(table)
		.append(" WHERE pro_tipo_cadastro = '")
		.append(TipoCadastroEnum.atLiteral(tipoCadastro.getDescricao()).getSymbol())
		.append("'");
		
		return getJdbcTemplate().query(sql.toString(), new ProdutoRowMapper());
		
	}
	
	class ProdutoRowMapper implements RowMapper<Produto> {

		@Override
		public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Produto produto = new BeanPropertyRowMapper<>(Produto.class).mapRow(rs, rowNum);
			produto.setId(rs.getLong("pro_id"));
			produto.setCodigo(rs.getString("pro_codigo"));
			produto.setDescricao(rs.getString("pro_descricao"));
			
			TipoCadastro tipoCadastro = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastro.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("pro_tipo_cadastro")).getLiteral());
			
			produto.setTipoCadastro(tipoCadastro);
			
			return produto;
		}
		
	}

}
