package main;
import main.controller.MainController;
import main.database.Database;
import java.sql.Connection;
import java.util.Scanner;

public class startUpApplication {
    public static Scanner scanner;

    public static Connection connection;
    public static void main(String[] args) {
        // Global scanner
        scanner = new Scanner(System.in);
        // Initialize DB
        connection = Database.connectToDatabase();
        // Initialize Main menu
        MainController.initializeMainMenu();
        scanner.close();
    }
}
