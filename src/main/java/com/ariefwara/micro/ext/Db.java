package com.ariefwara.micro.ext;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.sql.DriverManager;

import com.ariefwara.micro.ext.db.Block;
import com.ariefwara.micro.ext.db.Operation;
 
public class Db {
 
	public static <T> T perform(Block<T> statement) {
		try (Connection c = DriverManager.getConnection("")){
			
			try {
				c.setAutoCommit(false);
				T result = statement.intact(new Operation(c));
				c.commit();
				c.close();
				return result;
				
			} catch (Exception e) {
				c.rollback();
				c.close();
				throw e;
			} 
			
		} catch (Exception e) {
			throw new UndeclaredThrowableException(e);
		}

	}

}
