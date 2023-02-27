package main.controller.course;

import main.business_logic.DefaultLogic;

import main.controller.infrastructure.InfrastructureSubMenuController;
import main.database.SQLPrinter;
import main.database.course.AssignmentDatabaseLogic;
import main.ui.course.COURSE;

import java.sql.ResultSet;

public class AssignmentController {

    public static void assignmentMenu() {
        System.out.print(COURSE.MANAGE_ASSIGNMENTS);
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch(inputConvertedToInteger) {
                case 1:
                    assignCoachTeacher();
                    break;
                case 2:
                    removeCoachTeacher();
                    break;
                case 3:
                    classRoomAssignment();
                    break;
                case 4:
                    removeClassroomAssignment();
                    break;
                default:
                    CourseController.coursesAndEnrollmentsMenu();

            }
        } catch (Exception e)
        {

        }
    }

    public static void seeAllStaff() {
        try {
            ResultSet resultSet = AssignmentDatabaseLogic.getAllStaff();
            SQLPrinter.printResultSet(resultSet);
        } catch (Exception e) {
            System.out.println("See all staff failed, try again.");
            assignmentMenu();
        }
    }

    public static void seeAllCoaches() {
        try {
            ResultSet resultSet = AssignmentDatabaseLogic.getAllCoaches();
            SQLPrinter.printResultSet(resultSet);
        } catch (Exception e) {
            System.out.println("See all staff failed, try again.");
            assignmentMenu();
        }
    }


    public static void assignCoachTeacher() {
        try {
            seeAllStaff();
            System.out.print("Input staff_id:");
            String staff_id = DefaultLogic.handleInput();
            CourseController.seeAllCourses();
            System.out.print("Input course id:");
            String course_id = DefaultLogic.handleInput();
            System.out.print("Input role type(Coach or Teacher):");
            String role_type = DefaultLogic.handleInput();
            AssignmentDatabaseLogic.assignCoach(staff_id, course_id, role_type);
            assignmentMenu();
        } catch (Exception e) {
            System.out.println("LOG: ERROR - while assigning professor, try again.");
            System.out.printf("LOG: EXCEPTION: " + e);
            assignmentMenu();
        }

    }

    public static void removeCoachTeacher() {
        try {
            seeAllCoaches();
            System.out.print("Input person_id:");
            String staff_id = DefaultLogic.handleInput();
            System.out.print("Input course id:");
            String course_id = DefaultLogic.handleInput();
            AssignmentDatabaseLogic.removeCoach(staff_id, course_id);
            assignmentMenu();
        } catch (Exception e) {
            System.out.println("LOG: ERROR - while removing coach, try again.");
            System.out.printf("LOG: EXCEPTION: " + e);
            assignmentMenu();
        }
    }

    public static void seeAllClassrooms() {
        try {
            ResultSet resultSet = AssignmentDatabaseLogic.getAllClassrooms();
            SQLPrinter.printResultSet(resultSet);
        } catch (Exception e) {
            System.out.println("See all staff failed, try again.");
            assignmentMenu();
        }
    }

    public static void seeAllClassroomAssignments() {
        try {
            ResultSet resultSet = AssignmentDatabaseLogic.getAllClassroomAssignments();
            SQLPrinter.printResultSet(resultSet);
        } catch (Exception e) {
            System.out.println("See all staff failed, try again.");
            assignmentMenu();
        }
    }
    public static void classRoomAssignment() {

        try {
            CourseController.seeAllCourses();
            System.out.print("Input course id:");
            String course_id = DefaultLogic.handleInput();
            seeAllClassrooms();
            System.out.print("Input classroom id:");
            String classroom_id = DefaultLogic.handleInput();
            AssignmentDatabaseLogic.assignClassroom(course_id, classroom_id);
            assignmentMenu();
        } catch (Exception e) {
            System.out.println("LOG: ERROR - while removing coach, try again.");
            System.out.printf("LOG: EXCEPTION: " + e);
            assignmentMenu();
        }

    }

    public static void removeClassroomAssignment() {
        try {
            seeAllClassroomAssignments();
            System.out.print("Input course id:");
            String course_id = DefaultLogic.handleInput();
            System.out.print("Input classroom id:");
            String classroom_id = DefaultLogic.handleInput();
            AssignmentDatabaseLogic.removeClassroomAssignment(course_id, classroom_id);
            assignmentMenu();
        } catch (Exception e) {
            System.out.println("LOG: ERROR - while removing coach, try again.");
            System.out.printf("LOG: EXCEPTION: " + e);
            assignmentMenu();
        }
    }
}
