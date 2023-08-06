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
	public static boolean addEvent(Connection con, MySQLDatabase mySQLDatabase, Event event) {

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
	
	public static LinkedList<Event> readAllEvents(MySQLDatabase mySQLDatabase) {

        try {        	
        	return mySQLDatabase.readAllSimpleEventTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static Event readEvent(MySQLDatabase mySQLDatabase, int id) {

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

	/*
	 * About Duration Event
	 */
	public static boolean addEvent(Connection con, MySQLDatabase mySQLDatabase, DurationEvent event) {
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
}
