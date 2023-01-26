package com.ariefwara.micro.x10c.db.mapper;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.util.Map;

import com.axiomalaska.jdbc.NamedParameterPreparedStatement;

public class JDBCPreparedStatment {
	
	NamedParameterPreparedStatement ps;

	public JDBCPreparedStatment(Connection c, String sql) {
		super();
		try {
			this.ps = NamedParameterPreparedStatement.createNamedParameterPreparedStatement(c, sql);
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
	}

	public JDBCPreparedStatment setParameters(Map<String, Object> parameters) {
		return this;
	}

	public <T> JDBCPreparedStatment setParameters(T target) {
		return this;
	}

	public NamedParameterPreparedStatement getPreparedStatement() {
		return ps;
	}
	
	
	
	
}
