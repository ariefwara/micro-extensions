package com.ariefwara.micro.x10c.peer.response;

@FunctionalInterface
public interface Mapping<O, T> {
	
	public T setup(O origin);
	
}
