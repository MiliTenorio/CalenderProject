package model;

import java.util.Date;

public class Event {
	int id;
	Date dateEvent; //Can be only MM-DD-YYYY or MM-DD-YYYY HH:MM or MM-DD-YYYY HH:MM - HH:MM
	String nameEvent;
	
	public Event(int id, Date date, String name) {
		this.id = id;
		this.dateEvent = date;
		this.nameEvent = name;
	}
	
	public Event(int id, String date, String name) {
		this.id = id;
		this.dateEvent = controller.ParseData.convertStringToDate(date);
		this.nameEvent = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateEvent() {
		return dateEvent;
	}
	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}
	public String getNameEvent() {
		return nameEvent;
	}
	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}
	
	public Event getSimpleEvent() {
		return new Event(this.id,this.dateEvent, this.nameEvent);
	}
}
