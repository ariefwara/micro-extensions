package com.ariefwara.micro.extensions.db.operation.multi.record;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.ariefwara.micro.extensions.db.MultiRecordOperation;
import com.ariefwara.micro.extensions.db.flag.Entity;
import com.ariefwara.micro.extensions.db.mapper.JDBCPreparedStatment;
import com.ariefwara.micro.extensions.db.mapper.JDBCResultSet;

public class Delete<T> extends MultiRecordOperation<T> {

	public Delete(Connection c, Class<T> from) {
		super(c, from);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> exec(Where condition) {
		try {
			
			StringBuilder sb = new StringBuilder("DELETE FROM ");
			sb.append(from.getAnnotation(Entity.class).value());
			sb.append(condition.buildQuery());

			String query = sb.toString();
			
			PreparedStatement ps = new JDBCPreparedStatment(c, query)
					.setParameters(condition.getParameters())
					.getPreparedStatement();
			
			ResultSet rs = ps.executeQuery();
			List<T> result = new JDBCResultSet(rs).asList(from);
			
			rs.close();
			ps.close();
			
			return result;
			
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}
	}

}
