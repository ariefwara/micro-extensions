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
import com.ariefwara.micro.ext.db.operation.post.PostOperation;

public class Operation {
	
	Connection conn;
	
	public Operation(Connection c) {
		super();
		this.conn = c;
	}
	
	public <T> Optional<T> select(T object){
		return new Select(conn).exec(object);
	}
	
	public <T> PostOperation<T> insert(T object){
		new Insert(conn).exec(object);
		return new PostOperation<T>(conn, object);
	}
	 
	public <T> PostOperation<T> update(T object){
		new Update(conn).exec(object);
		return new PostOperation<T>(conn, object);
	} 
	
	public <T> PostOperation<T> delete(T object){
		new Delete(conn).exec(object);
		return new PostOperation<T>(conn, object);
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