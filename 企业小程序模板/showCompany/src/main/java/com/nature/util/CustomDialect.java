package com.nature.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;

public class CustomDialect extends MySQL5Dialect {

	public CustomDialect() {
		super();
		registerHibernateType(-1, Hibernate.STRING.getName());  
	}

	
}
