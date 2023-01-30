package com.ariefwara.micro.extensions.db.operation;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Map;

import com.ariefwara.micro.extensions.common.mapper.FlatBean;
import com.ariefwara.micro.extensions.db.operation.flag.Query;
import com.ariefwara.micro.extensions.db.operation.type.Execution;
import com.ariefwara.micro.extensions.db.operation.type.Selection;

public class Prepared {

	Connection c;
	
	public Prepared(Connection conn) {
		this.c = conn;
	}

	@SuppressWarnings("unchecked")
	public <T> T exec(Class<T> target) {
		
		return (T) Proxy.newProxyInstance(target.getClassLoader(),
			        new Class[]{target},
			        (proxy, method, methodArgs) -> {
			        	String[] query = method.getDeclaredAnnotation(Query.class).value();
			        	
			        	if (methodArgs[0] instanceof Map) {
							return null;
						} else {
							new FlatBean(methodArgs[0]).asMap();
						}
			        	
			        	method.getReturnType().equals(Selection.class);
			        	method.getReturnType().equals(Execution.class);
			        	return null;
			        });
		
	}

	public Prepared setConnection(Connection conn) {
		this.c = conn;
		return this;
	}
	
}