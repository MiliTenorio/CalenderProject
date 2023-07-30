package view;
//import java.util.Date;
import java.util.Scanner;

import controller.ControllerClass;
import model.DurationEvent;
import model.Event;
import model.HourEvent;

public class ViewClass {
	
	static ControllerClass theController;
	//private static Scanner scanner;
	private static Scanner scan;
	
	public static void main(String[] args) {
		
		theController = new ControllerClass();


	  	int option = 0;
	  	
	  	do {
	  		scan = new Scanner (System.in);
	  		
		  	System.out.print("##---------- Menu -----------##\n\n");
		  	System.out.print("|-----------------------------|\n");
		  	System.out.print("| 1 - Add Event               |\n");
		  	System.out.print("| 2 - Edit Event              |\n");
		  	System.out.print("| 3 - See Event               |\n");
		  	System.out.print("| 4 - Back to Menu            |\n");
		  	System.out.print("| 0 - Exit                    |\n");
		  	System.out.print("|-----------------------------|\n");
		  	
		  	option = controller.InputData.menuChoice(scan);
	
		  	if (option == 0) {
		  		break;
		  	}
		  	
		  	switch (option) {
			  	case 1:
			  		addEvent(scan);
			  		break;
			  		
			  	case 2:
			  		seeEvent();
			  		break;
			  		
			  	case 3:
			  		editEvent();
			  		break;
			  		
			  	default:
			  		option = 4;
			  		break;
			  }
		} while(option != 0);
	  	
  		System.out.println("Thanks for coming!");
	}
	
	private static void seeEvent() {
		// TODO Auto-generated method stub
		System.out.println("Let's looking for some informations about your event");
		
		
	}

	private static void editEvent() {
		// TODO Auto-generated method stub
		System.out.println("Let's edit some information about your events");
	}

	private static void addEvent(Scanner scan) {
		// TODO Auto-generated method stub
		System.out.println("Let's add a new event:");
					
		/*if(theController.addEvent(theController.inputSimpleEventInformation(scan)) == false) {
			System.out.println("Some problem happens :( ");
			addEvent(scan);
		}*/
		
		Event newEvent = theController.inputSimpleEventInformation(scan);
		
		System.out.println("Are you need add more information about the event?");
	  	System.out.print("|--------------------------------|\n");
	  	System.out.print("| 1 - I don't need               |\n");
	  	System.out.print("| 2 - Add Initial Time           |\n");
	  	System.out.print("| 3 - Add Initial and Final Time |\n");
	  	System.out.print("|--------------------------------|\n");
		
		int option = controller.InputData.menuTypeEvent(scan);
	  	
	  	switch (option) {
		  	case 1:
		  		if(theController.addEvent(newEvent) == false) {
					System.out.println("Some problem happens :( ");
					addEvent(scan);
				}
		  		break;
		  		
		  	case 2:
		  		HourEvent newHourEvent = theController.inputHourEventInformation(scan, newEvent);
		  		if(theController.addEvent(newHourEvent) == false) {
					System.out.println("Some problem happens :( ");
					addEvent(scan);
				}
		  		break;
		  		
		  	case 3:
		  		DurationEvent newDurationEvent = theController.inputDurationEventInformation(scan, newEvent);
		  		if(theController.addEvent(newDurationEvent) == false) {
					System.out.println("Some problem happens :( ");
					addEvent(scan);
				}
		  		break;
		  }
		
		
		/*System.out.println("All Events:");
	
		for(Event eventList : theController.getAllEvents()) {
			System.out.printf("Date: " + controller.ParseData.convertDateToString(eventList.getDateEvent()) + 
					" Name: " + eventList.getNameEvent() + "\n");
		}*/
		
	}


}
