package com.ariefwara.micro.x10c.common.handler;

import java.lang.reflect.UndeclaredThrowableException;

public class Entrust {

	@FunctionalInterface
	public interface Function<T> {
		
		public T execute() throws Exception;
		
	}
	
	@FunctionalInterface
	public interface Procedure {
		
		public void execute() throws Exception;
		
	}
	
	public static <T> T on(Function<T> operation) {
		try {
			return operation.execute();
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
	}
	
	public static void on(Procedure operation) {
		try {
			operation.execute();
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
	}
	
}
