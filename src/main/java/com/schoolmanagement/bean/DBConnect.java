package com.schoolmanagement.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	private static String jdbcURL = "jdbc:mysql://localhost:3306/oop-project";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "ransilu5718";
	private static String jdbcDriver = "com.mysql.jdbc.Driver";
	
	
	
	private static DBConnect instance;
	
	private static Connection connection;
	
	private DBConnect() {
		//System.out.println("DBConnect instance created!");
	}
	
	public static DBConnect getInstance() {
		if(instance == null) {
			synchronized(DBConnect.class) {
				if(instance == null) {
					instance = new DBConnect();
				}
			}
		}
		return instance;
	}
	
	
	
	
	public Connection getConnection() {
		if (connection == null) {
			

		try {
			Class.forName(jdbcDriver);
			
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			//System.out.println("Connected to the database successfully!");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		}
		return connection;
		
	}

}

