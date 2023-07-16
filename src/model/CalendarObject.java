package model;

import java.util.Date;

public class CalendarObject {
	
	@SuppressWarnings("deprecation")
	public Date getDate(int day, int month, int year) {
		Date date = new Date();
		date.setDate(day);
		date.setMonth(month);
		date.setYear(year);
		return date;
	}

	@SuppressWarnings("deprecation")
	public Date getDate(int day, int month, int year, int hour, int minute) {
		Date date = new Date();
		date.setDate(day);
		date.setMonth(month);
		date.setYear(year);
		date.setHours(hour);
		date.setMinutes(minute);
		return date;
	}
}
