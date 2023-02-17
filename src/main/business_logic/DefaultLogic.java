package main.business_logic;
import main.startUpApplication;

import java.util.Scanner;

public class DefaultLogic {
    public static String handleInput(){
        String input = "";
        input = startUpApplication.scanner.nextLine();
        return input;
    }
}
