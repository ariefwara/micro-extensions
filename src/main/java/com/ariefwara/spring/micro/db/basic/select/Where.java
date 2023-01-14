package com.ariefwara.spring.micro.db.basic.select;

import java.util.HashMap;
import java.util.Map;

public class Where {
	
	StringBuilder completeClause;
	StringBuilder clause;
	Map<String, Object> args;
	
	public Where(StringBuilder completeClause, StringBuilder clause, Map<String, Object> args) {
		super();
		this.completeClause = completeClause;
		this.clause = clause;
		this.args = args;
	}

	public static Where oneEqOne() {
		return new Where(new StringBuilder("WHERE 1 = 1"), new StringBuilder(), new HashMap<>());
	}
	
	public class WhereIf extends Where {
		
		public WhereIf(StringBuilder completeClause, StringBuilder clause, Map<String, Object> args) {
			super(completeClause, clause, args);
		}
		
		public Where whenNotNull() { 
			return new Where(completeClause, clause, args);
		}
		
	}
	
	public class Operator {
		
		StringBuilder completeClause;
		StringBuilder clause;
		Map<String, Object> args;
		
		public Operator(StringBuilder completeClause, StringBuilder clause, Map<String, Object> args) {
			this.completeClause = completeClause;
			this.clause = clause;
			this.args = args;
		}
		
		public WhereIf eq(Object to) { 
			clause.append(" = ");
			return new WhereIf(completeClause, clause, args); 
		}
		
		public WhereIf lt(Object to) { 
			clause.append(" < ");
			return new WhereIf(completeClause, clause, args);
		}
		
		public WhereIf gt(Object to) { 
			clause.append(" > ");
			return new WhereIf(completeClause, clause, args); 
		}
		
		public WhereIf lte(Object to) { 
			clause.append(" <= ");
			return new WhereIf(completeClause, clause, args); 
		}
		
		public WhereIf gte(Object to) { 
			clause.append(" >= ");
			return new WhereIf(completeClause, clause, args); 
		}
		
		public WhereIf like(Object to) { 
			clause.append(" like ");
			return new WhereIf(completeClause, clause, args); 
		}
	
	}
	
	public Operator and(String column) {
		
		StringBuilder clause = new StringBuilder(" AND ");
		clause.append(column);
		args.put(column, "xcc");
		
		return new Operator(completeClause, clause, args);
	}
	
}
