package model;
import java.util.Date;

public class HourEvent extends Event {
	int idEvent;
	String time;

	public HourEvent(int idEvent, Date date, String name, String time) {
		super(idEvent, date, name);
		// TODO Auto-generated constructor stub
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
