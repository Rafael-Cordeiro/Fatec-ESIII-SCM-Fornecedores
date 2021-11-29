package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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
		sql.append("SELECT * FROM PRODUTOS ");
		return getJdbcTemplate().query(sql.toString(), new ProdutoRowMapper());
	}

	@Override
	public Produto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(Produto aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Produto aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long delete(String status, Long id) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Produto> findProdutosRelatedWithAFornecedor(Long id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ").append(table).append(" ").append(alias)
		.append(" INNER JOIN FORNECEDORES_PRODUTOS fp1")
		.append(" ON fp1.for_pro_pro_id = ").append(alias).append(".pro_id")
		.append(" WHERE fp1.for_pro_for_id = ").append(id);
		
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
