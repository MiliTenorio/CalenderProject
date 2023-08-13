package controller;

import java.util.Date;
import java.util.Scanner;

import model.Event;
import model.HourEvent;
import model.DurationEvent;

public class InputData {
	
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
    
    public static void editingSimpleEvent(Scanner scanner, ControllerClass controller) {
    	int id = inputId(scanner);
    	Event originalEvent = controller.findSimpleEvent(id);
    	
    	Date date = inputDate(scanner);
    	String name = inputName(scanner);
    	
    	Event editedEvent = new Event(originalEvent.getId(), date, name);
    	
    	controller.editEvent(originalEvent, editedEvent);
    }

	public static void editingHourEvent(Scanner scanner, ControllerClass controller) {
    	int id = inputId(scanner);
    	//Event originalEvent = controller.findSimpleEvent(id); > Will be change to find foreign key in SQL
    	HourEvent originalEvent = controller.findHourEvent(id);
    	
    	Date date = inputDate(scanner);
    	String name = inputName(scanner);
    	String hour = inputHour(scanner);
    	
    	HourEvent editedEvent = new HourEvent(originalEvent.getId(), date, name, hour);
    	
    	controller.editEvent(originalEvent, editedEvent);
		
	}
	
	public static void editingDurationEvent(Scanner scanner, ControllerClass controller) {
    	int id = inputId(scanner);
    	//Event originalEvent = controller.findSimpleEvent(id); > Will be change to find foreign key in SQL
    	DurationEvent originalEvent = controller.findDurationEvent(id);
    	
    	Date date = inputDate(scanner);
    	String name = inputName(scanner);
    	String initialHour = inputHour(scanner);
    	String finalHour = inputHour(scanner);
    	
    	DurationEvent editedEvent = new DurationEvent(originalEvent.getId(), date, name, initialHour, finalHour);
    	
    	controller.editEvent(originalEvent, editedEvent);
		
	}

	public static void deleteSimpleEvent(Scanner scanner, ControllerClass controller) {
    	int idEvent = inputId(scanner);    
		Event event = controller.findSimpleEvent(idEvent);
		
		if(event == null) {
			System.out.println("Event not found!");
			return;
		}
		
		System.out.println("Are you sure you want to delete this event? Y/N");
		OutputData.showSimpleEvent(event);
		String delete = scanner.next();
		
		if(delete.equals("Y") || delete.equals("y")) {
			int idList = controller.events.indexOf(event);
			
	    	if(controller.deleteSimpleEvent(idList,event)) {
				System.out.println("Deleted!");
	    	}else {
				System.out.println("Not deleted");
	    	}
		}
		else {
			System.out.println("Request canceled");
		}
	}

	public static void deleteHourEvent(Scanner scanner, ControllerClass controller) {
    	int idEvent = inputId(scanner);    
		HourEvent event = controller.findHourEvent(idEvent);
		
		if(event == null) {
			System.out.println("Event not found!");
			return;
		}
		
		System.out.println("Are you sure you want to delete this event? Y/N");
		OutputData.showSimpleEvent(event);
		
		String delete = scanner.nextLine();
		
		if(delete.equals("Y")|| delete.equals("y")) {
			int idList = controller.hourEvents.indexOf(event);
			
	    	if(controller.deleteHourEvent(idList,event)) {
				System.out.println("Deleted!");
	    	}else {
				System.out.println("Not deleted");
	    	}
		}
		else {
			System.out.println("Request canceled");
		}	
	}

	public static void deleteDurationEvent(Scanner scanner, ControllerClass controller) {
    	int idEvent = inputId(scanner);    
		DurationEvent event = controller.findDurationEvent(idEvent);
		
		if(event == null) {
			System.out.println("Event not found!");
			return;
		}
		
		System.out.println("Are you sure you want to delete this event? Y/N");
		OutputData.showSimpleEvent(event);
		String delete = scanner.nextLine();
		
		if(delete.equals("Y") || delete.equals("y")) {
			int idList = controller.durationEvents.indexOf(event);
			
	    	if(controller.deleteDurationEvent(idList,event)) {
				System.out.println("Deleted!");
	    	}else {
				System.out.println("Not deleted");
	    	}
		}
		else {
			System.out.println("Request canceled");
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
		  		int id = InputData.inputId(scan);
		  		OutputData.showSimpleEvent(theController.findSimpleEvent(id));
		  		break;
		  		
		  	case 2:
		  		OutputData.showEvents(theController, 0);
		  		break;
		  		
		  	case 3:
		  		OutputData.showEvents(theController, 1);
		  		break;
		  		
		  	case 4:
		  		OutputData.showEvents(theController, 2);
		  		break;
		  		
		  	case 5:
		  		OutputData.showEvents(theController, 3);
		  		break;
		  }
		
	}
	
}
