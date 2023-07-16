package model;

import java.sql.*;

public class DataBaseConnection {
	public static String status = "Without Database Connection";
	
	public DataBaseConnection() {
		
	}
	
	public static java.sql.Connection getDataBaseConnection() {
		//Connection
		Connection con = null;

		try {
			//JDBC Driver
			//String driverName = "com.mysql.jdbc.Driver";

			//Class.forName(driverName);

			//Connection configuration
			String serverName = "localhost";
			String myDataBase = "mysql";
			String url = "jdbc:mysql://" + serverName + "/" + myDataBase;
			String username = "userTest";
			String password = "userTest";
			
			con = DriverManager.getConnection(url,username,password);
			
			//Testing connection
			if(con != null) {
				status = "STATUS -> Conected Success";
			}
			else {
				status = "STATUS -> Driver not found";
			}
			
		} catch (/*ClassNotFoundException |*/ SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection Fail!");
		}
		
		return con;	
		
	}
	
	public static String statusConnection() {
		return status;
	}
	
	public boolean closeConnection() {
		try {
			DataBaseConnection.getDataBaseConnection().close();
			//DataBaseConnection.getDataBaseConnection().close();
			return true;
		} catch(SQLException e) {
			return false;
		}
	}
	
	public java.sql.Connection Reconnect(){
		closeConnection();
		return DataBaseConnection.getDataBaseConnection();// DataBaseConnection.getDataBaseConnection();
	}
}
