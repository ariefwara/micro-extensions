package com.ariefwara.micro.extensions.db.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCResultSet {
	
	ResultSet rs;
	
	public JDBCResultSet(ResultSet rs) {
		super();
		this.rs = rs;
	}

	public <T> T mergeWith(T object) {
		return object;
	}

	public <T> List<T> asList(Class<T> from) {
		
		try {
			
			Map<String, String> fieldMapping = EntityBean.fieldMapping(from);
			
			ResultSetMetaData md = rs.getMetaData();
			List<T> result = new ArrayList<>();
			while (rs.next()) {
				
				T row = (T) from.getConstructor().newInstance();
				for (int i = 1; i <= md.getColumnCount(); i++) {
					try {
						Field field = from.getDeclaredField(fieldMapping.get(md.getColumnName(i).toUpperCase()));
						field.setAccessible(true);
						field.set(row, rs.getObject(i));
					} catch (Exception e) { System.out.println(e.getMessage()); }
				}
				result.add(row);
			}
			
			return result;
			
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
		
	}
	
}
