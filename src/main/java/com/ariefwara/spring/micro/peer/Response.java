package com.ariefwara.spring.micro.peer;

import java.util.List;

public class Response {

	public <T> T result(Class<T> as){
		return null;
	}
	
	public <T> List<T> result(Class<? extends List<?>> asList, Class<T> as){
		return null;
	}
	
}
