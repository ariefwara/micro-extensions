package com.ariefwara.micro.x10c;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;

import com.ariefwara.micro.x10c.db.Block;
import com.ariefwara.micro.x10c.db.Operation;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
 
public class Db {
 
	public static HikariDataSource dataSource;
	
	static {
		
		HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/banking-in-general");
        config.setUsername("postgres");
        config.setPassword("rahasia");
        config.addDataSourceProperty("cachePrepStmts" , "true");
        config.addDataSourceProperty("prepStmtCacheSize" , "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
        
    }
	
	public static <T> T perform(Block<T> statement) {
		try (Connection c = dataSource.getConnection()){
			
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
