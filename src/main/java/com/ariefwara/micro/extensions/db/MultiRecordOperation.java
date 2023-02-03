package com.ariefwara.micro.extensions.db;

import java.sql.Connection;
import java.util.List;

import com.ariefwara.micro.extensions.db.operation.multi.record.Where;

public abstract class MultiRecordOperation <T> {
	
	protected Connection c;
	protected Class<T> from;
	
	public MultiRecordOperation(Connection c, Class<T> from) {
		super();
		this.c = c;
		this.from = from;
	}

	public MultiRecordOperation<?> setConnection(Connection c) {
		this.c = c;
		return this;
	} 
	
	abstract public <T> List<T> exec(Where condition);
	
}
