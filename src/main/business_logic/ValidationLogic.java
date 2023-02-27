package main.business_logic;


import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ValidationLogic {

    public static class InvalidDateFormatException extends Exception {
        public InvalidDateFormatException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static Date validateDateInput(String date) throws InvalidDateFormatException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            java.util.Date utilDate = dateFormat.parse(date);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please use the format yyyy-MM-dd:");
        }
    }
}
