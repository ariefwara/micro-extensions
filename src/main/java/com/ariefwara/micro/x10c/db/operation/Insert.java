package com.ariefwara.micro.x10c.db.operation;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.ariefwara.micro.x10c.common.mapper.FlatBean;
import com.ariefwara.micro.x10c.db.Statement;
import com.ariefwara.micro.x10c.db.flag.Entity;
import com.ariefwara.micro.x10c.db.mapper.EntityBean;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.internal.lang3.StringUtils;

public class Insert extends Statement {


	public Insert(Connection c) {
		super(c);
	}

	protected Handlebars handlebars = new Handlebars();
	{
		handlebars.registerHelper("whenNotNull", new Helper<Object>() {

			@Override
			public CharSequence apply(Object context, Options options) throws IOException {
				if (context != null)
					return options.fn(context);
				return StringUtils.EMPTY;
			}

		});
	}

	static Map<Class<?>, Template> templates = new HashMap<>();

	public String buildQuery(Object bean) {
		Template template;
		Class<?> type = bean.getClass();
		if (!templates.containsKey(type)) {

			Map<String, String> fieldMap = new EntityBean(bean).fieldMapping();
			System.out.println(fieldMap);
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("INSERT INTO %s (", type.getDeclaredAnnotation(Entity.class).value()));
			for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
				sb.append(String.format("{{#whenNotNull %s}}%s, {{/whenNotNull}}", entry.getValue(), entry.getKey(),
						entry.getValue()));
			}

			sb.append(") VALUES (");

			for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
				sb.append(String.format("{{#whenNotNull %s}}:%s, {{/whenNotNull}}", entry.getValue(), entry.getValue(),
						entry.getValue()));
			}

			sb.append(")");
			
			try {

				template = handlebars.compileInline(sb.toString());
				templates.put(type, template);

			} catch (IOException e) {
				throw new UndeclaredThrowableException(e);
			}
			
		} else template = templates.get(type);
		
		try {

			return template.apply(bean).replaceAll(", \\)", "\\)");

		} catch (IOException e) {
			throw new UndeclaredThrowableException(e);
		}
		
	}
	
}
