package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.Fornecivel;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.enums.TipoFornecimentoEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;
import com.temperosoft.scmfornecedores.domain.tipos.TipoFornecimento;

@Component
public class FornecivelDAO extends AbstractDAO<Fornecivel> {

	@Override
	public List<Fornecivel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Fornecivel> findAll(TipoFornecimento tipo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM FORNECIVEIS ")
		.append("WHERE fnv_tipo = '").append(TipoFornecimentoEnum.atLiteral(tipo.getDescricao()).getSymbol()).append("'");
		return getJdbcTemplate().query(sql.toString(), new FornecivelRowMapper());
	}

	@Override
	public Fornecivel findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(Fornecivel aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Fornecivel aEntity) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long delete(String status, Long id) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	class FornecivelRowMapper implements RowMapper<Fornecivel> {

		@Override
		public Fornecivel mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Fornecivel fornecivel = new BeanPropertyRowMapper<>(Fornecivel.class).mapRow(rs, rowNum);
			fornecivel.setId(rs.getLong("fnv_id"));
			fornecivel.setCodigo(rs.getString("fnv_codigo"));
			fornecivel.setDescricao(rs.getString("fnv_descricao"));
			
			TipoFornecimento tipoFornecimento = new BeanPropertyRowMapper<>(TipoFornecimento.class).mapRow(rs, rowNum);
			tipoFornecimento.setDescricao(TipoFornecimentoEnum.atSymbol(rs.getString("fnv_tipo")).getLiteral());
			
			fornecivel.setTipoFornecimento(tipoFornecimento);
			
			TipoCadastro tipoCadastro = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastro.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("fnv_tipo_cadastro")).getLiteral());
			
			fornecivel.setTipoCadastro(tipoCadastro);
			
			return fornecivel;
		}
		
	}

}
