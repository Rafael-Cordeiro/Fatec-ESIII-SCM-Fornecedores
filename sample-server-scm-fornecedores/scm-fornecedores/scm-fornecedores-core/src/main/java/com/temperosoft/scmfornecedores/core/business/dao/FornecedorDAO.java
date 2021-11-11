package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.core.utils.ValidationUtils;
import com.temperosoft.scmfornecedores.domain.Cidade;
import com.temperosoft.scmfornecedores.domain.Endereco;
import com.temperosoft.scmfornecedores.domain.Fornecedor;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.enums.TipoEnderecoEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;
import com.temperosoft.scmfornecedores.domain.tipos.TipoEndereco;
import com.temperosoft.scmfornecedores.domain.tipos.TipoLogradouro;

@Component
public class FornecedorDAO extends AbstractDAO<Fornecedor> {
	
	@Autowired
	private CidadeDAO cidadeDAO;
	
	public FornecedorDAO() {
        table = "FORNECEDORES";
        idTable = "for_id";
        alias = "for1";
	}

	@Override
	public Long create(Fornecedor aEntity) throws DataAccessException, Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO ").append(table).append(" (")
		.append("for_razao_social, ")
		.append("for_nome_fantasia, ")
		.append("for_codigo, ")
		.append("for_tipo_endereco, ")
		.append("for_logradouro, ")
		.append("for_tipo_logradouro, ")
		.append("for_numero, ")
		.append("for_bairro, ")
		.append("for_cep, ")
		.append("for_complemento, ")
		.append("for_cid_id, ")
		.append("for_tipo_cadastro")
		.append(") VALUES ")
		.append("(?,?,?,?,?,?,?,?,?,?,?,?)");
		
		return (long) getJdbcTemplate().update(sql.toString(),
				aEntity.getRazaoSocial(),
				aEntity.getNomeFantasia(),
				aEntity.getCodigo(),
				TipoEnderecoEnum.atLiteral(aEntity.getEndereco().getTipoEndereco().getDescricao()).getSymbol(),
				aEntity.getEndereco().getLogradouro(),
				aEntity.getEndereco().getTipoLogradouro().getDescricao(),
				aEntity.getEndereco().getNumero(),
				aEntity.getEndereco().getBairro(),
				aEntity.getEndereco().getCep(),
				aEntity.getEndereco().getComplemento(),
				ValidationUtils.returnIdOrNull(aEntity.getEndereco().getCidade()),
				TipoCadastroEnum.atLiteral(aEntity.getTipoCadastro().getDescricao()).getSymbol()
				);
	}

	@Override
	public Long update(Fornecedor aEntity) throws DataAccessException, Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE ").append(table).append(" SET ")
		.append("for_razao_social = ?, ")
		.append("for_nome_fantasia = ?, ")
		.append("for_codigo = ?, ")
		.append("for_tipo_endereco = ?, ")
		.append("for_logradouro = ?, ")
		.append("for_tipo_logradouro = ?, ")
		.append("for_numero = ?, ")
		.append("for_bairro = ?, ")
		.append("for_cep = ?, ")
		.append("for_complemento = ?, ")
		.append("for_cid_id = ?, ")
		.append("for_tipo_cadastro = ? ")
		.append("WHERE ").append(idTable).append(" = ?");
		
		return (long) getJdbcTemplate().update(sql.toString(),
				aEntity.getRazaoSocial(),
				aEntity.getNomeFantasia(),
				aEntity.getCodigo(),
				TipoEnderecoEnum.atLiteral(aEntity.getEndereco().getTipoEndereco().getDescricao()).getSymbol(),
				aEntity.getEndereco().getLogradouro(),
				aEntity.getEndereco().getTipoLogradouro().getDescricao(),
				aEntity.getEndereco().getNumero(),
				aEntity.getEndereco().getBairro(),
				aEntity.getEndereco().getCep(),
				aEntity.getEndereco().getComplemento(),
				ValidationUtils.returnIdOrNull(aEntity.getEndereco().getCidade()),
				TipoCadastroEnum.atLiteral(aEntity.getTipoCadastro().getDescricao()).getSymbol(),
				aEntity.getId()
		);
	}
	
	@Override
	public Fornecedor findById(Long id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ")
		.append(table)
		.append(" AS ")
		.append(alias)
		.append(" WHERE ")
		.append(alias)
		.append(".for_id = ")
		.append(id);
		
		return getJdbcTemplate().queryForObject(sql.toString(), new FornecedorRowMapper());
	}

	@Override
	public List<Fornecedor> findAll() {
		String sql = "SELECT * FROM FORNECEDORES";
		return getJdbcTemplate().query(sql, new FornecedorRowMapper());
	}
	
	class FornecedorRowMapper implements RowMapper<Fornecedor> {

		@Override
		public Fornecedor mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Fornecedor fornecedor = new BeanPropertyRowMapper<>(Fornecedor.class).mapRow(rs, rowNum);
			fornecedor.setId(rs.getLong("for_id"));
			fornecedor.setRazaoSocial(rs.getString("for_razao_social"));
			fornecedor.setNomeFantasia(rs.getString("for_nome_fantasia"));
			fornecedor.setCodigo(rs.getString("for_codigo"));
		
			Endereco endereco = new BeanPropertyRowMapper<>(Endereco.class).mapRow(rs, rowNum);

			TipoEndereco tipoEndereco = new BeanPropertyRowMapper<>(TipoEndereco.class).mapRow(rs, rowNum);
			tipoEndereco.setDescricao(TipoEnderecoEnum.atSymbol(rs.getString("for_tipo_endereco")).getLiteral());

			endereco.setTipoEndereco(tipoEndereco);
			endereco.setLogradouro(rs.getString("for_logradouro"));
			
			TipoLogradouro tipoLogradouro = new BeanPropertyRowMapper<>(TipoLogradouro.class).mapRow(rs, rowNum);
			tipoLogradouro.setDescricao(rs.getString("for_tipo_logradouro"));
			
			endereco.setTipoLogradouro(tipoLogradouro);
			endereco.setNumero(rs.getString("for_numero"));
			endereco.setBairro(rs.getString("for_bairro"));
			endereco.setCep(rs.getString("for_cep"));
			endereco.setComplemento(rs.getString("for_complemento"));
			
			Cidade cidade = cidadeDAO.findById(rs.getLong("for_cid_id"));
			
			endereco.setCidade(cidade);

			fornecedor.setEndereco(endereco);
			
			TipoCadastro tipoCadastroFor = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastroFor.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("for_tipo_cadastro")).getLiteral());
			
			fornecedor.setTipoCadastro(tipoCadastroFor);
			
			return fornecedor;
		}
		
	}
	
/*
	class FornecedorRowMapper implements RowMapper<Fornecedor> {

		@Override
		public Fornecedor mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Fornecedor fornecedor = new BeanPropertyRowMapper<>(Fornecedor.class).mapRow(rs, rowNum);
			fornecedor.setId(rs.getLong("for_id"));
			fornecedor.setRazaoSocial(rs.getString("for_razao_social"));
			fornecedor.setNomeFantasia(rs.getString("for_nome_fantasia"));
			fornecedor.setCodigo(rs.getString("for_codigo"));
		
			Endereco endereco = new BeanPropertyRowMapper<>(Endereco.class).mapRow(rs, rowNum);

			TipoEndereco tipoEndereco = new BeanPropertyRowMapper<>(TipoEndereco.class).mapRow(rs, rowNum);
			tipoEndereco.setDescricao(EnumUtils.getTipoEnderecoEnum(rs.getString("for_tipo_endereco")));

			endereco.setTipoEndereco(tipoEndereco);
			endereco.setLogradouro(rs.getString("for_logradouro"));
			
			TipoLogradouro tipoLogradouro = new BeanPropertyRowMapper<>(TipoLogradouro.class).mapRow(rs, rowNum);
			tipoLogradouro.setDescricao(rs.getString("for_tipo_logradouro"));
			
			endereco.setTipoLogradouro(tipoLogradouro);
			endereco.setNumero(rs.getString("for_numero"));
			endereco.setBairro(rs.getString("for_bairro"));
			endereco.setCep(rs.getString("for_cep"));
			endereco.setComplemento(rs.getString("for_complemento"));
			
			Cidade cidade = new BeanPropertyRowMapper<>(Cidade.class).mapRow(rs, rowNum);
			cidade.setId(rs.getLong("cid_id"));
			cidade.setCodigo(rs.getString("cid_codigo"));
			cidade.setDescricao(rs.getString("cid_descricao"));
			
			TipoCadastro tipoCadastroCid = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastroCid.setDescricao(EnumUtils.getTipoCadastroEnum(rs.getString("cid_tipo_cadastro")));
			
			cidade.setTipoCadastro(tipoCadastroCid);
			
			Estado estado = new BeanPropertyRowMapper<>(Estado.class).mapRow(rs, rowNum);
			estado.setId(rs.getLong("est_id"));
			estado.setCodigo(rs.getString("est_codigo"));
			estado.setDescricao(rs.getString("est_descricao"));
			
			TipoCadastro tipoCadastroEst = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastroEst.setDescricao(EnumUtils.getTipoCadastroEnum(rs.getString("est_tipo_cadastro")));
			
			estado.setTipoCadastro(tipoCadastroEst);
			
			Pais pais = new BeanPropertyRowMapper<>(Pais.class).mapRow(rs, rowNum);
			pais.setId(rs.getLong("pai_id"));
			pais.setCodigo(rs.getString("pai_codigo"));
			pais.setDescricao(rs.getString("pai_descricao"));
			
			TipoCadastro tipoCadastroPai = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastroPai.setDescricao(EnumUtils.getTipoCadastroEnum(rs.getString("pai_tipo_cadastro")));
			
			pais.setTipoCadastro(tipoCadastroPai);
			
			estado.setPais(pais);
			
			cidade.setEstado(estado);
			
			endereco.setCidade(cidade);

			fornecedor.setEndereco(endereco);
			
			TipoCadastro tipoCadastroFor = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastroFor.setDescricao(EnumUtils.getTipoCadastroEnum(rs.getString("for_tipo_cadastro")));
			
			fornecedor.setTipoCadastro(tipoCadastroFor);
			
			return fornecedor;
		}
		
	}
*/	

}
