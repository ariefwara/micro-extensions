package com.ariefwara.spring.micro.db.basic.query;

@FunctionalInterface
public interface Condition<T> {
	
	public Where setup(T from);
	
}
