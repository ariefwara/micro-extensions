package com.ariefwara.spring.micro.db.basic.operation.process;

@FunctionalInterface
public interface Target<T> {
	
	public T setup();
	
}
