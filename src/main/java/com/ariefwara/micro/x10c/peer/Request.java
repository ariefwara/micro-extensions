package com.ariefwara.micro.x10c.peer;

@FunctionalInterface
public interface Request<T> {
	
	public T setup();
	
}