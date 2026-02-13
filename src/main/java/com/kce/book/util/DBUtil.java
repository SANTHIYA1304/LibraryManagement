package com.kce.book.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

public static Connection getDBConnection() {
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String user="Santhiya";
	String pass="system";
	
		Connection connection =DriverManager.getConnection(url,user,pass);
		return connection;
		}
	catch(SQLException e) {
			System.out.println(e);
			return null;
		}
	catch(ClassNotFoundException e) {
			System.out.println(e);
			return null;
		}
	
}
}