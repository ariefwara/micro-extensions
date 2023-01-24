package com.ariefwara.micro.x10c.db.operation;

import java.sql.Connection;
import java.util.Optional;

public class Next<T> {

	T bean;
	Connection c;
	
	public Next(Connection c, T bean) {
		super();
		this.c = c;
		this.bean = bean;
	}

	public Optional<T> select() {
		return new Select(c).exec(bean);
	}
	
}
