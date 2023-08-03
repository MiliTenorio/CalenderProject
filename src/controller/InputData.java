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
}
