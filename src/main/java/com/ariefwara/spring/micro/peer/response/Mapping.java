package com.ariefwara.spring.micro.peer.response;

@FunctionalInterface
public interface Mapping<O, T> {
	
	public T setup(O origin);
	
}
