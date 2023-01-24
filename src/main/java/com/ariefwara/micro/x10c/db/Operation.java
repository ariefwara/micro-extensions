package com.ariefwara.micro.x10c.db;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.ariefwara.micro.x10c.db.operation.Delete;
import com.ariefwara.micro.x10c.db.operation.Insert;
import com.ariefwara.micro.x10c.db.operation.Next;
import com.ariefwara.micro.x10c.db.operation.Prepared;
import com.ariefwara.micro.x10c.db.operation.Select;
import com.ariefwara.micro.x10c.db.operation.Update;
import com.ariefwara.micro.x10c.db.operation.Where;

public class Operation {
	
	Connection conn;
	
	public Operation(Connection c) {
		super();
		this.conn = c;
	}
	
	public <T> Optional<T> select(T object){
		return new Select(conn).exec(object);
	}
	
	public <T> Next<T> insert(T object){
		new Insert(conn).exec(object);
		return new Next<T>(conn, object);
	}
	 
	public <T> Next<T> update(T object){
		new Update(conn).exec(object);
		return new Next<T>(conn, object);
	} 
	
	public <T> Next<T> delete(T object){
		new Delete(conn).exec(object);
		return new Next<T>(conn, object);
	}
	
	public <T> List<T> select(Class<T> from, Where condition){
		return condition.setConnection(conn).exec(from);
	}
	
	public <T> T prepared(Class<T> target){
		return new Prepared(conn).exec(target);
	}

	public Connection getConnection() {
		return conn;
	}
	
}
