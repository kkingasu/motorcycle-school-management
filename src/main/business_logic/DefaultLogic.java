package main.business_logic;
import main.startUpApplication;

import java.util.Objects;
import java.util.Scanner;

public class DefaultLogic {

    // This handles all user input
    public static String handleInput(){
        String input = "";
        input = startUpApplication.scanner.nextLine();
        return input;
    }

    public static int convertStringToInteger(String input) {
        return Integer.parseInt(input);
    }

    public static Boolean convertStringToBoolean(String input) {
        if (input.equals("Y") || input.equals("y")) {
            return true;
        } else {
            return false;
        }
    }
}
