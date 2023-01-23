package com.ariefwara.micro.x10c.db;

import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import com.ariefwara.micro.x10c.db.flag.Column;
import com.ariefwara.micro.x10c.db.flag.Entity;

public class BeanMap {
	
	static Map<Class<?>, Map<String, String>> fieldMaps = new HashMap<>();

	public static Map<String, String> extractFieldMap(Class<?> type) {
		
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
}
