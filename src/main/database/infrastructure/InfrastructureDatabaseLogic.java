package main.database.infrastructure;
import main.database.SQLPrinter;
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
            System.out.println("Submitted Classroom Data.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitCreateClassroomDataToDatabase().");
        }
    }

    public static void submitDisplayAllClassroomDataToDatabase() {
        try {
            final String query =    "SELECT *  FROM classroom C";
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);

            if (rs != null) {
                SQLPrinter.printResultSet(rs);
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitDisplayAllClassroomDataToDatabase().");
        }
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
            System.out.println("Submitted Classroom Data.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitUpdateClassroomDataToDatabase().");
        }
    }

    public static void submitDeleteClassroomDataToDatabase(InfrastructureClassroomModel classroomModel) {
        try {
            final String query =    "DELETE FROM classroom WHERE classroom_id =?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, classroomModel.getClassroom_id());
            preparedStatement.executeUpdate();
            System.out.println("Deleted Course Data.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Log: FAILED - Create course : submitDeleteClassroomDataToDatabase().");
        }
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
            System.out.println("Submitted Range Data.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitCreateRangeDataToDatabase().");
        }
    }

    public static void submitDisplayAllRangeDataToDatabase() {
        try {
            final String query = "SELECT * FROM range";
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);

            if (rs != null) {
                SQLPrinter.printResultSet(rs);
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitDisplayAllRangeDataToDatabase().");
        }
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
            System.out.println("Submitted Range Data.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitUpdateRangeDataToDatabase().");
        }
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
