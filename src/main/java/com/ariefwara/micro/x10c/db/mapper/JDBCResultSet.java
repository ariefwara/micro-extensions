package com.ariefwara.micro.x10c.db.mapper;

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
		// TODO Auto-generated method stub
		return null;
	}
	
}
