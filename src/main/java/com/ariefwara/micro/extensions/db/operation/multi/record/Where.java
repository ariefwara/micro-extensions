package com.ariefwara.micro.extensions.db.operation.multi.record;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.CaseUtils;

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
			if (clause.whenNotNull && clause.value == null)
				continue;
			sb.append(" AND ");
			sb.append(clause.column);
			sb.append(" ");
			sb.append(clause.operator);
			sb.append(" :");
			sb.append(CaseUtils.toCamelCase(clause.column, false, new char[] { '_' }));
			sb.append(clause.operator.contains("<") ? "_to" : "");
			sb.append(clause.operator.contains(">") ? "_from" : "");
		}

		return sb.toString();
	}

	public Map<String, Object> getParameters() {

		Map<String, Object> parameters = new HashMap<>();
		for (Clause clause : clauses) {
			if (clause.whenNotNull && clause.value == null)
				continue;
			String var = CaseUtils.toCamelCase(clause.column, false, new char[] { '_' });
			var = var.concat(clause.operator.contains("<") ? "_to" : "");
			var = var.concat(clause.operator.contains(">") ? "_from" : "");
			if (parameters.containsKey(var))
				throw new UndeclaredThrowableException(
						new Exception("Duplicate/simmiliar comparation for ".concat(clause.column)));
			parameters.put(var, clause.value);
		}

		return parameters;
	}

}
