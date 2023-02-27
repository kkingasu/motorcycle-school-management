package main.controller.course;

import main.business_logic.DefaultLogic;
import main.database.SQLPrinter;
import main.database.course.EnrollmentDatabaseLogic;
import main.database.student.StudentDatabaseLogic;
import main.ui.course.COURSE;

import java.sql.ResultSet;

public class EnrollmentController {

    public static void enrollmentMenu(){
        System.out.print(COURSE.MANAGE_ENROLLMENTS);
        String input = DefaultLogic.handleInput();
        int inputConvertedToInteger = Integer.parseInt(input);
        switch (inputConvertedToInteger) {
            case 1:
                enrollStudent();
                enrollmentMenu();
                break;
            case 2:
                unenrollStudent();
                enrollmentMenu();
                break;
            default:
                CourseController.coursesAndEnrollmentsMenu();
                break;
        }
    }

    public static void enrollStudent() {
        try {
            System.out.print(COURSE.NEW_ENROLLMENT);
            CourseController.seeAllCourses();
            System.out.print("Enter Course Id: ");
            String course_id = DefaultLogic.handleInput();
            StudentDatabaseLogic.viewAllStudents();
            System.out.print("Enter Student Id: ");
            String student_id = DefaultLogic.handleInput();
            EnrollmentDatabaseLogic.enrollStudent(student_id, course_id);

        } catch (Exception e) {
            System.out.println("Issue with enrolling student");
            System.out.println(e);
        }

    }

    public static void unenrollStudent() {
        try {
            System.out.println(COURSE.ENROLLMENT_LIST);
            ResultSet resultSet = EnrollmentDatabaseLogic.getEnrollments();
            SQLPrinter.printResultSet(resultSet);
            System.out.print("Enter Student Id: ");
            String student_id = DefaultLogic.handleInput();
            System.out.print("Enter Course Id: ");
            String course_id = DefaultLogic.handleInput();
            EnrollmentDatabaseLogic.unEnrollStudent(student_id, course_id);
            //
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
