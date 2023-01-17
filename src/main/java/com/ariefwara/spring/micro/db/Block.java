package com.ariefwara.spring.micro.db;

@FunctionalInterface
public interface Block<T> {
	
	public T intact(Operation operation);
	
}
