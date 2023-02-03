package com.ariefwara.micro.extensions.db;

import java.sql.Connection;
import java.util.List;

import com.ariefwara.micro.extensions.db.operation.AdvanceOperation;
import com.ariefwara.micro.extensions.db.operation.multi.record.Where;
import com.ariefwara.micro.extensions.db.operation.single.record.Delete;
import com.ariefwara.micro.extensions.db.operation.single.record.Insert;
import com.ariefwara.micro.extensions.db.operation.single.record.Select;
import com.ariefwara.micro.extensions.db.operation.single.record.Then;
import com.ariefwara.micro.extensions.db.operation.single.record.Update;

public class Operation {
	
	Connection conn;
	
	public Operation(Connection c) {
		super();
		this.conn = c;
	}
	
	public <T> T select(T object){
		return new Select(conn).exec(object);
	}
	
	public <T> Then<T> insert(T object){
		return new Then<T>(conn, new Insert(conn).exec(object));
	}
	 
	public <T> Then<T> update(T object){
		return new Then<T>(conn, new Update(conn).exec(object));
	} 
	
	public <T> Then<T> delete(T object){
		return new Then<T>(conn, new Delete(conn).exec(object));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> select(Class<T> from, Where where){
		return new com.ariefwara.micro.extensions.db.operation.multi.record.Select(conn, from).exec(where);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> com.ariefwara.micro.extensions.db.operation.multi.record.Then<T> update(T by, Where where){
		new com.ariefwara.micro.extensions.db.operation.multi.record.Update<>(conn, by.getClass()).exec(where);
		return new com.ariefwara.micro.extensions.db.operation.multi.record.Then(conn, by.getClass(), where);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> com.ariefwara.micro.extensions.db.operation.multi.record.Then<T> delete(Class<T> from, Where where){
		new com.ariefwara.micro.extensions.db.operation.multi.record.Delete<>(conn, from).exec(where);
		return new com.ariefwara.micro.extensions.db.operation.multi.record.Then(conn, from, where);
	}
	
	public <T> T prepared(Class<T> target){
		return new AdvanceOperation(conn).prepare(target);
	}

	public Connection getConnection() {
		return conn;
	}
	
}
