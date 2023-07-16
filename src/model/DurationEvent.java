package model;

import java.sql.Time;
import java.util.Date;

public class DurationEvent extends Event{
	
	Time initialTime;
	Time finalTime;

	public DurationEvent(Date date, String name, Time initialTime, Time finalTime) {
		super(date, name);
		this.initialTime = initialTime;
		this.finalTime = finalTime;
	}

	public Time getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(Time initialTime) {
		this.initialTime = initialTime;
	}

	public Time getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Time finalTime) {
		this.finalTime = finalTime;
	}
	
	

}
