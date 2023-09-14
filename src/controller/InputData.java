package controller;

import java.util.Date;
import java.util.Scanner;

import model.Event;
import model.HourEvent;
import model.DurationEvent;

public class InputData {
	
	/*
	 * InputData class responsible to receive data from console and send to controller the action 
	 * the View Class are expect happens
	 */
	
    public static Date inputDate(Scanner scanner) {
        System.out.print("Enter a date (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        
		while(ValidationData.validationDate(dateString) == false) {
			System.out.println("OPS! Could you, please, enter a date in this format, please: YYYY-MM-DD");
			dateString = scanner.next();
		}

        scanner.nextLine();
		return controller.ParseData.convertStringToDate(dateString);
    }

    public static String inputName(Scanner scanner) {
        System.out.print("Enter a name: ");
        String name = scanner.nextLine();

        scanner.nextLine();
        return name;
    }
    
    public static int menuChoice(Scanner scanner) {   	
    	int choice = scanner.nextInt();
        
    	while(!ValidationData.validationChoice(choice)) {
        	System.out.print("Could you, please, choice a available number?");
        	choice = scanner.nextInt();
        }
        scanner.nextLine();
        return choice;
    }
    
    public static int menuTypeEvent(Scanner scanner) {   	
    	int choice = scanner.nextInt();
        
    	while(!ValidationData.validationTypeEvent(choice)) {
        	System.out.print("Could you, please, choice a available number?");
        	choice = scanner.nextInt();
        }
        scanner.nextLine();
        return choice;
    }

	public static String inputHour(Scanner scanner) {
        System.out.print("Enter a hour (hh:mm): ");
        String hourString = scanner.nextLine();
        
        while(ValidationData.validationTime(hourString) == false) {
			System.out.println("OPS! Could you, please, enter a date in this format, please: hh:mm");
			hourString = scanner.next();
		}
        scanner.nextLine();
        return hourString;
	}
	
    public static int menuTypeShowEvent(Scanner scanner) {   	
    	int choice = scanner.nextInt();
        
    	while(!ValidationData.validationTypeShowEvent(choice)) {
        	System.out.print("Could you, please, choice a available number?");
        	choice = scanner.nextInt();
        }
        scanner.nextLine();
        return choice;
    }
    
    public static int inputId(Scanner scanner) { 	
    	System.out.print("Enter id of the event:");
    	int id = scanner.nextInt();
    	scanner.nextLine();
    	return id;
    }
	
	public static void updateEvent(Scanner scanner, ControllerClass controller, int type) {
		int id = inputId(scanner);
		boolean update = false;
		
    	if(controller.findSimpleEvent(id) == null) {
    		OutputData.eventNotFind();
    		return;
    	}

		System.out.println("Let's edit!");
    	switch (type) {
  		case 0: //Simple Event
  	    	update = controller.updateEvent(new Event(id, inputDate(scanner), inputName(scanner)));
  	    	break;
  		case 1:
  			update = controller.updateEvent(new HourEvent(id, inputDate(scanner), inputName(scanner), inputHour(scanner)));
  			break;
  		case 2:
  			update = controller.updateEvent(new DurationEvent(id, inputDate(scanner), inputName(scanner), inputHour(scanner), inputHour(scanner)));
    	}
    	    	
    	if(update == true) {
    		System.out.println("Event updated");
    	}else {
    		System.out.println(":( something wrong happens");
    	}
	}
	
	public static void deleteEvent(Scanner scanner, ControllerClass controller) {
		System.out.println("Let's delete!");
		int id = inputId(scanner);
		
    	if(controller.findSimpleEvent(id) == null) {
    		OutputData.eventNotFind();
    		return;
    	}

    	if(controller.deleteEvent(id)) {
    		System.out.println("Event deleted");
    	}else {
    		System.out.println(":( something wrong happens");
    	}
	}
	
	public static boolean inputEventInformation(Scanner scan, ControllerClass controller) {
		System.out.println("Let's add a new event:");
		
		Event newEvent = inputSimpleEventInformation(scan);
		
		System.out.println("Are you need add more information about the event?");
	  	System.out.print("|--------------------------------|\n");
	  	System.out.print("| 1 - I don't need               |\n");
	  	System.out.print("| 2 - Add Initial Time           |\n");
	  	System.out.print("| 3 - Add Initial and Final Time |\n");
	  	System.out.print("|--------------------------------|\n");
		
		int option = InputData.menuTypeEvent(scan);
	  	
	  	switch (option) {
		  	case 1:
		  		if(controller.addEvent(newEvent) == true) {
			  		return true;
				}
		  		
		  	case 2:
		  		HourEvent newHourEvent = inputHourEventInformation(scan, newEvent);
		  		if(controller.addEvent(newHourEvent) == true) {
			  		return true;
				}
		  		
		  	case 3:
		  		DurationEvent newDurationEvent = inputDurationEventInformation(scan, newEvent);
		  		if(controller.addEvent(newDurationEvent) == true) {
			  		return true;
				}
		  		
				System.out.println("Some problem happens :( ");
				inputEventInformation(scan,controller);
		  }
	  	return false;
	}
	
	public static Event inputSimpleEventInformation(Scanner scan) {
		
		Event simpleEvent = new Event(InputData.inputDate(scan), InputData.inputName(scan));
		
		return simpleEvent;
	}
	
	public static HourEvent inputHourEventInformation(Scanner scan, Event simpleEvent) {
		HourEvent hourEvent = new HourEvent(simpleEvent.getDateEvent(),simpleEvent.getNameEvent(),inputHour(scan));
		
		return hourEvent;
	}
	
	public static DurationEvent inputDurationEventInformation(Scanner scan, Event simpleEvent) {
		DurationEvent durationEvent = new DurationEvent(simpleEvent.getDateEvent(),simpleEvent.getNameEvent(),inputHour(scan), inputHour(scan));
		
		return durationEvent;
	}

	public static void readInformations(Scanner scan, ControllerClass theController) {
		System.out.println("Let's looking for some informations about your event");
	  	System.out.print("|--------------------------------------------|\n");
	  	System.out.print("| 1 - Only one event                         |\n");
	  	System.out.print("| 2 - All events                             |\n");
	  	System.out.print("| 3 - All simple events                      |\n");
	  	System.out.print("| 4 - All events with initial time           |\n");
	  	System.out.print("| 5 - All events with inital and final time  |\n");
	  	System.out.print("| 0 - Return to menu                         |\n");
	  	System.out.print("|--------------------------------------------|\n");
		
		int option = menuTypeShowEvent(scan);
	  	
	  	switch (option) {
	  		case 0:
	  			break;
		  	case 1:
		  		menuOneEvent(scan, theController);;
		  		break;
		  		
		  	case 2:
		  		OutputData.showAllInfoEvents(theController);
		  		break;
		  		
		  	case 3:
		  		OutputData.showSimpleEvents(theController);
		  		break;
		  		
		  	case 4:
		  		OutputData.showHourEvents(theController);
		  		break;
		  		
		  	case 5:
		  		OutputData.showDurationEvents(theController);
		  		break;
		  }
		
	}

	private static void menuOneEvent(Scanner scan, ControllerClass theController) {
		System.out.println("What is the type of this event?");
	  	System.out.print("|--------------------------------------------|\n");
	  	System.out.print("| 1 - Simple event                           |\n");
	  	System.out.print("| 2 - Event with initial time                |\n");
	  	System.out.print("| 3 - Event with inital and final time       |\n");
	  	System.out.print("| 0 - Return to menu                         |\n");
	  	System.out.print("|--------------------------------------------|\n");
		
	  	int option = scan.nextInt();
		int id = InputData.inputId(scan);	  	

	  	switch (option) {
	  		case 0:
	  			break;
		  	case 1:
		  		Event newEvent = theController.findSimpleEvent(id);
		  		if(newEvent != null) {
			  		OutputData.showSimpleEvent(newEvent);
		  		}else {
		  			OutputData.eventNotFind();
		  		}
		  		break;
		  		
		  	case 2:
		  		HourEvent newHourEvent = theController.findHourEvent(id);
		  		if(newHourEvent != null) {
			  		OutputData.showHourEvent(newHourEvent);
		  		}else {
		  			OutputData.eventNotFind();
		  		}
		  		break;
		  		
		  	case 3:
		  		DurationEvent newDurationEvent = theController.findDurationEvent(id);
		  		if(newDurationEvent != null) {
			  		OutputData.showDurationEvent(newDurationEvent);
		  		}else {
		  			OutputData.eventNotFind();
		  		}
		  		break;
		  }
		
	}
	
}
