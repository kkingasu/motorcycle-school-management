package main.database.course;

import main.models.CourseModel;
import main.startUpApplication;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class CourseDatabaseLogic {
    private static Connection connection = startUpApplication.connection;
    public static void submitCreateCourseDataToDatabase(CourseModel courseModel) {
        //TODO: Add logic to submit data to database, should probably make a db factory for doing different request...
        try {
            final String query = "INSERT INTO course(name, description, type, date, cost, range_number, range_am, range_pm) VALUES (?,?,?,?,?,?,?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseModel.getName());
            preparedStatement.setString(2, courseModel.getDescription());
            preparedStatement.setString(3, courseModel.getType());
            Date sqlDate = new Date(courseModel.getDate().getTime());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setFloat(5, courseModel.getCost());
            preparedStatement.setInt(6, courseModel.getRange_number());
            preparedStatement.setBoolean(7, courseModel.getRange_am());
            preparedStatement.setBoolean(8, courseModel.getRange_pm());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Log: FAILED - Create course : submitCreateCourseDataToDatabase().");
        }
        System.out.println("Submitting Course Data......");
    }

    public static ResultSet seeAllCourses() throws SQLException {
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try{
            final String query = "SELECT * FROM course";
            preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();
            return resultSet;

        }catch (Exception e) {
            System.out.println("LOG: ERROR - seeAllCourses() in CourseDataBaseLogic");
            return null;
        }
    }

    public static ResultSet getCourseForEditing(UUID course_id){
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try{
            final String query = "Select * From course Where course_id = ?";
            preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setObject(1, course_id);
            resultSet = preparedStatement.executeQuery();
            return resultSet;

        }catch (Exception e) {
            System.out.println("LOG: ERROR - getCourseForEditing() in CourseDataBaseLogic");
            return null;
        }
    }
}
