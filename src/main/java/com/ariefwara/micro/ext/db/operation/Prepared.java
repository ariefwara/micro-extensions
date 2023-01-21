package com.ariefwara.micro.ext.db.operation;

import java.sql.Connection;

public class Prepared {

	Connection c;
	
	public Prepared(Connection conn) {
		this.c = conn;
	}

	public <T> T exec(Class<T> target) {
		return null;
	}

	public Prepared setConnection(Connection conn) {
		this.c = conn;
		return this;
	}
	
}