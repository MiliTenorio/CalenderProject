package controller;

import model.DurationEvent;
import model.Event;
import model.HourEvent;

public class OutputData {
	
	public static void showEvent(Object event) {
		System.out.println("--------------------");
		if(event instanceof Event) {
			showSimpleEvent((Event) event);
		}
		else if(event instanceof HourEvent) {
			showHourEvent((HourEvent) event);
		}
		else if(event instanceof Event) {
			showDurationEvent((DurationEvent) event);
		}
		System.out.println("--------------------");
	}
	
	public static void showSimpleEvent(Event event) {
		System.out.println("Informations:");
		System.out.printf("Date: " + ParseData.convertDateToString(event.getDateEvent()) + "\n");
		System.out.println("Name: " + event.getNameEvent());
	}

	public static void showHourEvent(HourEvent event) {
		showSimpleEvent(event);
		System.out.println("The time of event: " + event.getTime() + "\n");
	}
	
	public static void showDurationEvent(DurationEvent event) {
		showSimpleEvent(event);
		System.out.println("The initial time of event: " + event.getInitialTime() + "\n");
		System.out.println("The final time of event: " + event.getFinalTime() + "\n");
	}

}

