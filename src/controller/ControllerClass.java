package controller;

//import java.sql.Time;
import java.util.LinkedList;
import java.util.Scanner;

import model.DurationEvent;

//import java.sql.SQLException;
//import java.util.Date;

import model.Event;
import model.HourEvent;
//import model.DataBase;
//import model.DataBaseConnection;

public class ControllerClass {

	//private static DataBaseConnection dBConnection;
	//private static Event newEvent;
	
	/*public static void createDB() {
		// TODO Auto-generated method stub
		System.out.println("> Controller <");
		
		try (java.sql.Connection con = DataBaseConnection.getDataBaseConnection()) {
			System.out.println("> Conectou <");*/
			/*DataBase.createTables(con, "events");
			DataBase.createTables(con, "events_hour");
			DataBase.createTables(con, "events_duration");*/
			
		/*} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("> nao rolou <");
		}
		
	}*/
	
	/*public static boolean addEvent(Event newEvent) {
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
	}*/
	
	//Until I don't finish my DB in MySQL, I'll use:
	// >> LinkedList to be the Tables
	LinkedList <Event>  events;
	LinkedList <HourEvent> hourEvents;
	LinkedList <DurationEvent> durationEvents;
	// >> To be used as automatic iterator similar in SQL, this is important because of the foreign key
	int id;
	
	//Return all the simple informations about all the data
	//This is not will occurs in MySQL because of use foreign key
	public LinkedList<Event> getAllEvents(){
		LinkedList<Event> allEvents = new LinkedList<Event>();
		
		allEvents = events;
		
		for(HourEvent event : hourEvents) {
			allEvents.add(event.getSimpleEvent());
		}
		
		for(DurationEvent event : durationEvents) {
			allEvents.add(event.getSimpleEvent());
		}
		
		return allEvents;
	}
	
	public LinkedList<Event> getEvents() {
		return events;
	}

	public void setEvents(LinkedList<Event> events) {
		this.events = events;
	}

	public LinkedList<HourEvent> getHourEvents() {
		return hourEvents;
	}

	public void setHourEvents(LinkedList<HourEvent> hourEvents) {
		this.hourEvents = hourEvents;
	}

	public LinkedList<DurationEvent> getDurationEvents() {
		return durationEvents;
	}

	public void setDurationEvents(LinkedList<DurationEvent> durationEvents) {
		this.durationEvents = durationEvents;
	}

	//Database using LinkedList
	public ControllerClass() {
		this.events = new LinkedList<Event>();
		this.hourEvents = new LinkedList<HourEvent>();
		this.durationEvents = new LinkedList<DurationEvent>();
		
		//Because problems to use Scanner, here add some examples of events:
		events.add(new Event(0,ParseData.convertStringToDate("2023-02-01"),"Event 1"));
		events.add(new Event(1,ParseData.convertStringToDate("2023-04-22"),"Event 2"));
		hourEvents.add(new HourEvent(2,ParseData.convertStringToDate("2023-02-01"),"Event 3","9:10"));
		hourEvents.add(new HourEvent(3,ParseData.convertStringToDate("2023-07-13"),"Event 4","15:30"));
		durationEvents.add(new DurationEvent(4,ParseData.convertStringToDate("2023-10-01"),"Event 5","19:00","20:00"));
		durationEvents.add(new DurationEvent(5,ParseData.convertStringToDate("2023-09-12"),"Event 6","18:30","19:45"));
		
		id = 6;
	}
	
	//Input informations to do actions
	public Event inputSimpleEventInformation(Scanner scan) {
		this.id++;
		Event simpleEvent = new Event(this.id,InputData.inputDate(scan), InputData.inputName(scan));
		
		return simpleEvent;
	}
	
	public HourEvent inputHourEventInformation(Scanner scan, Event simpleEvent) {
		this.id++;
		HourEvent hourEvent = new HourEvent(simpleEvent.getId(),simpleEvent.getDateEvent(),simpleEvent.getNameEvent(),InputData.inputHour(scan));
		
		return hourEvent;
	}
	
	public DurationEvent inputDurationEventInformation(Scanner scan, Event simpleEvent) {
		this.id++;
		DurationEvent durationEvent = new DurationEvent(simpleEvent.getId(),simpleEvent.getDateEvent(),simpleEvent.getNameEvent(),InputData.inputHour(scan), InputData.inputHour(scan));
		
		return durationEvent;
	}
	
	//Add Event
	public boolean addEvent(Event newEvent) {
		this.events.add(newEvent);
		if(events.contains(newEvent)) {
			System.out.println("Event add!");
			return true;
		}
		
		System.out.println("Event not add");
		return false;
	}
	
	public boolean addEvent(HourEvent newEvent) {
		//This part of the code will resolve foreign key actions
		//Event simpleEvent = new Event(newEvent.getId(), newEvent.getDateEvent(),newEvent.getNameEvent());
		//this.events.add(simpleEvent);
		this.hourEvents.add(newEvent);
		
		if(/*events.contains(simpleEvent) &&*/ this.hourEvents.contains(newEvent)) {
			System.out.println("Event add!");
			return true;
		}
		
		System.out.println("Event not add");
		return false;
	}
	
	public boolean addEvent(DurationEvent newEvent) {
		//This part of the code will resolve foreign key actions
		//Event simpleEvent = new Event(newEvent.getId(), newEvent.getDateEvent(),newEvent.getNameEvent());
		//this.events.add(simpleEvent);
		this.durationEvents.add(newEvent);
		
		if(/*events.contains(simpleEvent) && */this.durationEvents.contains(newEvent)) {
			System.out.println("Event add!");
			return true;
		}
		
		System.out.println("Event not add");
		return false;
	}
	
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
