package com.ariefwara.spring.micro.db.basic.operation.process;

import java.util.Optional;
import java.util.function.Function;

public class Affected<T> {

	public <R> Optional<R> result(Function<T, R> as){
		return null;
	}
	
	public Optional<T> result(){
		return null;
	}
	
}
