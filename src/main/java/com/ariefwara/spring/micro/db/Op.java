package com.ariefwara.spring.micro.db;

import com.ariefwara.spring.micro.db.basic.query.Delete;
import com.ariefwara.spring.micro.db.basic.query.Insert;
import com.ariefwara.spring.micro.db.basic.query.Select;
import com.ariefwara.spring.micro.db.basic.query.Update;

public class Op {
	
	public static Select SELECT = new Select();
	public static Insert INSERT = new Insert();
	public static Update UPDATE = new Update();
	public static Delete DELETE = new Delete();
	
}
