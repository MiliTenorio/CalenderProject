package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
}
