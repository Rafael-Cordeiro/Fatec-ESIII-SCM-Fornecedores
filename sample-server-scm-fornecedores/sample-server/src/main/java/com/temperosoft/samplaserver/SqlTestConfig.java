package com.temperosoft.samplaserver;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SqlTestConfig {

	@Bean(name="pg-datasource")
	@Primary
	@ConfigurationProperties(prefix="spring.pg-datasource")
	DataSource dataSource() {
		return DataSourceBuilder.create().build();
		
	}
}
