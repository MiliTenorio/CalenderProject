package controller;

import java.sql.*;
import java.util.LinkedList;

import model.DurationEvent;
import model.Event;
import model.HourEvent;
import model.MySQLDatabase;

public class DataBase {
	/*
	 * The following functions will work to return informations from Tables
	*/
	
	/*
	 * About Simple Event
	 */
	public static boolean addEvent(MySQLDatabase mySQLDatabase, Event event) {

        try {        	
        	boolean rowsInserted;

        	rowsInserted = mySQLDatabase.createSimpleEvent(event);
        	
            if (rowsInserted) {
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
	
	public static LinkedList<Event> readAllSimpleEvent(MySQLDatabase mySQLDatabase) {
        try {        	
        	return mySQLDatabase.readAllSimpleEventTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static Event readSimpleEvent(MySQLDatabase mySQLDatabase, int id) {

        try {        	
        	return mySQLDatabase.readSimpleEvent(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/*
	 * About Hour Event
	 */
	public static boolean addEvent(MySQLDatabase mySQLDatabase, HourEvent event) {
		
        try {        	
        	boolean rowsInserted;

        	rowsInserted = mySQLDatabase.createHourEvent(event);
        	
            if (rowsInserted) {
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
	
	public static LinkedList<HourEvent> readAllHourEvent(MySQLDatabase mySQLDatabase) {
        try {        	
        	return mySQLDatabase.readAllHourEventTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static HourEvent readHourEvent(MySQLDatabase mySQLDatabase, int id) {
        try {        	
        	return mySQLDatabase.readHourEventTable(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

	/*
	 * About Duration Event
	 */
	public static boolean addEvent(MySQLDatabase mySQLDatabase, DurationEvent event) {
        try {        	
        	boolean rowsInserted;

        	rowsInserted = mySQLDatabase.createDurationEvent(event);
        	
            if (rowsInserted) {
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



	public static LinkedList<DurationEvent> readAllDurationEvent(MySQLDatabase mySQLDatabase) {
        try {        	
        	return mySQLDatabase.readAllDurationEventTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static DurationEvent readDurationEvent(MySQLDatabase mySQLDatabase, int id) {
        return mySQLDatabase.readDurationEventTable(id);
    }
	
	/*
	 * Others methods
	 */
	

}
