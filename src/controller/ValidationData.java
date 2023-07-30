package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class ValidationData {
	
	public static boolean validationDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false); // Strict parsing

        try {
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
	}
	
    public static boolean validationTime(String timeString) {
        // Regular expression to match the "HH:mm" format
        String timePattern = "^([01]\\d|2[0-3]):[0-5]\\d$";

        // Use Pattern.matches() to check if the timeString matches the pattern
        return Pattern.matches(timePattern, timeString);
    }
	
	public static boolean validationChoice(int choice) {
		if(choice < -1 || choice > 4) {
			return false;
		}
		else {
			return true;
		}
	}

	public static boolean validationTypeEvent(int choice) {
		if(choice < -1 || choice > 3) {
			return false;
		}
		else {
			return true;
		}
	}

	public static boolean validationTypeShowEvent(int choice) {
		if(choice < -1 || choice > 5) {
			return false;
		}
		else {
			return true;
		}
	}
}
