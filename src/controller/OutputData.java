package controller;

import model.DurationEvent;
import model.Event;
import model.HourEvent;

public class OutputData {
	/*
	 * The OutputData is a class used to show all informations to user
	 * All the models informations has templates here to be display
	*/
	
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
	
	//All informations
	public static void showAllInfoEvents(ControllerClass controller) {
		System.out.println("\n All Events: \n");
		showSimpleEvents(controller);
		showHourEvents(controller);
		showDurationEvents(controller);
	}
	
	public static void showSimpleEvents(ControllerClass controller) {
		System.out.println("\n All Events: \n");
		
		for(Event eventList : controller.getAllSimpleEvent()) {
			showSimpleEvent(eventList);
		}
	}
	
	public static void showHourEvents(ControllerClass controller) {
		System.out.println("\n All Events with Time: \n");
		
		for(HourEvent eventList : controller.getAllHourEvent()) {
			showHourEvent(eventList);
		}
	}

	public static void showDurationEvents(ControllerClass controller) {
		System.out.println("\n All Events with Initial and Final Time: \n");
		
		for(DurationEvent eventList : controller.getAllDurationEvent()) {
			showDurationEvent(eventList);
		}
	}

	public static void eventNotFind() {
		System.out.println("Sorry! We don't find ID event");
	}
}

