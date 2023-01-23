package com.ariefwara.micro.x10c.db;

@FunctionalInterface
public interface Block<T> {
	
	public T intact(Operation op);
	
}
 