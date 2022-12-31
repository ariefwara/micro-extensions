package com.ariefwara.spring.micro.peer.response.list;

import java.util.List;

@FunctionalInterface
public interface ItemMapping<O, T> {
	
	public T setup(List<O> origin);
	
}
