package com.ariefwara.micro.x10c.db.mapper;

import java.sql.ResultSet;

public class JDBCResultSet {
	
	ResultSet rs;
	
	public JDBCResultSet(ResultSet rs) {
		super();
		this.rs = rs;
	}

	public <T> T mergeWith(T object) {
		return object;
	}
	
}
