package com.ariefwara.micro.x10c.db.mapper;

import java.sql.PreparedStatement;
import java.util.Map;

public class JDBCPreparedStatment {
	
	PreparedStatement ps;

	public JDBCPreparedStatment(PreparedStatement ps) {
		super();
		this.ps = ps;
	}

	public void setParameters(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
