package com.ariefwara.spring.micro.db.basic.statement.result;

import java.util.Optional;
import java.util.function.Function;

public class Selection<T> {

	public <R> Optional<R> result(Function<T, R> as){
		return null;
	}
	
	public Optional<T> affected(){
		return null;
	}
	
}
