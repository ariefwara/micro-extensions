package com.ariefwara.spring.micro.db.basic.select;

public class Where {

	
	
	private Where() {}
	
	public static Where oneEqOne() {
		return new Where();
	}
	
	public class WhereIf extends Where {
		public Where whenNotNull() { return null; }
	}
	
	public class Operator {
		
		public WhereIf eq(Object to) { return null; }
		public WhereIf lt(Object to) { return null; }
		public WhereIf gt(Object to) { return null; }
		public WhereIf lte(Object to) { return null; }
		public WhereIf gte(Object to) { return null; }
		public WhereIf like(Object to) { return null; }
	
	}
	
	public Operator and(String column) {
		return null;
	}
	
}
