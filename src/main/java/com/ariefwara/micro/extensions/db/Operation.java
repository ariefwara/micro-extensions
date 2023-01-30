package com.ariefwara.micro.extensions.db;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.ariefwara.micro.extensions.db.operation.Delete;
import com.ariefwara.micro.extensions.db.operation.Insert;
import com.ariefwara.micro.extensions.db.operation.Next;
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
	
	public <T> Next<T> insert(T object){
		return new Next<T>(conn, new Insert(conn).exec(object).get());
	}
	 
	public <T> Next<T> update(T object){
		return new Next<T>(conn, new Update(conn).exec(object).get());
	} 
	
	public <T> Next<T> delete(T object){
		return new Next<T>(conn, new Delete(conn).exec(object).get());
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
