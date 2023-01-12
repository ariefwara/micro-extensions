package com.ariefwara.spring.micro;

import org.springframework.stereotype.Component;

import com.ariefwara.spring.micro.db.BasicOperation;
import com.ariefwara.spring.micro.db.Block;
import com.ariefwara.spring.micro.db.basic.query.operation.Affected;
import com.ariefwara.spring.micro.db.basic.query.operation.Preparation;
import com.ariefwara.spring.micro.db.basic.query.select.Condition;
import com.ariefwara.spring.micro.db.basic.query.select.Selected;

@Component
public class Db {

	public <T> Affected<T> execute(BasicOperation operation, Preparation<T> on) {
		return operation.exec(on);
	}
	
	public <T> Affected<T> select(Preparation<T> on) {
		return null;
	}
	
	public <T> Selected<T> select(Class<T> from, Condition where) {
		return null;
	}
	
	public <T> T operate(Class<T> advanceOperation) {
		return null;
	}
	
	public void intact(Block block) {
		
	}
	
}
