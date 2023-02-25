package main.business_logic;
import main.startUpApplication;

import java.util.Objects;
import java.util.Scanner;

public class DefaultLogic {

    // This handles all user input
    public static String handleInput(){
        String input;
        input = startUpApplication.scanner.nextLine();
        return input.trim();
    }

    public static int convertStringToInteger(String input) {
        return Integer.parseInt(input.trim());
    }

    public static Boolean convertStringToBoolean(String input) {
        if (input.toLowerCase().equals("y")) {
            return true;
        } else {
            return false;
        }
    }
}
