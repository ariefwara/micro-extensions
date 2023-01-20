package com.ariefwara.micro.ext;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Component;

@Component
public class Parallel {

	public Object[] run(Callable<?>... callables) {
		return new Object[] {};
	}
	


}
