package com.ariefwara.micro.ext.db;

@FunctionalInterface
public interface Block<T> {
	
	public T intact(Operation op);
	
}
 