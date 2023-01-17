package com.ariefwara.spring.micro.db;

import java.sql.Connection;

import com.ariefwara.spring.micro.db.basic.statement.Delete;
import com.ariefwara.spring.micro.db.basic.statement.Insert;
import com.ariefwara.spring.micro.db.basic.statement.Select;
import com.ariefwara.spring.micro.db.basic.statement.Update;
import com.ariefwara.spring.micro.db.basic.statement.result.Affect;

public class Operation {
	
	Connection conn;
	
	public Operation(Connection c) {
		super();
		this.conn = c;
	}
	
	public <T> Affect<T> select(T target){
		return  new Select().connection(conn).exec(target);
	}
	
	public <T> Affect<T> insert(T target){
		return  new Insert().connection(conn).exec(target);
	}
	
	public <T> Affect<T> update(T target){
		return  new Update().connection(conn).exec(target);
	}
	
	public <T> Affect<T> delete(T target){
		return  new Delete().connection(conn).exec(target);
	}
	
}
