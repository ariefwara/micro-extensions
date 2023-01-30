package com.ariefwara.micro.extensions.db;

@FunctionalInterface
public interface Block<T> {
	
	public T intact(Operation op);
	
}
 