package view;
import java.util.Scanner;
import controller.ControllerClass;
import controller.InputData;


public class ViewClass {
	
	static ControllerClass theController;
	private static Scanner scan;
	
	public static void main(String[] args) {
		
		theController = new ControllerClass();

	  	int option = 0;
	  	
	  	do {
	  		scan = new Scanner (System.in);
	  		
		  	System.out.print("##---------- Menu -----------##\n\n");
		  	System.out.print("|-----------------------------|\n");
		  	System.out.print("| 1 - Add Event               |\n");
		  	System.out.print("| 2 - See Events              |\n");
		  	System.out.print("| 3 - Edit Event              |\n");
		  	System.out.print("| 4 - Delete Event            |\n");
		  	System.out.print("| 5 - Back to Menu            |\n");
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
			  		seeEvents(scan);
			  		break;
			  		
			  	case 3:
			  		editEvent(scan);
			  		break;
			  		
			  	case 4:
			  		deleteEvent(scan);
			  		break;
			  		
			  	default:
			  		option = 5;
			  		break;
			  }
		} while(option != 0);
	  	
  		System.out.println("Thanks for coming!");
	}
	
	private static void addEvent(Scanner scan) {		
		if(InputData.inputEventInformation(scan, theController)) {
			System.out.print("|--- Event Add ---|\n");
		}
	}

	private static void seeEvents(Scanner scan) {
	  	controller.InputData.readInformations(scan, theController);
	}
	
	private static void editEvent(Scanner scan) {
		System.out.println("Let's edit some information about your events");
		System.out.println("What's is the type of the Event?");
	  	System.out.print("|---------------------------------------|\n");
	  	System.out.print("| 1 - Simple event                      |\n");
	  	System.out.print("| 2 - Event with initial time           |\n");
	  	System.out.print("| 3 - Event with inital and final time  |\n");
	  	System.out.print("| 0 - Return to menu                    |\n");
	  	System.out.print("|---------------------------------------|\n");
	  	
		int option = controller.InputData.menuTypeShowEvent(scan);
	  	
	  	switch (option) {
	  		case 0:
	  			break;
		  	case 1:
		  		InputData.updateEvent(scan, theController, 0);
		  		break;
		  		
		  	case 2:
		  		InputData.updateEvent(scan, theController, 1);
		  		break;
		  		
		  	case 3:
		  		InputData.updateEvent(scan, theController, 2);
		  		break;
		  }
		
	}
	
	private static void deleteEvent(Scanner scan) {	
		InputData.deleteEvent(scan, theController);
	}

}
