package controller;

import java.util.Date;
import java.util.Scanner;

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
        System.out.print("Enter a hour (XXhXX): ");
        String hour = scanner.nextLine();

        scanner.nextLine();
        return hour;
	}
}
