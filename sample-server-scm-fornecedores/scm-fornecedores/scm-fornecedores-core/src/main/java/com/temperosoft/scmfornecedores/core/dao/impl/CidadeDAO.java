package com.temperosoft.scmfornecedores.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.Cidade;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;
import com.temperosoft.scmfornecedores.domain.utils.EnumUtils;

@Component
public class CidadeDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Cidade> test(String... args) throws Exception {
		String sql = "SELECT * FROM CIDADES";
		return jdbcTemplate.query(sql, new MyRowMapper());
	}
	

	
    class MyRowMapper implements RowMapper <Cidade> {

        @Override
        public Cidade mapRow (ResultSet rs, int rowNum) throws SQLException {

        	Cidade cidade = new BeanPropertyRowMapper<>(Cidade.class).mapRow(rs, rowNum);
            cidade.setId(rs.getLong("cid_id"));
            cidade.setCodigo(rs.getString("cid_codigo"));
            cidade.setDescricao(rs.getString("cid_descricao"));
            
            TipoCadastro tipoCadastro = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
            tipoCadastro.setDescricao(EnumUtils.getTipoCadastroEnum(rs.getString("cid_tipo_cadastro")));
            
            cidade.setTipoCadastro(tipoCadastro);

            return cidade;
        }

    }
}
