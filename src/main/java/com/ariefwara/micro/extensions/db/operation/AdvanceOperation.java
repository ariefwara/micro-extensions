package com.ariefwara.micro.extensions.db.operation;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Map;

import com.ariefwara.micro.extensions.common.mapper.FlatBean;
import com.ariefwara.micro.extensions.db.operation.advance.Execution;
import com.ariefwara.micro.extensions.db.operation.advance.Query;
import com.ariefwara.micro.extensions.db.operation.advance.Selection;

public class AdvanceOperation {

	Connection c;
	
	public AdvanceOperation(Connection conn) {
		this.c = conn;
	}

	@SuppressWarnings("unchecked")
	public <T> T prepare(Class<T> target) {
		
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

	public AdvanceOperation setConnection(Connection conn) {
		this.c = conn;
		return this;
	}
	
}