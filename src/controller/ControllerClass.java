package controller;

import java.sql.SQLException;
//import java.util.Date;

import model.Event;
import model.DataBase;
import model.DataBaseConnection;

public class ControllerClass {

	//private static DataBaseConnection dBConnection;
	//private static Event newEvent;
	
	public static void createDB() {
		// TODO Auto-generated method stub
		System.out.println("> Controller <");
		
		try (java.sql.Connection con = DataBaseConnection.getDataBaseConnection()) {
			System.out.println("> Conectou <");
			/*DataBase.createTables(con, "events");
			DataBase.createTables(con, "events_hour");
			DataBase.createTables(con, "events_duration");*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("> nao rolou <");
		}
		
	}
	
	public static boolean addEvent(Event newEvent) {
		boolean add;
		try (java.sql.Connection con = DataBaseConnection.getDataBaseConnection()) {
						
			add = DataBase.addEvent(con, newEvent);
			
			if(add == true) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
