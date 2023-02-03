package com.ariefwara.micro.extensions.db.operation;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;

import com.ariefwara.micro.extensions.db.mapper.JDBCPreparedStatment;
import com.ariefwara.micro.extensions.db.mapper.JDBCResultSet;
import com.axiomalaska.jdbc.NamedParameterPreparedStatement;

public abstract class SingleRecordOperation {
	
	protected Connection c;
	
	public SingleRecordOperation(Connection c) {
		super();
		this.c = c;
	}

	public SingleRecordOperation setConnection(Connection c) {
		this.c = c;
		return this;
	} 

	public <T> T exec(T target) {
		
		try {
			
			String query = buildQuery(target);
			System.out.println(query);
			NamedParameterPreparedStatement ps = new JDBCPreparedStatment(c, query).setParameters(target).getPreparedStatement();
			
			ps.executeUpdate();
			new JDBCResultSet(ps.getGeneratedKeys()).mergeWith(target);
			
			ps.close();
			
			return target;
			
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
		
	}

	public abstract String buildQuery(Object bean);
	
}
