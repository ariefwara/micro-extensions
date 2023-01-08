package com.ariefwara.spring.micro.db.basic.query;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Map;

import com.ariefwara.spring.micro.db.BasicOperation;
import com.ariefwara.spring.micro.db.annotation.Entity;
import com.github.jknack.handlebars.Template;

public class SelectFirst extends BasicOperation {

	public Template buildQuery(Class<?> type) {

		Map<String, String> fieldMap = extractFieldMap(type);

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("INSERT INTO %s SET (", type.getDeclaredAnnotation(Entity.class).value()));
		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			sb.append(String.format("{{#not-null %s}}%s, {{/not-null}}", entry.getValue(), entry.getKey(),
					entry.getValue()));
		}

		sb.append(") VALUES (");

		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			sb.append(String.format("{{#not-null %s}}:%s, {{/not-null}}", entry.getValue(), entry.getValue(),
					entry.getValue()));
		}

		sb.append(")");

		try {

			Template template = handlebars.compileInline(sb.toString());
			return template;

		} catch (IOException e) {
			throw new UndeclaredThrowableException(e);
		}

	}

	@Override
	public String finalizeQuery(String query) {
		return query.replaceAll(", \\)", "\\)");
	}

}
