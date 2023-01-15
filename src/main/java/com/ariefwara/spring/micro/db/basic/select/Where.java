package com.ariefwara.spring.micro.db.basic.select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Where {
	
	public class Clause {
		
		String column;
		String operator;
		Object value;
		Boolean whenNotNull = false;
		
	}
	
	List<Clause> clauses;;
	
	public Where(List<Clause> clauses) {
		this.clauses = clauses;
	}

	public static Where oneEqOne() {
		return new Where(new ArrayList<>());
	}
	
	public class WhereIf extends Where {
		
		Clause clause;
		
		public WhereIf(List<Clause> clauses) {
			super(clauses);
			clause = clauses.get(clauses.size() - 1);
		}
		
		public Where whenNotNull() { 
			clause.whenNotNull = true;
			return new Where(clauses);
		}
		
	}
	
	public class Operator {
		
		List<Clause> clauses;
		Clause clause;
		
		public Operator(List<Clause> clauses) {
			this.clauses = clauses;
			clause = clauses.get(clauses.size() - 1);
		}
		
		public WhereIf eq(Object to) { 
			clause.operator = "=";
			clause.value = to;
			return new WhereIf(clauses); 
		}
		
		public WhereIf lt(Object to) { 
			clause.operator = "<";
			clause.value = to;
			return new WhereIf(clauses);
		}
		
		public WhereIf gt(Object to) { 
			clause.operator = ">";
			clause.value = to;
			return new WhereIf(clauses); 
		}
		
		public WhereIf lte(Object to) { 
			clause.operator = "<=";
			clause.value = to;
			return new WhereIf(clauses); 
		}
		
		public WhereIf gte(Object to) { 
			clause.operator = ">=";
			clause.value = to;
			return new WhereIf(clauses); 
		}
		
		public WhereIf like(Object to) { 
			clause.operator = "like";
			clause.value = to;
			return new WhereIf(clauses); 
		}
	
	}
	
	public Operator and(String column) {
		
		Clause clause = new Clause();
		clause.column = column;
		clauses.add(clause);
		
		return new Operator(clauses);
	}
	
	public String buildQuery() {
		
		StringBuilder sb = new StringBuilder("WHERE 1 = 1");
		for (Clause clause : clauses) {
			if (clause.whenNotNull && clause.value == null) continue;
			sb.append(" AND ");
			sb.append(clause.column);
			sb.append(" ");
			sb.append(clause.operator);
			sb.append(" :");
			sb.append(clause.column);
		}
		
		return sb.toString();
	}
	
	public Map<String, Object> getParameters(){
		
		Map<String, Object> parameters = new HashMap<>();
		for (Clause clause : clauses) {
			if (clause.whenNotNull && clause.value == null) continue;
			parameters.put(clause.column, clause.value);
		}
		
		return parameters;
	}
	
}
