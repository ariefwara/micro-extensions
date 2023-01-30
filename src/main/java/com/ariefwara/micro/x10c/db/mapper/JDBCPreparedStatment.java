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
	String sql;

	public JDBCPreparedStatment(Connection c, String sql) {
		super();
		try {
			this.sql = sql;
			this.ps = NamedParameterPreparedStatement.createNamedParameterPreparedStatement(c, sql);
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
	}

	public JDBCPreparedStatment setParameters(Map<String, Object> parameters) {
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			
			
			try {
				if (val instanceof Date) ps.setDate(key, new java.sql.Date(((Date) val).getTime()));
				else ps.setObject(key, val);
			} catch (IllegalArgumentException e) {
				System.out.println(key + " not found");
			} catch (Exception e) {
				throw new UndeclaredThrowableException(e);
			}
			
			
			
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
