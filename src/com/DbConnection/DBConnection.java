package com.DbConnection;

import java.sql.*;

public class DBConnection {
	
	public static Connection connect() {
			
		Connection cn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Loading Driver
//			System.out.println("Driver Loaded....");
			
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","");
//			System.out.println("Established Connection");
			// defining connection URL
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cn;
	}
	
}
