package main.database.course;

import main.controller.course.AssignmentController;
import main.controller.course.EnrollmentController;
import main.startUpApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class AssignmentDatabaseLogic {

    private static Connection connection = startUpApplication.connection;

    public static ResultSet getAllStaff(){
        ResultSet resultSet = null;
        try {
            final String query = "SELECT staff_id, person.name FROM staff JOIN person ON person.person_id=staff.staff_id";
            final PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid staff retrieval. Please try again.");
            System.out.println(e);
            AssignmentController.assignmentMenu();
        }
        return resultSet;
    }

    public static ResultSet getAllCoaches(){
        ResultSet resultSet = null;
        try {
            final String query = "SELECT person.name, person.person_id, coaches.course_id FROM coaches JOIN person ON person.person_id=coaches.person_id";
            final PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid staff retrieval. Please try again.");
            System.out.println(e);
            AssignmentController.assignmentMenu();
        }
        return resultSet;
    }

    public static void assignCoach(String staff_id, String course_id, String role_type){
        try {
            final String query = "INSERT INTO coaches(person_id, course_id, role_type) VALUES (?,?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setObject(1, UUID.fromString(staff_id));
            preparedStatement.setObject(2, UUID.fromString(course_id));
            preparedStatement.setString(3, role_type);
            preparedStatement.executeUpdate();
            System.out.println("Coach Assigned Successfully.");

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid assigning of coach please try again");
            System.out.println(e);
        }
    }

    public static void removeCoach(String person_id, String course_id){
        try {
            final String query = "DELETE FROM coaches WHERE person_id=? AND course_id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setObject(1, UUID.fromString(person_id));
            preparedStatement.setObject(2, UUID.fromString(course_id));
            preparedStatement.executeUpdate();
            System.out.println("Coach Removed Successfully.");

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid removal of coach please try again");
            System.out.println(e);
        }
    }

    public static ResultSet getAllClassrooms(){
        ResultSet resultSet = null;
        try {
            final String query = "SELECT * FROM classroom";
            final PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid staff retrieval. Please try again.");
            System.out.println(e);
            AssignmentController.assignmentMenu();
        }
        return resultSet;
    }

    public static ResultSet getAllClassroomAssignments(){
        ResultSet resultSet = null;
        try {
            final String query = "SELECT course.name, course.date, course.course_id, classroom_assignment.classroom_id FROM classroom_assignment JOIN course on course.course_id=classroom_assignment.course_id";
            final PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid staff retrieval. Please try again.");
            System.out.println(e);
            AssignmentController.assignmentMenu();
        }
        return resultSet;
    }

    public static void assignClassroom(String course_id, String classroom_id){
        try {
            final String query = "INSERT INTO classroom_assignment(course_id, classroom_id) VALUES (?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setObject(1, UUID.fromString(course_id));
            preparedStatement.setString(2, classroom_id);
            preparedStatement.executeUpdate();
            System.out.println("Classroom-assignment Assigned Successfully.");

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid assigning of classroom-assignment please try again");
            System.out.println(e);
        }
    }

    public static void removeClassroomAssignment(String course_id, String classroom_id){
        try {
            final String query = "DELETE FROM classroom_assignment WHERE course_id=? AND classroom_id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setObject(1, UUID.fromString(course_id));
            preparedStatement.setString(2, classroom_id);
            preparedStatement.executeUpdate();
            System.out.println("Classroom-assignment Removed Successfully.");

        } catch (Exception e) {
            System.out.println("LOG: ERROR - Invalid removal of classroom-assignment please try again");
            System.out.println(e);
        }
    }
}
