package com.ariefwara.spring.micro.db.basic.operation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ariefwara.spring.micro.db.annotation.Entity;
import com.ariefwara.spring.micro.db.basic.Operation;

public class Delete extends Operation {

	static Map<Class<?>, String> queries = new HashMap<>();
	
	public String buildQuery(Object bean) {

		Class<?> type = bean.getClass();
		if (queries.containsKey(type)) return queries.get(type);
		Map<String, String> fieldMap = extractFieldMap(type);

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("UPDATE %s WHERE", type.getDeclaredAnnotation(Entity.class).value()));
		List<String> keys = Arrays.asList(type.getDeclaredAnnotation(Entity.class).keys());
		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			if (!keys.contains(entry.getKey())) continue;
			sb.append(String.format(" AND %s = :%s", entry.getKey(), entry.getValue(),
					entry.getValue()));
		}

		String query = sb.toString().replaceAll(", WHERE AND", " WHERE");
		queries.put(type, query);
		return query;

	}
	
}
