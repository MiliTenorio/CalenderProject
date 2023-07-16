package view;
import java.util.Date;
import java.util.Scanner;

import controller.ControllerClass;
import model.Event;

public class ViewClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hi! Welcome to Calendar :)");
		
		ControllerClass.createDB();
		
		menuOption();
		
		System.out.println("Thanks for your time :)");
	}
	
	public static void menuOption() {
		try (Scanner scan = new Scanner(System.in)) {
			int option;
			
			do {
				System.out.println("--------------------");
				System.out.println("Choose a option:");
				System.out.println("1 - Add Event");
				System.out.println("2 - Edit Event");
				System.out.println("3 - See Event");
				System.out.println("0 - Exit");
				
				option = scan.nextInt();
				scan.nextLine();
								
				switch(option) {
					case 1: addEvent(); break;
					case 2: editEvent(); break;
					case 3 : seeEvent(); break;
					case 0: return;
				}
			} while(option != 0);

			scan.close();
		}
	}

	private static void seeEvent() {
		// TODO Auto-generated method stub
		System.out.println("Let's looking for some informations about your event");
		
		
	}

	private static void editEvent() {
		// TODO Auto-generated method stub
		System.out.println("Let's edit some information about your events");
	}

	private static void addEvent() {
		// TODO Auto-generated method stub
		System.out.println("Let's add a new event");
		
		try (Scanner scan = new Scanner(System.in)) {
			boolean add = true;
			Date dateEvent;
			String nameEvent;
			
			do {
				System.out.println("--------------------");
				System.out.println("Enter a date in this format, please: YYYY-MM-DD");
				dateEvent = ShowInformations.convertStringToDate(scan.next());
				
				System.out.println("What's the name of the event?");
				nameEvent = scan.next();
				
				Event event = new Event(dateEvent, nameEvent);
				ShowInformations.showEvent(event);
				//ControllerClass.addEvent(event);
				add = false;
			} while(add != false);

			scan.close();
		}
		
	}


}
