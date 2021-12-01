package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.core.utils.ValidationUtils;
import com.temperosoft.scmfornecedores.domain.Cidade;
import com.temperosoft.scmfornecedores.domain.Contato;
import com.temperosoft.scmfornecedores.domain.Documento;
import com.temperosoft.scmfornecedores.domain.Endereco;
import com.temperosoft.scmfornecedores.domain.Fornecedor;
import com.temperosoft.scmfornecedores.domain.Produto;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.enums.TipoEnderecoEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;
import com.temperosoft.scmfornecedores.domain.tipos.TipoEndereco;
import com.temperosoft.scmfornecedores.domain.tipos.TipoLogradouro;

@Component
public class FornecedorDAO extends AbstractDAO<Fornecedor> {
	
	private Logger logger = LoggerFactory.getLogger(FornecedorDAO.class);
	
	@Autowired
	private CidadeDAO cidadeDAO;
	
	@Autowired
	private ContatoDAO contatoDAO;
	
	@Autowired
	private DocumentoDAO documentoDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	public FornecedorDAO() {
        table = "FORNECEDORES";
        idTable = "for_id";
        alias = "for1";
        trigram = "for";
	}
	
	@Override
	public List<Fornecedor> findAll() {
		String sql = "SELECT * FROM FORNECEDORES";
		return getJdbcTemplate().query(sql, new FornecedorRowMapper());
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
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, aEntity.getRazaoSocial());
				ps.setString(2, aEntity.getNomeFantasia());
				ps.setString(3, aEntity.getCodigo());
				ps.setString(4, TipoEnderecoEnum.atLiteral(aEntity.getEndereco().getTipoEndereco().getDescricao()).getSymbol());
				ps.setString(5, aEntity.getEndereco().getLogradouro());
				ps.setString(6, aEntity.getEndereco().getTipoLogradouro().getDescricao());
				ps.setString(7, aEntity.getEndereco().getNumero());
				ps.setString(8, aEntity.getEndereco().getBairro());
				ps.setString(9, aEntity.getEndereco().getCep());
				ps.setString(10, aEntity.getEndereco().getComplemento());
				ps.setLong(11, ValidationUtils.returnIdOrNull(aEntity.getEndereco().getCidade()));
				ps.setString(12, TipoCadastroEnum.atLiteral(aEntity.getTipoCadastro().getDescricao()).getSymbol());
				
				return ps;
			}
			
		}, keyHolder);
		
		
		Integer id = (Integer) keyHolder.getKeys().get("for_id");
		return id.longValue();
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
	public Long delete(String status, Long id) throws DataAccessException, Exception {
		return (long) getJdbcTemplate().update(updateStatusQuery(), TipoCadastroEnum.atLiteral(status).getSymbol(), id);
	}
	
	public Long createProdutoRelation(Long forId, Long proId) throws DataAccessException, Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO FORNECEDORES_PRODUTOS ")
		.append("(for_pro_for_id, for_pro_pro_id) ")
		.append("VALUES (?, ?)");
		
		return (long) getJdbcTemplate().update(sql.toString(), forId, proId);
	}
	
	public void deleteProdutoRelations(Long forId) throws DataAccessException, Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM FORNECEDORES_PRODUTOS WHERE for_pro_for_id = ").append(forId);
		
		getJdbcTemplate().update(sql.toString());
		
	}
	
	public List<Fornecedor> findByTipoCadastro(TipoCadastro tipoCadastro) throws DataAccessException, Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ").append(table)
		.append(" WHERE for_tipo_cadastro = '")
		.append(TipoCadastroEnum.atLiteral(tipoCadastro.getDescricao()).getSymbol())
		.append("'");
		
		return getJdbcTemplate().query(sql.toString(), new FornecedorRowMapper());
		
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
			
			try {
				List<Contato> contatos = contatoDAO.findByFornecedorId(fornecedor.getId());
				fornecedor.setContatos(contatos);
			} catch (Exception e) {
				logger.error("Não foi possível consultar contatos relacionados ao fornecedor {}", fornecedor.getCodigo(), e);
			}
			
			try {
				List<Documento> documentos = documentoDAO.findByFornecedorId(fornecedor.getId());
				fornecedor.setDocumentos(documentos);
			} catch (Exception e) {
				logger.error("Não foi possível consultar documentos relacionados ao fornecedor {}", fornecedor.getCodigo(), e);
			}
			
			try {
				List<Produto> produtos = produtoDAO.findProdutosRelatedWithAFornecedor(fornecedor.getId());
				fornecedor.setProdutos(produtos);
			} catch (Exception e) {
				logger.error("Não foi possível consultar produtos relacionados ao fornecedor {}", fornecedor.getCodigo(), e);
			}
			
			TipoCadastro tipoCadastroFor = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
			tipoCadastroFor.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("for_tipo_cadastro")).getLiteral());
			
			fornecedor.setTipoCadastro(tipoCadastroFor);
			
			return fornecedor;
		}
		
	}

}
