package com.ariefwara.spring.micro;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Component;

import com.ariefwara.spring.micro.cache.parallel.Prepared;

@Component
public class Parallel {

	public Object[] perform(Callable<?>... callables) {
		return new Object[] {};
	}
	
	public Prepared prepare(Callable<?>... callables) {
		return null;
	}

}
