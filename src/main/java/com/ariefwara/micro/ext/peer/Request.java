package com.ariefwara.micro.ext.peer;

@FunctionalInterface
public interface Request<T> {
	
	public T setup();
	
}