package com.ariefwara.micro.extensions.db.mapper;

import java.sql.ResultSet;
import java.util.List;

public class JDBCResultSet {
	
	ResultSet rs;
	
	public JDBCResultSet(ResultSet rs) {
		super();
		this.rs = rs;
	}

	public <T> T mergeWith(T object) {
		return object;
	}

	public <T> List<T> asList(Class<T> from) {
		
		
		
		return null;
	}
	
}
