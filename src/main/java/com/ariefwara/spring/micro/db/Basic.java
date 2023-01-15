package com.ariefwara.spring.micro.db;

import com.ariefwara.spring.micro.db.basic.operation.Delete;
import com.ariefwara.spring.micro.db.basic.operation.Insert;
import com.ariefwara.spring.micro.db.basic.operation.Select;
import com.ariefwara.spring.micro.db.basic.operation.Update;

public class Basic {
	
	public static Select SELECT = new Select();
	public static Insert INSERT = new Insert();
	public static Update UPDATE = new Update();
	public static Delete DELETE = new Delete();
	
}
