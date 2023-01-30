package com.ariefwara.micro.extensions.db.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import com.ariefwara.micro.extensions.common.mapper.FlatBean;
import com.ariefwara.micro.extensions.db.flag.Column;
import com.ariefwara.micro.extensions.db.flag.Entity;

public class EntityBean extends FlatBean {

	static Map<Class<?>, Map<String, String>> fieldMaps = new HashMap<>();
	
	public EntityBean(Object bean) {
		super(bean);
	}

	public Map<String, String> fieldMapping() {

		Class<?> type = bean.getClass();
		
		if (fieldMaps.containsKey(type))
			return fieldMaps.get(type);

		if (!type.isAnnotationPresent(Entity.class))
			throw new UndeclaredThrowableException(new Exception("Non Entity Object"));
		Field[] fields = type.getDeclaredFields();

		Map<String, String> fieldMap = new HashMap<>();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(Column.class))
				continue;
			fieldMap.put(field.getAnnotation(Column.class).value(), field.getName());
			field.setAccessible(true);
		}

		fieldMaps.put(type, fieldMap);

		return fieldMap;
	}
	
}
