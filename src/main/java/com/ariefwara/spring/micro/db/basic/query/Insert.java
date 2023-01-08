package com.ariefwara.spring.micro.db.basic.query;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import com.ariefwara.spring.micro.db.BasicOperation;
import com.ariefwara.spring.micro.db.annotation.Entity;
import com.github.jknack.handlebars.Template;

public class Insert extends BasicOperation {

	static Map<Class<?>, Template> templates = new HashMap<>();

	public Template buildQuery(Class<?> type) {
		
		if (templates.containsKey(type)) return templates.get(type);
		
		Map<String, String> fieldMap = extractFieldMap(type);
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("INSERT INTO %s SET (", type.getDeclaredAnnotation(Entity.class).value()));
		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			sb.append(String.format("{{#not-null %s}}%s, {{/not-null}}", entry.getValue(), entry.getKey(), entry.getValue()));
		}
		
		sb.append(") VALUES (");
		
		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			sb.append(String.format("{{#not-null %s}}:%s, {{/not-null}}", entry.getValue(), entry.getValue(), entry.getValue()));
		}
		
		sb.append(")");
		
		try {
			
			Template template = handlebars.compileInline(sb.toString());
			templates.put(type, template);
			return template;
			
		} catch (IOException e) {
			throw new UndeclaredThrowableException(e);
		}
		
	}
	
}
