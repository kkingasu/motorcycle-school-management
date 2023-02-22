package main.database.infrastructure;
import main.models.infrastructure.InfrastructureClassroomModel;
import main.models.infrastructure.InfrastructureRangeModel;
import main.startUpApplication;

import java.sql.*;

public class InfrastructureDatabaseLogic {

    static String spacePipe = " | ";

    private static Connection connection = startUpApplication.connection;

    public static void submitCreateClassroomDataToDatabase(InfrastructureClassroomModel classroomModel) {

        try {
            final String query =    "INSERT INTO " +
                                    "classroom(classroom_id, is_available) " +
                                    "VALUES (?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, classroomModel.getClassroom_id());
            preparedStatement.setBoolean(2, classroomModel.getIs_available());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitCreateClassroomDataToDatabase().");
        }
        System.out.println("Submitting Course Data......");
    }

    public static void submitDisplayAllClassroomDataToDatabase() {
        try {
            final String query =    "SELECT *  FROM classroom C";
//            final String query =    "SELECT * " +
//                                    "FROM classroom C" +
//                                    "ORDER BY C.classroom_id ASC";
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);

            // Get database metadata
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();
            if (rs != null) {
                System.out.print(formatOutput("classroom_id") + spacePipe);
                System.out.print(formatOutput("is_available") + spacePipe);
                System.out.println("\n-----------------------------------------");
                while(rs.next()){
                    String resultSetClassroomID = rs.getString("classroom_id");
                    String resultSetIsAvailable = rs.getString("is_available");
                    System.out.print(formatOutput(resultSetClassroomID) + spacePipe);
                    System.out.print(formatOutput(resultSetIsAvailable) + spacePipe);
                    System.out.println();
                }
                System.out.println("\n-----------------------------------------");
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitDisplayAllClassroomDataToDatabase().");
        }
        System.out.println("Displaying Classroom Data......");
    }

    public static void submitUpdateClassroomDataToDatabase(InfrastructureClassroomModel classroomModel) {
        try {
            final String query =    "UPDATE classroom " +
                                    "SET is_available =?" +
                                    "WHERE classroom_id =?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, classroomModel.getIs_available());
            preparedStatement.setString(2, classroomModel.getClassroom_id());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitUpdateClassroomDataToDatabase().");
        }
        System.out.println("Submitting Course Data......");
    }

    public static void submitDeleteClassroomDataToDatabase(InfrastructureClassroomModel classroomModel) {
        try {
            final String query =    "DELETE FROM classroom WHERE classroom_id =?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, classroomModel.getClassroom_id());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Log: FAILED - Create course : submitDeleteClassroomDataToDatabase().");
        }
        System.out.println("Deleting Course Data......");
    }

    public static void submitCreateRangeDataToDatabase(InfrastructureRangeModel rangeModel) {

        try {
            final String query =    "INSERT INTO " +
                                    "range(range_number, is_available) " +
                                    "VALUES (?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rangeModel.getRange_number());
            preparedStatement.setBoolean(2, rangeModel.getIs_available());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitCreateRangeDataToDatabase().");
        }
        System.out.println("Submitting Range Data......");
    }

    public static void submitDisplayAllRangeDataToDatabase() {
        try {
            final String query = "SELECT * FROM range";
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);

            // Get database metadata
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();
            if (rs != null) {
                System.out.print(formatOutput("range_number") + spacePipe);
                System.out.print(formatOutput("is_available") + spacePipe);
                System.out.println("\n-----------------------------------------");
                while(rs.next()){
                    String resultSetRangeNumber = rs.getString("range_number");
                    String resultSetIsAvailable = rs.getString("is_available");
                    System.out.print(formatOutput(resultSetRangeNumber) + spacePipe);
                    System.out.print(formatOutput(resultSetIsAvailable) + spacePipe);
                    System.out.println();
                }
                System.out.println("\n-----------------------------------------");
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitDisplayAllRangeDataToDatabase().");
        }
        System.out.println("Displaying Range Data......");
    }

    public static void submitUpdateRangeDataToDatabase(InfrastructureRangeModel rangeModel) {
        try {
            final String query =    "UPDATE range " +
                                    "SET is_available =?" +
                                    "WHERE range_number =?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, rangeModel.getIs_available());
            preparedStatement.setInt(2, rangeModel.getRange_number());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitUpdateRangeDataToDatabase().");
        }
        System.out.println("Submitting Range Data......");
    }

    public static void submitDeleteRangeDataToDatabase(InfrastructureRangeModel rangeModel) {
        try {
            final String query =    "DELETE FROM range WHERE range_number =?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rangeModel.getRange_number());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitDeleteRangeDataToDatabase().");
        }
        System.out.println("Deleting Range Data......");
    }


    public static String formatOutput(String output) {
        String indent = "                  "; // 20 spaces
        return output += indent.substring(0, indent.length() - output.length());
    }
}
