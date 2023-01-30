package com.ariefwara.micro.extensions.peer.response;

@FunctionalInterface
public interface Mapping<O, T> {
	
	public T setup(O origin);
	
}
