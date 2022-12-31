package com.ariefwara.spring.micro.db.annotation;

public @interface Query {
	String[] value() default "";
}
