package com.ariefwara.micro.extensions.db.operation.multi.record;

import java.sql.Connection;
import java.util.List;

public class Then<T> {

	Class<T> from;
	Connection c;
	Where where;
	
	
	public Then(Connection c, Class<T> from, Where where) {
		super();
		this.c = c;
		this.from = from;
		this.where = where;
	}

	public List<T> select() {
		return new Select<>(c, from).exec(where);
	}
	
}
