package main.database.course;

import main.models.CourseModel;
import main.startUpApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
            java.sql.Date sqlDate = new java.sql.Date(courseModel.getDate().getTime());
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
}
