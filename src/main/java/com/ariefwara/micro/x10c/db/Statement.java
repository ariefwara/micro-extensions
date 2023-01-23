package com.ariefwara.micro.x10c.db;

import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.ariefwara.micro.x10c.db.flag.Column;
import com.ariefwara.micro.x10c.db.flag.Entity;
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
			
			
			
			ps.setObject(query, ps);
			ps.execute();
			
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
		
		return null;
	}

	public abstract String buildQuery(Object bean);
	
}
