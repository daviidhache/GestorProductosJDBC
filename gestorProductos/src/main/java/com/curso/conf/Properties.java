package com.curso.conf;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Properties {
	private DataSource ds;

	
	public Properties() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/MiDataSource");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public DataSource getDs() {
		return ds;
	}

}
