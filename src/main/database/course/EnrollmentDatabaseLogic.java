package main.database.course;

import main.controller.course.EnrollmentController;
import main.startUpApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class EnrollmentDatabaseLogic {

    private static Connection connection = startUpApplication.connection;


    public static ResultSet getEnrollments() {
        ResultSet resultSet = null;
        try {
            final String query = "SELECT * FROM enrollment";
            final PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();
            System.out.println("List Retrieved.");

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid enrollment retrieval. Please try again.");
            System.out.println(e);
            EnrollmentController.enrollmentMenu();
        }
        return resultSet;
    }


    public static void enrollStudent(String student_id, String course_id) {
        try {
            final String query = "INSERT INTO enrollment(student_id, course_id, test_date, final_score, exercise_1_score, exercise_2_score, exercise_3_score, exercise_4_score, exercise_5_score, course_fee_paid) VALUES (?,?,null,null,null,null,null,null,null, false)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setObject(1, UUID.fromString(student_id));
            preparedStatement.setObject(2, UUID.fromString(course_id));
            preparedStatement.executeUpdate();
            System.out.println("Student enrolled.");

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid enrollment please try again");
            System.out.println(e);
            EnrollmentController.enrollmentMenu();
        }
    }

    public static void unEnrollStudent(String student_id, String course_id) {
        try {
            final String query = "DELETE FROM ENROLLMENT WHERE student_id=? AND course_id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, UUID.fromString(student_id));
            preparedStatement.setObject(2, UUID.fromString(course_id));
            preparedStatement.executeUpdate();
            System.out.println("Student unenrolled.");

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid enrollment please try again");
            System.out.println(e);
            EnrollmentController.enrollmentMenu();
        }
    }
}
