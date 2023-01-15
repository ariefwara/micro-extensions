package com.ariefwara.spring.micro;

import org.springframework.stereotype.Component;

import com.ariefwara.spring.micro.db.Block;
import com.ariefwara.spring.micro.db.Op;
import com.ariefwara.spring.micro.db.basic.operation.process.Affected;
import com.ariefwara.spring.micro.db.basic.operation.process.Target;
import com.ariefwara.spring.micro.db.basic.select.Condition;
import com.ariefwara.spring.micro.db.basic.select.Selected;

@Component
public class Db {
	
	public <T> Affected<T> insert(Target<T> on) {
		return Op.INSERT.exec(on);
	}
	
	public <T> Affected<T> update(Target<T> on) {
		return Op.UPDATE.exec(on);
	}
	
	public <T> Affected<T> delete(Target<T> on) {
		return Op.DELETE.exec(on);
	}
	
	public <T> Affected<T> select(Target<T> on) {
		return Op.SELECT.exec(on);
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
