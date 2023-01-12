package com.ariefwara.spring.micro.db.basic.query.select;

@FunctionalInterface
public interface Condition {
	
	public Where setup();
	
}
