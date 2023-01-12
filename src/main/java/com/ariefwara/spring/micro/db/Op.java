package com.ariefwara.spring.micro.db;

import com.ariefwara.spring.micro.db.basic.operation.Delete;
import com.ariefwara.spring.micro.db.basic.operation.Insert;
import com.ariefwara.spring.micro.db.basic.operation.Update;

public class Op {
	
	public static Insert INSERT = new Insert();
	public static Update UPDATE = new Update();
	public static Delete DELETE = new Delete();
	
}
