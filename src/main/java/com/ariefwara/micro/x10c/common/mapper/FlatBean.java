package com.ariefwara.micro.x10c.common.mapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.ariefwara.micro.x10c.common.handler.Entrust;

public class FlatBean {

	protected Object bean;
	
	public FlatBean(Object bean) {
		super();
		this.bean = bean;
	}
	
	public Map<String, Object> asMap(){
		
		Class<?> type = bean.getClass();
		Field[] fields = type.getDeclaredFields();
		
		Map<String, Object> result = new HashMap<>();
		for (Field field : fields) {
			field.setAccessible(true);
			result.put(field.getName(), Entrust.on(() -> field.get(bean)));
		}
		
		return result;
	}

}
