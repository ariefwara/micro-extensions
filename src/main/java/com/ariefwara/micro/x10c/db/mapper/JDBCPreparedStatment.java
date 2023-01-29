package com.ariefwara.micro.x10c.db.mapper;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import com.ariefwara.micro.x10c.common.handler.Entrust;
import com.ariefwara.micro.x10c.common.mapper.FlatBean;
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
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			
			if (val instanceof Date) Entrust.on(() -> ps.setDate(key, new java.sql.Date(((Date) val).getTime())));
			else Entrust.on(() -> ps.setObject(key, val));
			
			
			
		}
		System.out.println(parameters);
		return this;
	}

	public <T> JDBCPreparedStatment setParameters(T target) {
		return setParameters(new FlatBean(target).asMap());
	}

	public NamedParameterPreparedStatement getPreparedStatement() {
		return ps;
	}
	
	
	
	
}
