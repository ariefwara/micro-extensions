package com.ariefwara.micro.extensions.db.operation.single.record;

import java.sql.Connection;

public class Then<T> {

	T bean;
	Connection c;
	
	public Then(Connection c, T bean) {
		super();
		this.c = c;
		this.bean = bean;
	}

	public T select() {
		return new Select(c).exec(bean);
	}
	
}
