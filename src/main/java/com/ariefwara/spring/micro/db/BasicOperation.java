package com.ariefwara.spring.micro.db;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import com.ariefwara.spring.micro.db.annotation.Column;
import com.ariefwara.spring.micro.db.annotation.Entity;
import com.ariefwara.spring.micro.db.basic.query.Affected;
import com.ariefwara.spring.micro.db.basic.query.Preparation;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.internal.lang3.StringUtils;

public abstract class BasicOperation {
	
	protected Handlebars handlebars = new Handlebars();
	{
		handlebars.registerHelper("not-null", new Helper<Object>() {

			@Override
			public CharSequence apply(Object context, Options options) throws IOException {
				if (context != null) return options.fn(context);
				return StringUtils.EMPTY;
			}
			
		});
	}
	
	
	public <T> Affected<T> exec(Preparation<T> on) {
		
		T bean = on.setup();
		buildQuery(bean.getClass());
		
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

	public abstract Template buildQuery(Class<?> type);
	
}
