package com.fpoly.java3.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnect {
	private static final String DB_NAME = "java3";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "123";

	public static Statement dbConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;database=" + DB_NAME + ";encrypt=false";
			Connection con = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
			Statement stm = con.createStatement();
			System.out.println(con.getClientInfo().propertyNames());
			return stm;
		} catch (Exception e) {
			return null;
		}
	}
}
