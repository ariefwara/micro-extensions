package com.ariefwara.spring.micro;

import org.springframework.stereotype.Component;

import com.ariefwara.spring.micro.database.Operation;

@Component
public class Database {

	public <T> Operation set(Class<T> entity) {
		return new Operation();
	}
	
	public <T> T query(Class<T> clasz) {
		return null;
	}
	
}
