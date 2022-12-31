package com.ariefwara.spring.micro.db;

import com.ariefwara.spring.micro.db.basic.query.Delete;
import com.ariefwara.spring.micro.db.basic.query.Insert;
import com.ariefwara.spring.micro.db.basic.query.Select;
import com.ariefwara.spring.micro.db.basic.query.SelectFirst;
import com.ariefwara.spring.micro.db.basic.query.Update;

public class Op {
	
	public static CustomQuery CUSTOM_QUERY = new CustomQuery();
	public static Select SELECT = new Select();
	public static SelectFirst SELECT_FIRST = new SelectFirst();
	public static Insert INSERT = new Insert();
	public static Update UPDATE = new Update();
	public static Delete DELETE = new Delete();
	
}
