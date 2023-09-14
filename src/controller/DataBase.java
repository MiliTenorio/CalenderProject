package controller;

import java.sql.*;
import java.util.LinkedList;

import model.DurationEvent;
import model.Event;
import model.HourEvent;
import model.MySQLDatabase;

public class DataBase {
	/*
	 * The DataBase Class has the functions will work to send and return informations from Tables
	 * This class is a intermediate between Controller Class and SQL commands in the MySQLDatabase
	*/
	
	/*
	 * About Simple Event
	 */
	public static boolean addEvent(MySQLDatabase mySQLDatabase, Event event) {

        try {        	
        	boolean rowsInserted;

        	rowsInserted = mySQLDatabase.createSimpleEvent(event);
        	
            if (rowsInserted) {
                System.out.println("Event add!");
                return true;
            } else {
                System.out.println("Fail to add event");
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public static LinkedList<Event> readAllSimpleEvent(MySQLDatabase mySQLDatabase) {
        try {        	
        	return mySQLDatabase.readAllSimpleEventTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static Event readSimpleEvent(MySQLDatabase mySQLDatabase, int id) {
        try {        	
        	return mySQLDatabase.readSimpleEvent(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static boolean updateSimpleEvent(MySQLDatabase mySQLDatabase, int eventId, Event updateEvent) {
        try {        	
        	return mySQLDatabase.updateSimpleEvent(updateEvent, eventId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static boolean deleteSimpleEvent(MySQLDatabase mySQLDatabase, int id) {
        try {        	
        	return mySQLDatabase.deleteSimpleEvent(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	/*
	 * About Hour Event
	 */
	public static boolean addEvent(MySQLDatabase mySQLDatabase, HourEvent event) {
		
        try {        	
        	boolean rowsInserted;

        	rowsInserted = mySQLDatabase.createHourEvent(event);
        	
            if (rowsInserted) {
                System.out.println("Event add!");
                return true;
            } else {
                System.out.println("Fail to add event");
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }   
   }
	
	public static LinkedList<HourEvent> readAllHourEvent(MySQLDatabase mySQLDatabase) {
        try {        	
        	return mySQLDatabase.readAllHourEventTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static HourEvent readHourEvent(MySQLDatabase mySQLDatabase, int id) {
        try {        	
        	return mySQLDatabase.readHourEventTable(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static boolean updateHourEvent(MySQLDatabase mySQLDatabase, int id, HourEvent updateEvent) {
        try {        	
        	return mySQLDatabase.updateHourEvent(updateEvent, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static boolean deleteHourEvent(MySQLDatabase mySQLDatabase, int id) {
        try {        	
        	return mySQLDatabase.deleteHourEvent(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	/*
	 * About Duration Event
	 */
	public static boolean addEvent(MySQLDatabase mySQLDatabase, DurationEvent event) {
        try {        	
        	boolean rowsInserted;

        	rowsInserted = mySQLDatabase.createDurationEvent(event);
        	
            if (rowsInserted) {
                System.out.println("Event add!");
                return true;
            } else {
                System.out.println("Fail to add event");
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }   
	}



	public static LinkedList<DurationEvent> readAllDurationEvent(MySQLDatabase mySQLDatabase) {
        try {        	
        	return mySQLDatabase.readAllDurationEventTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static DurationEvent readDurationEvent(MySQLDatabase mySQLDatabase, int id) {
        return mySQLDatabase.readDurationEventTable(id);
    }

	public static boolean updateDurationEvent(MySQLDatabase mySQLDatabase, int id, DurationEvent updateEvent) {
        try {        	
        	return mySQLDatabase.updateDurationEvent(updateEvent, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	public static boolean deleteDurationEvent(MySQLDatabase mySQLDatabase, int id) {
        try {        	
        	return mySQLDatabase.deleteDurationEvent(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	/*
	 * If is possible find the id event as foreign key in the others table,
	 * we need delete this event first and after that delete the original 
	 * event in the simple table
	 * */
	public static boolean deleteEvent(MySQLDatabase mySQLDatabase, int id) throws SQLException {
		boolean moreInfo = false;
		boolean deletedMoreInfo = false;
		
		if(readHourEvent(mySQLDatabase, id) != null) {
			moreInfo = true;
			deletedMoreInfo = mySQLDatabase.deleteHourEvent(id);
		}else {
			if(readDurationEvent(mySQLDatabase, id) != null) {
				moreInfo = true;
				deletedMoreInfo = mySQLDatabase.deleteDurationEvent(id);
			}
		}
		
		if(moreInfo == true && deletedMoreInfo ==  true) {
			return mySQLDatabase.deleteSimpleEvent(id);
		}
		
		if(moreInfo == false && deletedMoreInfo ==  false) {
			return mySQLDatabase.deleteSimpleEvent(id);
		}
		
		return false;
	}
	

}
