package com.ariefwara.spring.micro;

import org.springframework.stereotype.Component;

import com.ariefwara.spring.micro.db.BasicOperation;
import com.ariefwara.spring.micro.db.BasicSelect;
import com.ariefwara.spring.micro.db.Block;
import com.ariefwara.spring.micro.db.basic.query.Affected;
import com.ariefwara.spring.micro.db.basic.query.Preparation;
import com.ariefwara.spring.micro.db.basic.query.Selected;

@Component
public class Db {

	public <T> Affected<T> execute(BasicOperation operation, Preparation<T> on) {
		return operation.exec(on);
	}
	
	public <T> Affected<T> execute(BasicSelect operation, Preparation<T> on) {
		return null;
	}
	
	public <T, E> Selected<T> execute(BasicSelect operation, Class<E> entity, Preparation<T> where) {
		return operation.exec(where);
	}
	
	public <T> T operate(Class<T> advanceOperation) {
		return null;
	}
	
	public void intact(Block block) {
		
	}
	
}
