package com.ariefwara.micro.x10c.util;

import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ariefwara.micro.x10c.db.flag.Column;
import com.ariefwara.micro.x10c.db.flag.Entity;
import com.axiomalaska.jdbc.NamedParameterPreparedStatement;

public class BeanMap {

	static Map<Class<?>, Map<String, String>> fieldMaps = new HashMap<>();

	public static Map<String, String> extractFieldMap(Class<?> type) {

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

	@SuppressWarnings("all")
	public static <T> List<T> resultSetAsList(ResultSet resultSet, Class<?> type) {

		try {

			ResultSetMetaData metaData = resultSet.getMetaData();
			Map<String, String> fieldMap = extractFieldMap(type);

			List<T> result = new ArrayList<>();

			while (resultSet.next()) {

				T bean = (T) type.getDeclaredConstructor().newInstance();
				for (Map.Entry<String, String> entry : fieldMap.entrySet()) {

					String key = entry.getKey();
					String val = entry.getValue();
					try {

						Field field = type.getDeclaredField(val);
						field.setAccessible(true);
						field.set(bean, resultSet.getObject(key));

					} catch (Exception e) {
						// log error
					}

				}

				result.add(bean);

			}

			return result;

		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}

	}
	
	public static void asMap(Object bean) {

		try {
			Class<?> type = bean.getClass();
			Map<String, Object> fieldMap;

			
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}

	}

	public static void namedParameterPreparedStatementSet(NamedParameterPreparedStatement namedParameterPreparedStatement, Object bean) {

		try {
			Class<?> type =bean.getClass();
			Map<String, String> fieldMap = extractFieldMap(type);
			for (Map.Entry<String, String> entry : fieldMap.entrySet()) {

				String val = entry.getValue();
				try {

					Field field = type.getDeclaredField(val);
					field.setAccessible(true);
					namedParameterPreparedStatement.setObject(val, field.get(bean));

				} catch (Exception e) {
					// log error
				}

			}
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}

	}
	
	public static void namedParameterPreparedStatementMap(NamedParameterPreparedStatement namedParameterPreparedStatement, Map<String, Object> map) {

		try {
		
			for (Map.Entry<String, Object> entry : map.entrySet()) {

				String key = entry.getKey();
				Object val = entry.getValue();
				
				try {

					namedParameterPreparedStatement.setObject(key, val);

				} catch (Exception e) {
					// log error
				}

			}
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}

	}

}
