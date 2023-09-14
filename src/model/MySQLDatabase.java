package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import controller.ParseData;

public class MySQLDatabase {
	/*
	 * The Database in MySQL
	 * Create the Database, Tables and all SQL commands
	 */
	
	//Informations to connect database
    private String jdbcURL = "jdbc:mysql://localhost:3306/";
    private String databaseName = "calendarDatabase";
    private String username = "userTest";
    private String password = "userTest";

    //SQL to create the tables
    private static final String createSimpleEventTableSQL = "CREATE TABLE IF NOT EXISTS simpleEventTable (id INT AUTO_INCREMENT PRIMARY KEY, date DATE NOT NULL, name VARCHAR(255) NOT NULL)";
    private static final String createHourEventTable2SQL = "CREATE TABLE IF NOT EXISTS hourEventTable (id INT AUTO_INCREMENT PRIMARY KEY, foreign_id INT NOT NULL, time VARCHAR(8) NOT NULL, FOREIGN KEY (foreign_id) REFERENCES simpleEventTable(id))";
    private static final String createDurationEventTableSQL = "CREATE TABLE IF NOT EXISTS durationEventTable (id INT AUTO_INCREMENT PRIMARY KEY, foreign_id INT NOT NULL, initialTime VARCHAR(8) NOT NULL, finalTime VARCHAR(8) NOT NULL, FOREIGN KEY (foreign_id) REFERENCES simpleEventTable(id))";
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL + databaseName, username, password);
    }

    public void createDatabase() {
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             PreparedStatement statement = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS " + databaseName)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTables() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(createSimpleEventTableSQL);
             PreparedStatement statement2 = connection.prepareStatement(createHourEventTable2SQL);
             PreparedStatement statement3 = connection.prepareStatement(createDurationEventTableSQL)) {
            statement.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //CRUD Simple Event
    public boolean createSimpleEvent(Event event) throws SQLException {
        String insertSQL = "INSERT INTO simpleEventTable (date, name) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, ParseData.convertDateToString(event.dateEvent));
            preparedStatement.setString(2, event.getNameEvent());
            int rowsInserted = preparedStatement.executeUpdate();
            
            if(rowsInserted > 0) {
            	return true;
            }
            else {
            	return false;
            }
        }
    }

    public LinkedList<Event> readAllSimpleEventTable() throws SQLException {
    	LinkedList<Event> result = new LinkedList<Event>();
        String selectSQL = "SELECT * FROM simpleEventTable";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                String name = resultSet.getString("name");
                result.add(new Event(id, date, name));
            }
        }
        return result;
    }
    
    public Event readSimpleEvent(int id) throws SQLException {
        String selectSQL = "SELECT * FROM simpleEventTable WHERE id=" + id;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String name = resultSet.getString("name");
                return(new Event(id, date, name));
            }
        }
        return null;
    }

    public boolean updateSimpleEvent(Event updateEvent) throws SQLException {
        String updateSQL = "UPDATE simpleEventTable SET date = ?, name = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, ParseData.convertDateToString(updateEvent.getDateEvent()));
            preparedStatement.setString(2, updateEvent.getNameEvent());
            preparedStatement.setInt(3, updateEvent.getId());
            int rowsInserted = preparedStatement.executeUpdate();
            
            if(rowsInserted > 0) {
            	return true;
            }
            else {
            	return false;
            }
        }
    }
        
    public boolean updateSimpleEvent(Event updateEvent, int id) throws SQLException {
        String updateSQL = "UPDATE simpleEventTable SET date = ?, name = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, ParseData.convertDateToString(updateEvent.getDateEvent()));
            preparedStatement.setString(2, updateEvent.getNameEvent());
            preparedStatement.setInt(3, id);
            int rowsInserted = preparedStatement.executeUpdate();
            
            if(rowsInserted > 0) {
            	return true;
            }
            else {
            	return false;
            }
        }
    }

    public boolean deleteSimpleEvent(int id) throws SQLException {
        String deleteSQL = "DELETE FROM simpleEventTable WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);
            int rowsInserted = preparedStatement.executeUpdate();
            
            if(rowsInserted > 0) {
            	return true;
            }
            else {
            	return false;
            }
        }
    }

    //CRUD Hour Event
    public boolean createHourEvent(HourEvent event) throws SQLException {
    	boolean simpleEvent = createSimpleEvent(event);
    	
    	if(simpleEvent == false) {
    		return false;
    	}
    	
    	int foreignId = findIdSimpleEvent(ParseData.convertDateToString(event.getDateEvent()),event.getNameEvent());
        String insertSQL = "INSERT INTO hourEventTable (foreign_id, time) VALUES (?, ?)";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, foreignId);
            preparedStatement.setString(2, event.getTime());
            int rowsInserted = preparedStatement.executeUpdate();
            
            if(rowsInserted > 0 && simpleEvent) {
            	return true;
            }
            else {
            	return false;
            }
        }
    }
    
    public LinkedList<HourEvent> readAllHourEventTable() throws SQLException {
    	LinkedList<HourEvent> result = new LinkedList<HourEvent>();
        String selectSQL = "SELECT t2.id, t1.name, t1.date, t2.time, t2.foreign_id FROM houreventtable t2 INNER JOIN "
        		+ "simpleeventtable t1 ON t2.foreign_id = t1.id;";
       
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {

            	   //Print
            	   System.out.println("foreign_id: " + resultSet.getInt("foreign_id"));
            	   result.add(new HourEvent(resultSet.getInt("foreign_id"), ParseData.convertStringToDate(resultSet.getString("date")),
            			   resultSet.getString("name"), resultSet.getString("time")));
               }
        }
        
        return result;
    }
    
    public HourEvent readHourEventTable(int id) throws SQLException {
        String selectSQL = "SELECT t1.name, t1.date, t2.time, t2.foreign_id FROM houreventtable t2 INNER JOIN "
        		+ "simpleeventtable t1 ON t2.foreign_id = t1.id  WHERE t2.foreign_id = " + id ;
       
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {
            	   //Print
            	   System.out.println("foreign_id: " + resultSet.getInt("foreign_id"));
            	   return new HourEvent(resultSet.getInt("foreign_id"), ParseData.convertStringToDate(resultSet.getString("date")),
            			   resultSet.getString("name"), resultSet.getString("time"));
               }
        }
        
        return null;
    }

    public boolean updateHourEvent(HourEvent event, int foreignId) throws SQLException {
        String updateSQL = "UPDATE hourEventTable SET time = ? WHERE foreign_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, event.time);
            preparedStatement.setInt(2, event.getId());
            int hourEvent = preparedStatement.executeUpdate();
            
            boolean simpleEvent = updateSimpleEvent(event, foreignId);    
            
            if(hourEvent > 0 || simpleEvent) {
            	return true;
            }else {
            	return false;
            }
        }
    }

    public boolean deleteHourEvent(int id) throws SQLException {
        String deleteSQL = "DELETE FROM hourEventTable WHERE foreign_id = ?";
        int rows = -1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);
            rows = preparedStatement.executeUpdate();
        }
        
        if(rows > 0) {
        	return true;
        }
        else {
        	return false;
        }
    }
    
    //CRUD Duration Event
    public boolean createDurationEvent(DurationEvent event) throws SQLException {
    	boolean simpleEvent = createSimpleEvent(event);
    	
    	if(simpleEvent == false) {
    		return false;
    	}
    	
    	int foreignId = findIdSimpleEvent(ParseData.convertDateToString(event.getDateEvent()),event.getNameEvent());    	
    	String insertSQL = "INSERT INTO durationEventTable (foreign_id, initialTime, finalTime) VALUES (?, ?, ?)";
        
    	try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, foreignId);
            preparedStatement.setString(2, event.initialTime);
            preparedStatement.setString(3, event.finalTime);
            int rowsInserted = preparedStatement.executeUpdate();
            
            if(rowsInserted > 0 && simpleEvent) {
            	return true;
            }
            else {
            	return false;
            }
        }
    }
    
    public LinkedList<DurationEvent> readAllDurationEventTable() throws SQLException {
    	LinkedList<DurationEvent> result = new LinkedList<DurationEvent>();    	
    	String selectSQL = "SELECT t2.id, t1.name, t1.date, t2.initialTime, t2.finalTime, t2.foreign_id FROM "
    			+ "durationEventTable t2 INNER JOIN simpleEventTable t1 ON t2.foreign_id = t1.id;";
        
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {
            	             	   
            	   result.add(new DurationEvent(resultSet.getInt("foreign_id"), ParseData.convertStringToDate(resultSet.getString("date")),
            			   resultSet.getString("name"), resultSet.getString("initialTime"), resultSet.getString("finalTime")));
               }
        }
           return result;
    }
    

	public DurationEvent readDurationEventTable(int id) {
		String selectSQL = "SELECT t1.name, t1.date, t2.initialTime, t2.finalTime, t2.foreign_id FROM durationeventtable t2 INNER JOIN "
        		+ "simpleeventtable t1 ON t2.foreign_id = t1.id  WHERE t2.foreign_id = " + id ;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {
            	   //Print
            	   System.out.println("foreign_id: " + resultSet.getInt("foreign_id"));
            	   return new DurationEvent(resultSet.getInt("foreign_id"), ParseData.convertStringToDate(resultSet.getString("date")),
            			   resultSet.getString("name"), resultSet.getString("initialTime"), resultSet.getString("finalTime"));
               }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
	}

    public boolean updateDurationEvent(DurationEvent event, int foreignId) throws SQLException {      
        String updateSQL = "UPDATE durationEventTable SET initialTime = ?, finalTime = ? WHERE foreign_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, event.getInitialTime());
            preparedStatement.setString(2, event.getFinalTime());
            preparedStatement.setInt(3, event.getId());
            int hourEvent = preparedStatement.executeUpdate();
            
            boolean simpleEvent = updateSimpleEvent(event, foreignId);    
            
            if(hourEvent > 0 && simpleEvent) {
            	return true;
            }else {
            	return false;
            }
        }
    }

    public boolean deleteDurationEvent(int id) throws SQLException {
        String deleteSQL = "DELETE FROM durationEventTable WHERE foreign_id = ?";
        int rows = -1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);
            rows = preparedStatement.executeUpdate();
        }
        
        if(rows > 0) {
        	return true;
        }
        else {
        	return false;
        }
    }
    
    //Other important methods
    public int findIdSimpleEvent(String date, String name) throws SQLException {
        int id = -1; // Default value if no match is found
        String selectSQL = "SELECT id FROM simpleEventTable WHERE name = ? AND date = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }
        }

        return id;
    }

}

