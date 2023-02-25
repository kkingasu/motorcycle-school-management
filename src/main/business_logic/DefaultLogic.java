package main.business_logic;
import main.startUpApplication;

import java.util.Scanner;

public class DefaultLogic {

    // This handles all user input
    public static String handleInput(){
        String input = "";
        input = startUpApplication.scanner.nextLine();
        return input.trim();
    }
}
