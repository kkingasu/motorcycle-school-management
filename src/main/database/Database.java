package main.database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

public class Database {
    static Connection connection = null;
    public static Connection connectToDatabase()  {
        // Open a connection
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
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Log: Exception -" + e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return connection;
    }

    public static ResultSet modifyColumnByUniqueId(String type,String tableName, String columnName,String newValue, String uniqueKeyColumnName, String uniqueKey) throws SQLException {
        ResultSet resultSet = null;
        try{
            if (newValue != null || newValue.length() > 0) {
                PreparedStatement preparedStatement;
                String query = "UPDATE " + tableName + " SET " + columnName + " = ? " + " WHERE " + uniqueKeyColumnName + " = ?";

                // String query2 = "SELECT * FROM ? WHERE ? = ?";
                preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                preparedStatement.setObject(2, UUID.fromString(uniqueKey));

                if (type.toLowerCase().equals("string")) {
                    preparedStatement.setString(1, newValue);
                }
                if (type.toLowerCase().equals("int")) {
                    int newValueConvertedToInteger = Integer.parseInt(newValue);
                    preparedStatement.setInt(1, newValueConvertedToInteger);
                }
                if (type.toLowerCase().equals("float")) {
                    Float newValueConvertedToFloat = Float.parseFloat(newValue);
                    preparedStatement.setFloat(1, newValueConvertedToFloat);
                }
                if (type.toLowerCase().equals("date")) {
                    java.util.Date newValueConvertedToDate = new SimpleDateFormat("MM/dd/yyyy").parse(newValue);
                    preparedStatement.setDate(1, new Date(newValueConvertedToDate.getTime()));
                }
                if (type.toLowerCase().equals("boolean")) {
                    boolean newValueConvertedToBoolean = Boolean.parseBoolean(newValue);
                    preparedStatement.setBoolean(1, newValueConvertedToBoolean);
                }
                preparedStatement.executeUpdate();
            }
        }catch(Exception e) {
            System.out.println("LOG: ERROR - modifyCourse() in CourseDatabaseLogic.java");
            System.out.println(e);
        }
        if (resultSet != null && resultSet.next()) {
            return resultSet;
        } else {
            return null;
        }
    }

    public static void deleteRowByUniqueIdWithTableName(String tableName, String uniqueKeyName, String uniqueKey) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM " + tableName + " WHERE " + uniqueKeyName + " = ?";
        try {
            preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setObject(1, UUID.fromString(uniqueKey));
            preparedStatement.executeUpdate();
            System.out.println("Deleted row successfully with id of: " + uniqueKey + ", from table " + tableName + ".");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}