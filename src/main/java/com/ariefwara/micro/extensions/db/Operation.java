package com.ariefwara.micro.extensions.db;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.ariefwara.micro.extensions.db.operation.Delete;
import com.ariefwara.micro.extensions.db.operation.Insert;
import com.ariefwara.micro.extensions.db.operation.Then;
import com.ariefwara.micro.extensions.db.operation.Prepared;
import com.ariefwara.micro.extensions.db.operation.Select;
import com.ariefwara.micro.extensions.db.operation.Update;
import com.ariefwara.micro.extensions.db.operation.Where;

public class Operation {
	
	Connection conn;
	
	public Operation(Connection c) {
		super();
		this.conn = c;
	}
	
	public <T> Optional<T> select(T object){
		return new Select(conn).exec(object);
	}
	
	public <T> Then<T> insert(T object){
		return new Then<T>(conn, new Insert(conn).exec(object).get());
	}
	 
	public <T> Then<T> update(T object){
		return new Then<T>(conn, new Update(conn).exec(object).get());
	} 
	
	public <T> Then<T> delete(T object){
		return new Then<T>(conn, new Delete(conn).exec(object).get());
	}
	
	public <T> List<T> select(Class<T> from, Where condition){
		return condition.setConnection(conn).select(from);
	}
	
	public <T> List<T> update(Class<T> from, Where condition){
		return condition.setConnection(conn).update(from).select(from);
	}
	
	public <T> List<T> delete(Class<T> from, Where condition){
		return condition.setConnection(conn).delete(from).select(from);
	}
	
	public <T> T prepared(Class<T> target){
		return new Prepared(conn).exec(target);
	}

	public Connection getConnection() {
		return conn;
	}
	
}
