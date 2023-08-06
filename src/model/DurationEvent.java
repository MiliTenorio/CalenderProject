package model;

import java.util.Date;

public class DurationEvent extends Event{
	
	String initialTime;
	String finalTime;

	public DurationEvent(int idEvent, Date date, String name, String initialTime, String finalTime) {
		super(idEvent, date, name);
		this.initialTime = initialTime;
		this.finalTime = finalTime;
	}
	
	public DurationEvent(int idEvent, String date, String name, String initialTime, String finalTime) {
		super(idEvent, date, name);
		this.initialTime = initialTime;
		this.finalTime = finalTime;
	}

	public String getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(String initialTime) {
		this.initialTime = initialTime;
	}

	public String getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(String finalTime) {
		this.finalTime = finalTime;
	}

}
