package controller;

//import java.util.LinkedList;

import model.DurationEvent;
import model.Event;
import model.HourEvent;

public class OutputData {
	
	/*public static void showEvent(Object event) {
		System.out.println("--------------------");
		if(event instanceof Event) {
			System.out.println("simple");
			showSimpleEvent((Event) event);
		}
		else if(event instanceof HourEvent) {
			System.out.println("hour");
			showHourEvent((HourEvent) event);
		}
		else if(event instanceof Event) {
			showDurationEvent((DurationEvent) event);
		}
		//System.out.println("--------------------");
	}*/
	
	public static void showSimpleEvent(Event event) {
		System.out.println("--------------------");
		System.out.println("Id: " + event.getId());
		System.out.printf("Date: " + ParseData.convertDateToString(event.getDateEvent()) + "\n");
		System.out.println("Name: " + event.getNameEvent());
	}

	public static void showHourEvent(HourEvent event) {
		showSimpleEvent(event);
		System.out.println("The time of event: " + event.getTime());
	}
	
	public static void showDurationEvent(DurationEvent event) {
		showSimpleEvent(event);
		System.out.println("The initial time of event: " + event.getInitialTime());
		System.out.println("The final time of event: " + event.getFinalTime());
	}
	
	public static void showAllEvents(ControllerClass controller) {
		System.out.println("All Events:");
		
		for(Event eventList : controller.getAllEvents()) {
			showSimpleEvent(eventList);
		}
	}
	
	public static void showSimpleEvents(ControllerClass controller) {
		System.out.println("All Simple Events:");
		
		for(Event eventList : controller.getEvents()) {
			showSimpleEvent(eventList);
		}
	}
	
	public static void showHourEvents(ControllerClass controller) {
		System.out.println("All Events with Time:");
		
		for(HourEvent eventList : controller.getHourEvents()) {
			showHourEvent(eventList);
		}
	}

	public static void showDurationEvents(ControllerClass controller) {
		System.out.println("All Events with Initial and Final Time:");
		
		for(DurationEvent eventList : controller.getDurationEvents()) {
			showDurationEvent(eventList);
		}
	}
	
	public static void showEvents(ControllerClass controller, int type) {
		if(type == 0) {
			showAllEvents(controller);
		}

		switch (type) {
		  	case 1:
		  		showSimpleEvents(controller);
		  		break;
		  	case 2:
		  		showHourEvents(controller);
		  		break;
		  	case 3:
		  		showDurationEvents(controller);
		  		break;
		}
		
	}
}

