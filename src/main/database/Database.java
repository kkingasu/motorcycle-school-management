package main.database;

import java.sql.*;
import java.util.Properties;

public class Database {

    public static Connection connectToDatabase() {
        // Open a connection
        Connection connection = null;
        String bitApiKey = "v2_3zDre_5SGRF56MABLK2E8SSt8CBW4";
        String bitDB = "kking45.bike_school";
        String bitUser = "kking45";
        String bitHost = "db.bit.io";
        String bitPort = "5432";
        String connectionURL = "jdbc:postgresql://" + bitHost + ":" + bitPort + "/" + bitDB;
        System.out.println(connectionURL);
        Properties props = new Properties();
        props.setProperty("sslmode", "require"); // if verify-full fails, use 'require'
        props.setProperty("user", bitUser);
        props.setProperty("password", bitApiKey);
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionURL, props);
            if(connection != null) {
                System.out.println("Log: Success - Database connection successful");
            } else {
                System.out.println("Log: Failed - Database connection failure. See exception below.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Log: Exception -" + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return connection;
    }
}