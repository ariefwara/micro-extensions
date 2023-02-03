package com.ariefwara.micro.extensions.db.operation.single.record;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ariefwara.micro.extensions.db.flag.Entity;
import com.ariefwara.micro.extensions.db.mapper.EntityBean;
import com.ariefwara.micro.extensions.db.mapper.JDBCPreparedStatment;
import com.ariefwara.micro.extensions.db.mapper.JDBCResultSet;
import com.ariefwara.micro.extensions.db.operation.SingleRecordOperation;
import com.axiomalaska.jdbc.NamedParameterPreparedStatement;

public class Select extends SingleRecordOperation {


	public Select(Connection c) {
		super(c);
	}

	static Map<Class<?>, String> queries = new HashMap<>();
	
	public String buildQuery(Object bean) {

		Class<?> type = bean.getClass();
		if (queries.containsKey(type)) return queries.get(type);
		Map<String, String> fieldMap = new EntityBean(bean).fieldMapping();

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("SELECT * FROM %s WHERE", type.getDeclaredAnnotation(Entity.class).value()));
		List<String> keys = Arrays.asList(type.getDeclaredAnnotation(Entity.class).keys());
		for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
			if (!keys.contains(entry.getKey())) continue;
			sb.append(String.format(" AND %s = :%s", entry.getKey(), entry.getValue(),
					entry.getValue()));
		}

		String query = sb.toString().replaceAll(" WHERE AND", " WHERE");
		queries.put(type, query);
		return query;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T exec(T target) {
		
		try {
			
			String query = buildQuery(target);
			System.out.println(query);
			NamedParameterPreparedStatement ps = new JDBCPreparedStatment(c, query).setParameters(target).getPreparedStatement();
			
			List<T> result = (List<T>) new JDBCResultSet(ps.executeQuery()).asList(target.getClass());
			
			ps.close();
			
			if (result.size() == 0) return null;
			return result.get(0);
			
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
		
	}
	
}

