package com.temperosoft.scmfornecedores.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.Pais;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;
import com.temperosoft.scmfornecedores.domain.utils.EnumUtils;

@Component
public class FooDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Pais> test(String... args) throws Exception {
		String sql = "SELECT * FROM paises";
		return jdbcTemplate.query(sql, new MyRowMapper());
	}
	

	
    class MyRowMapper implements RowMapper <Pais> {

        @Override
        public Pais mapRow (ResultSet rs, int rowNum) throws SQLException {

        	Pais pais = new BeanPropertyRowMapper<>(Pais.class).mapRow(rs, rowNum);
            pais.setId(rs.getLong("pai_id"));
            pais.setCodigo(rs.getString("pai_codigo"));
            pais.setDescricao(rs.getString("pai_descricao"));
            
            TipoCadastro tipoCadastro = new BeanPropertyRowMapper<>(TipoCadastro.class).mapRow(rs, rowNum);
            tipoCadastro.setDescricao(EnumUtils.getTipoCadastroEnum(rs.getString("pai_tipo_cadastro")));
            
            pais.setTipoCadastro(tipoCadastro);

            return pais;
        }

    }
}
