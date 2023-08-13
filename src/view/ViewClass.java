package view;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
import java.util.Scanner;

import controller.ControllerClass;
import controller.InputData;
/*import model.DurationEvent;
import model.Event;
import model.HourEvent;
import model.MySQLDatabase;*/

public class ViewClass {
	
	static ControllerClass theController;
	private static Scanner scan;
	
	public static void main(String[] args) {
		
		/*MySQLDatabase mySQLDatabase = new MySQLDatabase();
        mySQLDatabase.createDatabase(); // Create the database if it doesn't exist

    	// Create SimpleEventTable, HourEventTable, and DurationEventTable
        try (Connection connection = mySQLDatabase.getConnection()) {
        	mySQLDatabase.createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
		
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
		// TODO Auto-generated method stub
		/*System.out.println("Let's looking for some informations about your event");
	  	System.out.print("|--------------------------------------------|\n");
	  	System.out.print("| 1 - Only one event                         |\n");
	  	System.out.print("| 2 - All events                             |\n");
	  	System.out.print("| 3 - All simple events                      |\n");
	  	System.out.print("| 4 - All events with initial time           |\n");
	  	System.out.print("| 5 - All events with inital and final time  |\n");
	  	System.out.print("| 0 - Return to menu                         |\n");
	  	System.out.print("|--------------------------------------------|\n");
		
		int option = controller.InputData.menuTypeShowEvent(scan);
	  	
	  	switch (option) {
	  		case 0:
	  			break;
		  	case 1:
		  		int id = InputData.inputId(scan);
		  		controller.OutputData.showSimpleEvent(theController.findSimpleEvent(id));
		  		break;
		  		
		  	case 2:
		  		controller.OutputData.showEvents(theController, 0);
		  		break;
		  		
		  	case 3:
		  		controller.OutputData.showEvents(theController, 1);
		  		break;
		  		
		  	case 4:
		  		controller.OutputData.showEvents(theController, 2);
		  		break;
		  		
		  	case 5:
		  		controller.OutputData.showEvents(theController, 3);
		  		break;
		  }*/
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
		  		InputData.editingSimpleEvent(scan, theController);
		  		break;
		  		
		  	case 2:
		  		InputData.editingHourEvent(scan, theController);
		  		break;
		  		
		  	case 3:
		  		InputData.editingDurationEvent(scan, theController);
		  		break;
		  }
		
	}
	
	private static void deleteEvent(Scanner scan2) {
		System.out.println("Let's delete a event");
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
		  		InputData.deleteSimpleEvent(scan, theController);
		  		break;
		  		
		  	case 2:
		  		InputData.deleteHourEvent(scan, theController);
		  		break;
		  		
		  	case 3:
		  		InputData.deleteDurationEvent(scan, theController);
		  		break;
		  }
	}

}
