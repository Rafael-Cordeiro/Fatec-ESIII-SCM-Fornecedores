package com.temperosoft.samplaserver;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlTest {

	@Qualifier("pg-datasource")
	@Autowired
	static DataSource dataSource;

	public static void main(String... args) throws SQLException {
		System.out.println(dataSource);
	}

}
