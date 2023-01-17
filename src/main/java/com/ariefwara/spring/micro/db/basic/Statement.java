package com.ariefwara.spring.micro.db.basic;

import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.ariefwara.spring.micro.db.annotation.Column;
import com.ariefwara.spring.micro.db.annotation.Entity;
import com.ariefwara.spring.micro.db.basic.statement.result.Affect;

public abstract class Statement {
	
	Connection c;
	
	
	public Statement connection(Connection c) {
		this.c = c;
		return this;
	}

	public <T> Affect<T> exec(T target) {
		
		
		String query = buildQuery(target);
		System.out.println(query);
		
		return null;
	}

	static Map<Class<?>, Map<String, String>> fieldMaps = new HashMap<>();

	protected Map<String, String> extractFieldMap(Class<?> type) {
		
		if (fieldMaps.containsKey(type)) return fieldMaps.get(type);
		
		if (!type.isAnnotationPresent(Entity.class)) throw new UndeclaredThrowableException(new Exception("Non Entity Object"));
		Field[] fields = type.getDeclaredFields();
		
		Map<String, String> fieldMap = new HashMap<>();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(Column.class)) continue;
			fieldMap.put(field.getAnnotation(Column.class).value(), field.getName());
			field.setAccessible(true);
		}
		
		fieldMaps.put(type, fieldMap);
		
		return fieldMap;
	}

	public abstract String buildQuery(Object bean);
	
}
