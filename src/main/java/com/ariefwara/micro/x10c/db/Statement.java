package com.ariefwara.micro.x10c.db;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.util.Optional;

import com.ariefwara.micro.x10c.util.BeanMap;
import com.axiomalaska.jdbc.NamedParameterPreparedStatement;

public abstract class Statement {
	
	Connection c;
	
	public Statement(Connection c) {
		super();
		this.c = c;
	}

	public Statement setConnection(Connection c) {
		this.c = c;
		return this;
	} 

	public <T> Optional<T> exec(T target) {
		
		try {
			
			String query = buildQuery(target);
			NamedParameterPreparedStatement ps = NamedParameterPreparedStatement.createNamedParameterPreparedStatement(c, query);
			BeanMap.namedParameterPreparedStatementSet(ps, target);
			ps.execute();
			ps.close();
			
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
		
		return null;
	}

	public abstract String buildQuery(Object bean);
	
}
