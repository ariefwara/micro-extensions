package com.ariefwara.micro.extensions.peer;

@FunctionalInterface
public interface Request<T> {
	
	public T setup();
	
}