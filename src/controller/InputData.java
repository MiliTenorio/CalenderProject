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
    	Event originalEvent = controller.findSimpleEvent(id);
    	
    	Date date = inputDate(scanner);
    	String name = inputName(scanner);
    	String hour = inputHour(scanner);
    	
    	HourEvent editedEvent = new HourEvent(originalEvent.getId(), date, name, hour);
    	
    	controller.editEvent(originalEvent, editedEvent);
		
	}
	
	public static void editingDurationEvent(Scanner scanner, ControllerClass controller) {
    	int id = inputId(scanner);
    	Event originalEvent = controller.findSimpleEvent(id);
    	
    	Date date = inputDate(scanner);
    	String name = inputName(scanner);
    	String initialHour = inputHour(scanner);
    	String finalHour = inputHour(scanner);
    	
    	DurationEvent editedEvent = new DurationEvent(originalEvent.getId(), date, name, initialHour, finalHour);
    	
    	controller.editEvent(originalEvent, editedEvent);
		
	}
}
