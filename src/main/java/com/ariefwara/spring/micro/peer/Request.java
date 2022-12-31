package com.ariefwara.spring.micro.peer;

@FunctionalInterface
public interface Request<T> {
	
	public T set();
	
}
