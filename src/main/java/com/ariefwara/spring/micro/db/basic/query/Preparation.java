package com.ariefwara.spring.micro.db.basic.query;

@FunctionalInterface
public interface Preparation<T> {
	
	public T setup();
	
}
