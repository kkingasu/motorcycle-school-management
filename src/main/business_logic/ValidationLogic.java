package main.business_logic;


import java.util.Date;

public class ValidationLogic {
    public static Date validateDateInput(String date) {
        Date parsedDate = new Date(date);
        if (parsedDate != null) {
            return parsedDate;
        } else {
            return null;
        }
    }
}
