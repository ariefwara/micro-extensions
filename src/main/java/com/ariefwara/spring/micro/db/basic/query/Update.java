package com.ariefwara.spring.micro.db.basic.query;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.ariefwara.spring.micro.db.BasicOperation;
import com.ariefwara.spring.micro.db.annotation.Entity;
import com.github.jknack.handlebars.Template;

public class Update extends BasicOperation {

	public Template buildQuery(Class<?> type) {

		Map<String, String> fieldMap = extractFieldMap(type);

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("UPDATE %s SET ", type.getDeclaredAnnotation(Entity.class).value()));
		
		List<String> keys = Arrays.asList(type.getDeclaredAnnotation(Entity.class).keys());
		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			if (keys.contains(entry.getKey())) continue;
			sb.append(String.format("%s = :%s, ", entry.getKey(), entry.getValue(),
					entry.getValue()));
		}

		sb.append("WHERE");

		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			if (!keys.contains(entry.getKey())) continue;
			sb.append(String.format(" AND %s = :%s", entry.getKey(), entry.getValue(),
					entry.getValue()));
		}

		try {

			Template template = handlebars.compileInline(sb.toString());
			return template;

		} catch (IOException e) {
			throw new UndeclaredThrowableException(e);
		}

	}

	@Override
	public String finalizeQuery(String query) {
		return query.replaceAll(", WHERE AND", " WHERE");
	}

}
