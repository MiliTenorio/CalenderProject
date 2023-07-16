package model;

import java.sql.*;
import java.util.Date;

public class DataBase {
	
	public static String sql;
	private static int status;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		System.out.println("Data Base");

		//Data Base connection
		DataBaseConnection database = new DataBaseConnection();
		//Connection con = database.getDataBaseConnection();
		
		if(database.getDataBaseConnection() != null) {
			System.out.println(database.statusConnection());
		}else {
			database.Reconnect();
		}
		
		/*createTables(con,"events");
		createTables(con,"events_hour");
		createTables(con,"events_duration");*/
	}
	
	public static void createTables(Connection con, String table) {
		
		try {
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("SHOW TABLES LIKE '" + table + "';");
			
			if(resultSet.next()) {
				System.out.println("The table " + table + " exist");
				return;
			}
			
			sql = "CREATE TABLE  " + table + " ";
			
			switch(table){
				//The events table to create a simple event		
				case "events": sql.concat("(id INT AUTO_INCREMENT PRIMARY KEY, "
						+ "date DATE, event_name  VARCHAR(255));");
				//The events_hour table to create a event with initial time associated
				case "events_hour": sql.concat("(id INT AUTO_INCREMENT PRIMARY KEY, "
						+ "event_id INT, time TIME, FOREIGN KEY (event_id) REFERENCES event(id));");
				//The events_duration table to create a event with duration in the time
				case "events_duration": sql.concat("(id INT AUTO_INCREMENT PRIMARY KEY, "
						+ "event_id INT, initial_time TIME, final_time TIME, FOREIGN KEY (event_id) REFERENCES event(id));");
			}
						
			status = stmt.executeUpdate(sql);
			
			if(status > 0) {
				System.out.println("Table Event " + table + " created success!");
			} else {
				System.out.println("Table Event " + table + " fail");
			}

            stmt.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Simple Event
	public static boolean addEvent(Connection con, Event event) {
		
         sql = "INSERT INTO events (date, event_name) VALUES (" + event.getDateEvent() + ", " + event.getNameEvent() + ");";

        try {
        	Statement stmt = con.createStatement();
            
            int rowsInserted = stmt.executeUpdate(sql);
            if (rowsInserted > 0) {
                System.out.println("Event add!");
                return true;
            } else {
                System.out.println("Fail to add event");
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	//Event Hour
	public static boolean addEvent(Connection con, HourEvent event) {
		
		addEvent(con, event);

       try {
       	Statement stmt = con.createStatement();
       	ResultSet result = stmt.executeQuery(sql);
       	
       	if (result.next()) {
       		int foreignkey = findSimpleEventId(con, event);
   			sql = "INSERT INTO events_duration (event_id, time) VALUES (" + foreignkey + " , " + event.getTime() + ");";
   			System.out.println("Event add!");
   			return true;
   		} 
       	else {
       		System.out.println("Fail to add event");
       		return false;
       		}
       } catch (SQLException e) {
    	   e.printStackTrace();
    	   return false;
       }
   }

	//Event Hour
	public static boolean addEvent(Connection con, DurationEvent event) {
		addEvent(con, event);
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			
			if (result.next()) {
	       		int foreignkey = findSimpleEventId(con, event);
	       		sql = "INSERT INTO events_hour (event_id, initial_time, final_time) VALUES (" + foreignkey + " , " + event.getInitialTime().toString() + " , " + event.getFinalTime().toString() +");";
	       		System.out.println("Event add!");
	       		return true;
	       	} else {
	       		System.out.println("Fail to add event");
	       		return false;
	       	}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
	    }
	}
	
	/*
	 * The following functions will work to return informations from Tables
	*/
	
	/*
	 * About Simple Event
	 */
	
	//Find the ID in the Event Table to be used as Foreign Key in other Tables
	public static int findSimpleEventId (Connection con, Event event) {
		
		sql = "SELECT id FROM events WHERE date = '" + event.getDateEvent() + "' AND event_name = '" + event.getNameEvent() + "';";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				return result.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//Getters
	public static Date getEventDate (Connection con, int id) {
		
		sql = "SELECT date FROM events WHERE id " + id + " ;";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				return result.getDate("date");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String getEventName (Connection con, int id) {
		
		sql = "SELECT event_name FROM events WHERE id " + id + " ;";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				return result.getNString("event_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//Setters
	public static boolean setEventDate (Connection con, int id, Date newDate) {
		
		sql = "UPDATE events SET date = '" + newDate + "' WHERE id = \" + id + \" ;";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean setEventName (Connection con, int id, String newEventName) {
		
		sql = "UPDATE events SET event_name = '" + newEventName + "' WHERE id = " + id + " ;";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*
	 * About Event Hour
	 */
	
	//GET
	public static Time getTimeEvent (Connection con, int id) {

		sql = "SELECT time FROM events_hour WHERE id " + id + " ;";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				return result.getTime("time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//SET
	public static boolean setHour (Connection con, int id, Time newTime) {
		
		sql = "UPDATE events_hour SET time = '" + newTime + "' WHERE id = " + id + " ;";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

	/*
	 * About Event Duration
	 */
	
	//GET
	public static Time[] getDurationEvent (Connection con, int id) {
		
		Time[] results = new Time[2];

		sql = "SELECT initial_time, final_time FROM events_duration WHERE id " + id + " ;";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				results[0] = result.getTime("initial_time");
				results[1] = result.getTime("final_time");
				return results;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	//SET
	public static boolean setHour (Connection con, int id, Time[] newTimes) {
		
		sql = "UPDATE events_hour SET inicital_time = '" + newTimes[0] + "', final_time = '" + newTimes[1] +"' WHERE id = " + id + " ;";
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
