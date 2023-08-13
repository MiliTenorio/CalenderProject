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
	//Until I don't finish my DB in MySQL, I'll use:
	// >> LinkedList to be the Tables
	LinkedList <Event>  events;
	LinkedList <HourEvent> hourEvents;
	LinkedList <DurationEvent> durationEvents;
	// >> To be used as automatic iterator similar in SQL, this is important because of the foreign key
	int id;
	
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
	public boolean editEvent(Event editEvent, Event updateEvent) {
		if(!this.events.contains(editEvent)) {
			System.out.println("Event not found!");
			return false;
		}
		
		int index =	this.events.indexOf(editEvent);
		this.events.set(index, updateEvent);
		
		if(this.events.contains(updateEvent)) {
			System.out.println("Updated event!");
			return true;
		}
		
		System.out.println("Event not updated!");
		return false;
	}
	
	public boolean editEvent(HourEvent editEvent, HourEvent updateEvent) {
		//Event editSimpleEvent = new Event(editEvent.getId(), editEvent.getDateEvent(),editEvent.getNameEvent());
		
		if(/*!this.events.contains(editSimpleEvent) &&*/ !this.hourEvents.contains(editEvent)) {
			System.out.println("Event not found!");
			return false;
		}

		//This part of the code will resolve foreign key actions
		//int indexSimple = this.events.indexOf(editSimpleEvent);
		//Event updateSimpleEvent = new Event(editEvent.getId(), updateEvent.getDateEvent(),updateEvent.getNameEvent());
		//this.events.set(indexSimple, updateSimpleEvent);
		
		int index = this.hourEvents.indexOf(editEvent);
		this.hourEvents.set(index, updateEvent);
		
		if(/*this.events.contains(updateEvent) &&*/ this.hourEvents.contains(updateEvent)) {
			System.out.println("Updated event!");
			return true;
		}
			
		return false;
	}
	
	public boolean editEvent(DurationEvent editEvent, DurationEvent updateEvent) {
		//Event editSimpleEvent = new Event(editEvent.getId(), editEvent.getDateEvent(),editEvent.getNameEvent());
		
		if(/*!this.events.contains(editSimpleEvent) &&*/ !this.durationEvents.contains(editEvent)) {
			System.out.println("Event not found!");
			return false;
		}

		//This part of the code will resolve foreign key actions
		//int indexSimple = this.events.indexOf(editSimpleEvent);
		//Event updateSimpleEvent = new Event(editEvent.getId(), updateEvent.getDateEvent(),updateEvent.getNameEvent());
		//this.events.set(indexSimple, updateSimpleEvent);
		
		int index = this.durationEvents.indexOf(editEvent);
		this.durationEvents.set(index, updateEvent);
		
		if(/*this.events.contains(updateEvent) &&*/ this.durationEvents.contains(updateEvent)) {
			System.out.println("Updated event!");
			return true;
		}
			
		return false;
	}
	
	public Event findSimpleEvent(int id) {
		for(Event event : this.events) {
			if(event.getId()==id) {
				return event;
			}
		}
		return null;
	}
	
	public HourEvent findHourEvent(int id) {
		for(HourEvent event : this.hourEvents) {
			if(event.getId() == id) {
				return event;
			}
		}
		return null;
	}
	
	public DurationEvent findDurationEvent(int id) {
		for(DurationEvent event : this.durationEvents) {
			if(event.getId()==id) {
				return event;
			}
		}
		return null;
	}

	public boolean deleteSimpleEvent(int id, Event event) {
		events.remove(id);
		if(events.contains(event)) {
			return false;
		}
		return true;
	}

	public boolean deleteHourEvent(int id, HourEvent event) {
		hourEvents.remove(id);
		if(hourEvents.contains(event)) {
			return false;
		}
		return true;		
	}

	public boolean deleteDurationEvent(int id, DurationEvent event) {
		durationEvents.remove(id);
		if(durationEvents.contains(event)) {
			return false;
		}
		return true;
	}
	
}
