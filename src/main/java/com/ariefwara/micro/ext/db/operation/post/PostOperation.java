package com.ariefwara.micro.ext.db.operation.post;

import java.sql.Connection;
import java.util.Optional;

import com.ariefwara.micro.ext.db.operation.Select;

public class PostOperation<T> {

	T bean;
	Connection c;
	
	public PostOperation(Connection c, T bean) {
		super();
		this.c = c;
		this.bean = bean;
	}

	public Optional<T> select() {
		return new Select(c).exec(bean);
	}
	
}
