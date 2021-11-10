package com.temperosoft.scmfornecedores.core.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.temperosoft.scmfornecedores.domain.Pais;
import com.temperosoft.scmfornecedores.domain.enums.TipoCadastroEnum;
import com.temperosoft.scmfornecedores.domain.tipos.TipoCadastro;

@Component
public class FooDAO {
	
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
            tipoCadastro.setDescricao(TipoCadastroEnum.atSymbol(rs.getString("pai_tipo_cadastro")).getLiteral());
            
            pais.setTipoCadastro(tipoCadastro);

            return pais;
        }

    }
}
