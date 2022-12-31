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

	public <T> Affected<T> exec(BasicOperation operation, Preparation<T> on) {
		return null;
	}
	
	public <T> Selected<T> exec(BasicSelect operation, Preparation<T> on) {
		return null;
	}
	
	public <T> T op(Class<T> on) {
		return null;
	}
	
	public void intact(Block block) {
		
	}
	
}
