package model;

import java.util.Date;

public class Event {
	Date dateEvent; //Can be only MM-DD-YYYY or MM-DD-YYYY HH:MM or MM-DD-YYYY HH:MM - HH:MM
	String nameEvent;
	
	public Event(Date date, String name) {
		this.dateEvent = date;
		this.nameEvent = name;
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
}
