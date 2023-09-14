package controller;

import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.Time;
import java.util.LinkedList;
//import java.util.Scanner;

import model.DurationEvent;

//import java.sql.SQLException;
//import java.util.Date;

import model.Event;
import model.HourEvent;
//import model.DataBase;
//import model.DataBaseConnection;
import model.MySQLDatabase;

public class ControllerClass {	
	/*
	 * The Controller Class is the intermediate between the View operation and the all process
	 * Input, Output, Database and Model access others classes using the controller.
	*/
	
	static MySQLDatabase mySQLDatabase;
	
	public ControllerClass() {	
		mySQLDatabase = new MySQLDatabase();
        mySQLDatabase.createDatabase(); // Create the database if it doesn't exist
	}
	
	//Read Event
	
	public LinkedList<Event> getAllSimpleEvent() {
        try (Connection con = mySQLDatabase.getConnection()) {
        	return DataBase.readAllSimpleEvent(mySQLDatabase);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;	
	}

	public LinkedList<HourEvent> getAllHourEvent() {
        try (Connection con = mySQLDatabase.getConnection()) {
        	return DataBase.readAllHourEvent(mySQLDatabase);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;		}

	public LinkedList<DurationEvent> getAllDurationEvent() {
        try (Connection con = mySQLDatabase.getConnection()) {
        	return DataBase.readAllDurationEvent(mySQLDatabase);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;	
	}
	
	//Add Event
	public boolean addEvent(Event newEvent) {		
        try (Connection con = mySQLDatabase.getConnection()) {
        	if(DataBase.addEvent(mySQLDatabase, newEvent)) {
        		return true;
        	}
        	else {
        		return false;
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public boolean addEvent(HourEvent newEvent) {		
        try (Connection con = mySQLDatabase.getConnection()) {
        	if(DataBase.addEvent(mySQLDatabase, newEvent)) {
        		return true;
        	}
        	else {
        		return false;
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public boolean addEvent(DurationEvent newEvent) {		
        try (Connection con = mySQLDatabase.getConnection()) {
        	if(DataBase.addEvent(mySQLDatabase, newEvent)) {
        		return true;
        	}
        	else {
        		return false;
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	/*
	 * Still will be implemented
	 */
	
	//Edit Event 
	public boolean updateEvent( Event updateEvent) {
		 try (Connection con = mySQLDatabase.getConnection()) {
	        	if(DataBase.updateSimpleEvent(mySQLDatabase, updateEvent.getId(), updateEvent)) {
	        		return true;
	        	}
	        	else {
	        		return false;
	        	}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return false;
	}
	
	public boolean updateEvent(HourEvent updateEvent) {
		try (Connection con = mySQLDatabase.getConnection()) {
        	if(DataBase.updateHourEvent(mySQLDatabase, updateEvent.getId(), updateEvent)) {
        		return true;
        	}
        	else {
        		return false;
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	public boolean updateEvent(DurationEvent updateEvent) {
		try (Connection con = mySQLDatabase.getConnection()) {
        	if(DataBase.updateDurationEvent(mySQLDatabase, updateEvent.getId(), updateEvent)) {
        		return true;
        	}
        	else {
        		return false;
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }			
		return false;
	}
	
	/*
	 * Others methods to support
	 */
	
	public Event findSimpleEvent(int id) {
        try (Connection con = mySQLDatabase.getConnection()) {
        	return DataBase.readSimpleEvent(mySQLDatabase, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public HourEvent findHourEvent(int id) {
        try (Connection con = mySQLDatabase.getConnection()) {
        	return DataBase.readHourEvent(mySQLDatabase, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}
		
	public DurationEvent findDurationEvent(int id) {		
        try (Connection con = mySQLDatabase.getConnection()) {
        	return DataBase.readDurationEvent(mySQLDatabase, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	public boolean deleteEvent(int id) {
        try (Connection con = mySQLDatabase.getConnection()) {
        	return DataBase.deleteEvent(mySQLDatabase, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}
	
}
