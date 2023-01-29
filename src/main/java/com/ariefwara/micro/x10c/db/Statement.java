package com.ariefwara.micro.x10c.db;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.util.Map;
import java.util.Optional;

import com.ariefwara.micro.x10c.common.mapper.FlatBean;
import com.ariefwara.micro.x10c.db.mapper.JDBCPreparedStatment;
import com.ariefwara.micro.x10c.db.mapper.JDBCResultSet;
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
			System.out.println(query);
			NamedParameterPreparedStatement ps = new JDBCPreparedStatment(c, query).setParameters(target).getPreparedStatement();
			
			ps.executeUpdate();
			new JDBCResultSet(ps.getGeneratedKeys()).mergeWith(target);
			
			ps.close();
			
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
		
		return null;
	}

	public abstract String buildQuery(Object bean);
	
}
