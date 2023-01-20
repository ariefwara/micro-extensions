package com.ariefwara.micro.ext.peer.response;

@FunctionalInterface
public interface Mapping<O, T> {
	
	public T setup(O origin);
	
}
