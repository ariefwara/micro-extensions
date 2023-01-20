package com.ariefwara.micro.ext.db;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.ariefwara.micro.ext.db.operation.Delete;
import com.ariefwara.micro.ext.db.operation.Insert;
import com.ariefwara.micro.ext.db.operation.Prepared;
import com.ariefwara.micro.ext.db.operation.Select;
import com.ariefwara.micro.ext.db.operation.Update;
import com.ariefwara.micro.ext.db.operation.Where;

public class Operation {
	
	Connection conn;
	
	public Operation(Connection c) {
		super();
		this.conn = c;
	}
	
	public <T> Optional<T> select(T object){
		return  new Select().setConnection(conn).exec(object);
	}
	
	public <T> Optional<T> insert(T object){
		return  new Insert().setConnection(conn).exec(object);
	}
	 
	public <T> Optional<T> update(T object){
		return  new Update().setConnection(conn).exec(object);
	} 
	
	public <T> Optional<T> delete(T object){
		return  new Delete().setConnection(conn).exec(object);
	}
	
	public <T> List<T> select(Class<T> from, Where condition){
		return condition.setConnection(conn).exec(from);
	}
	
	public <T> T prepared(Class<T> target){
		return new Prepared().setConnection(conn).exec(target);
	}

	public Connection getConnection() {
		return conn;
	}
	
}
