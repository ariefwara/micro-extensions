package com.ariefwara.spring.micro.peer;

import java.util.List;

import com.ariefwara.spring.micro.peer.response.Mapping;
import com.ariefwara.spring.micro.peer.response.list.ItemMapping;

public class Response {

	public <T> T result(Class<T> as){
		return null;
	}
	
	public <O, T> T result(Class<O> origin, Mapping<O, T> as){
		return null;
	}
	
	public <T> List<T> result(Class<? extends List<?>> asList, Class<T> as){
		return null;
	}
	
	public <O, T> List<T> result(Class<? extends List<?>> asList, Class<O> of, ItemMapping<O, T> as){
		return null;
	}
	
}
