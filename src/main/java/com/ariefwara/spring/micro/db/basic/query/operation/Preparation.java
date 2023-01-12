package com.ariefwara.spring.micro.db.basic.query.operation;

@FunctionalInterface
public interface Preparation<T> {
	
	public T setup();
	
}
