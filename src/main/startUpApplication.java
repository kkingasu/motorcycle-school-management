package main;

import main.controller.MainController;
import main.database.Database;
import main.ui.MAIN;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class startUpApplication {
    public static Scanner scanner;
    public static void main(String[] args) {
        // Initializing global scanner
        scanner = new Scanner(System.in);
        // Initialize DB
        // Connection connection = Database.connectToDatabase();
        // Initialize Main menu
        System.out.print(MAIN.MAIN_MENU);
        MainController.handleInitialMainMenuInput();
        scanner.close();
    }
}
