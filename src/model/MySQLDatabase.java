package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import controller.ParseData;

public class MySQLDatabase {
	
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
        String selectSQL = "SELECT * FROM hourEventTable";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int idEvent = resultSet.getInt("foreign_id");
                String time = resultSet.getString("time");
                String name = null;
                String date = null;  
                
                String selectSQLEvent = "SELECT * FROM simpleEventTable WHERE id=" + idEvent;
                try (PreparedStatement preparedStatementEvent = connection.prepareStatement(selectSQLEvent)) {
                	ResultSet resultSetEvent = preparedStatement.executeQuery();
                    while (resultSetEvent.next()) {
                    	name = resultSetEvent.getString("name"); 
                    	date = resultSetEvent.getString("date"); 
                    }
				}
                result.add(new HourEvent(idEvent,date,name,time));
            }
         }
        return result;
    }
    
    public HourEvent readHourEventTable(int id) throws SQLException {
        String selectSQL = "SELECT * FROM hourEventTable WHERE id =" + id;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int idEvent = resultSet.getInt("foreign_id");
                String time = resultSet.getString("time");
                String name = null;
                String date = null;  
                
                String selectSQLEvent = "SELECT * FROM simpleEventTable WHERE id=" + idEvent;
                try (PreparedStatement preparedStatementEvent = connection.prepareStatement(selectSQLEvent)) {
                	ResultSet resultSetEvent = preparedStatement.executeQuery();
                    while (resultSetEvent.next()) {
                    	name = resultSetEvent.getString("name"); 
                    	date = resultSetEvent.getString("date"); 
                    }
				}
                return (new HourEvent(idEvent,date,name,time));
            }
         }
        return null;
    }

    public boolean updateHourEvent(HourEvent event, int foreignId) throws SQLException {
        String updateSQL = "UPDATE hourEventTable SET time = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, event.time);
            preparedStatement.setInt(2, event.getId());
            int hourEvent = preparedStatement.executeUpdate();
            
            boolean simpleEvent = updateSimpleEvent(event, foreignId);    
            
            if(hourEvent > 0 && simpleEvent) {
            	return true;
            }else {
            	return false;
            }
        }
    }

    public void deleteHourEvent(int id) throws SQLException {
        String deleteSQL = "DELETE FROM hourEventTable WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
    
    //CRUD Duration Event
    public boolean createDurationEvent(DurationEvent event) throws SQLException {
    	boolean simpleEvent = createSimpleEvent(event);
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
        String selectSQL = "SELECT * FROM durationEventTable";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {
                   int idEvent = resultSet.getInt("foreign_id");
                   String inicialTime = resultSet.getString("inicialTime");
                   String finalTime = resultSet.getString("finalTime");
                   String name = null;
                   String date = null;  
                   
                   String selectSQLEvent = "SELECT * FROM simpleEventTable WHERE id=" + idEvent;
                   try (PreparedStatement preparedStatementEvent = connection.prepareStatement(selectSQLEvent)) {
                   	ResultSet resultSetEvent = preparedStatement.executeQuery();
                       while (resultSetEvent.next()) {
                       	name = resultSetEvent.getString("name"); 
                       	date = resultSetEvent.getString("date"); 
                       }
   				}
                   result.add(new DurationEvent(idEvent,date,name,inicialTime, finalTime));
               }
            }
           return result;
    }

    public boolean updateDurationEvent(DurationEvent event, int foreignId) throws SQLException {      
        String updateSQL = "UPDATE duratationEventTable SET incialTime = ?, finalTime = ? WHERE id = ?";
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

    public void deleteDurationEvent(int id) throws SQLException {
        String deleteSQL = "DELETE FROM durationEventTable WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
    
    //Other important methods
    public int findIdSimpleEvent(String date, String name) throws SQLException {
        String selectSQL = "SELECT id FROM simpleEventTable WHERE  date = ?, name = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, name);
            return preparedStatement.executeUpdate();
        }
    }
}

