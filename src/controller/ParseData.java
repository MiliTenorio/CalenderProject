package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseData {
	
	public static Date convertStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
	
    public static String convertDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(date);
        return dateString;
    }
}
