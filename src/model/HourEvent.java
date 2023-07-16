package model;

import java.sql.Time;
import java.util.Date;

public class HourEvent extends Event {
	
	Time time;

	public HourEvent(Date date, String name, Time time) {
		super(date, name);
		// TODO Auto-generated constructor stub
		this.time = time;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
