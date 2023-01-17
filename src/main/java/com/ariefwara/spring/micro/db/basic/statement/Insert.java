package com.ariefwara.spring.micro.db.basic.statement;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import com.ariefwara.spring.micro.db.annotation.Entity;
import com.ariefwara.spring.micro.db.basic.Statement;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.internal.lang3.StringUtils;

public class Insert extends Statement {


	protected Handlebars handlebars = new Handlebars();
	{
		handlebars.registerHelper("not-null", new Helper<Object>() {

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

			Map<String, String> fieldMap = extractFieldMap(type);

			StringBuilder sb = new StringBuilder();
			sb.append(String.format("INSERT INTO %s SET (", type.getDeclaredAnnotation(Entity.class).value()));
			for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
				sb.append(String.format("{{#not-null %s}}%s, {{/not-null}}", entry.getValue(), entry.getKey(),
						entry.getValue()));
			}

			sb.append(") VALUES (");

			for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
				sb.append(String.format("{{#not-null %s}}:%s, {{/not-null}}", entry.getValue(), entry.getValue(),
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
