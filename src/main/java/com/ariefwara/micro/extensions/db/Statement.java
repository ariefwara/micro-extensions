package com.ariefwara.micro.extensions.db;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.util.Map;
import java.util.Optional;

import com.ariefwara.micro.extensions.common.mapper.FlatBean;
import com.ariefwara.micro.extensions.db.mapper.JDBCPreparedStatment;
import com.ariefwara.micro.extensions.db.mapper.JDBCResultSet;
import com.axiomalaska.jdbc.NamedParameterPreparedStatement;

public abstract class Statement {
	
	protected Connection c;
	
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
			
			return Optional.of(target);
			
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
		
	}

	public abstract String buildQuery(Object bean);
	
}
