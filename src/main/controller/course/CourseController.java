package main.controller.course;

import main.business_logic.DefaultLogic;
import main.controller.MainController;
import main.database.Database;
import main.database.SQLPrinter;
import main.database.course.CourseDatabaseLogic;
import main.ui.course.COURSE;
import main.models.CourseModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class CourseController {

    public static void coursesAndEnrollmentsMenu() {
        System.out.print(COURSE.MAIN_MENU);
        try
        {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch(inputConvertedToInteger) {
                case 1: // Course Main Menu
                   // handleCreateCourseInput();
                    courseManagementMenu();
                    break;
                case 2: // Enrollments Main Menu
                    System.out.println(COURSE.MANAGE_ENROLLMENTS);
                    break;
                case 3: // Assignments Main Menu
                    System.out.println(COURSE.MANAGE_ASSIGNMENTS);
                    break;
                default:
                    MainController.initializeMainMenu();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static Date validateDateInput(String date) {
        Date parsedDate = new Date(date);
        if (parsedDate != null) {
            return parsedDate;
        } else {
            return null;
        }
    }

    public static void createCourse() {
        System.out.println(COURSE.CREATE_COURSE);
        CourseModel courseModel = new CourseModel();
        System.out.print("Enter Course Name:");
        courseModel.setName(DefaultLogic.handleInput());
        System.out.print("Enter Course Description:");
        courseModel.setDescription(DefaultLogic.handleInput());
        System.out.print("Enter Course Type:");
        courseModel.setType(DefaultLogic.handleInput());
        // Print Enter Course Date
        System.out.print("Enter Course Date(mm/dd/yyyy):");
        // Get user input
        String dateInput = DefaultLogic.handleInput();
        // Send input to validator
        Date dateFormat = null;
        while (dateFormat == null) {
            try {
                dateFormat = validateDateInput(dateInput);
            }catch (Exception e) {
                System.out.print("Try again, please use format(yyyy-MM-dd):");
                dateInput = DefaultLogic.handleInput();
                dateFormat = validateDateInput(dateInput);
            }
        }
        courseModel.setDate(dateFormat);
        System.out.print("Enter Course Cost:");
        courseModel.setCost(Float.parseFloat(DefaultLogic.handleInput()));
        System.out.print("Enter Range Number:");
        courseModel.setRange_number(Integer.parseInt(DefaultLogic.handleInput()));
        System.out.print("Enter True or False if Range is AM:");
        courseModel.setRange_am(Boolean.parseBoolean(DefaultLogic.handleInput()));
        System.out.print("Enter True or False if Range is PM:");
        courseModel.setRange_pm(Boolean.parseBoolean(DefaultLogic.handleInput()));
        CourseDatabaseLogic.submitCreateCourseDataToDatabase(courseModel);

        CourseController.coursesAndEnrollmentsMenu();

    }
    public static void courseManagementMenu() throws SQLException {
        System.out.print(COURSE.MANAGE_COURSES);
        String input = DefaultLogic.handleInput();
        int inputConvertedToInteger = Integer.parseInt(input);
        switch(inputConvertedToInteger) {
            case 1: // See all courses
                    seeAllCourses();
                    courseManagementMenu();
                break;
            case 2:// Create New Course
                    createCourse();
                    courseManagementMenu();
                break;
            case 3:// Modify Course
                    modifyCourse(null);
                break;
            case 4:// Delete Course
                deleteCourse();
                courseManagementMenu();
                break;
            case 5:// Return to Courses and Enrollments Menu
                coursesAndEnrollmentsMenu();
                break;
            default:

        }
    }

    public static void seeAllCourses() throws SQLException {
        try {
            System.out.println(COURSE.COURSE_LIST);
            ResultSet resultSet = CourseDatabaseLogic.seeAllCourses();
            SQLPrinter.printResultSet(resultSet);
        }catch (Exception e) {
            System.out.println("LOG: ERROR - seeAllCourses() in CourseController.java");
        }
    }

    public static void modifyCourse(String uuidInput) {
        try {
            if (uuidInput == null) {
                System.out.print(COURSE.MODIFY_COURSE_TITLE);
                seeAllCourses();
                System.out.print(COURSE.MODIFY_COURSE_ENTER_ID);
                uuidInput = DefaultLogic.handleInput();
            }
            String newValue;
            UUID inputConvertedToUUID = UUID.fromString(uuidInput);
            ResultSet resultSet = CourseDatabaseLogic.getCourseForEditing(inputConvertedToUUID);
            SQLPrinter.printResultSet(resultSet);
            System.out.print(COURSE.EDIT_COURSE);
            String input2 = DefaultLogic.handleInput();
            int inputConvertedToInteger2 = Integer.parseInt(input2);
            switch(inputConvertedToInteger2) {
                case 1: // Edit Name
                    System.out.print(COURSE.EDIT_NAME);
                    newValue = DefaultLogic.handleInput();
                    Database.modifyColumnByUniqueId("string", "course", "name", newValue, "course_id", uuidInput);
                    break;
                case 2:// Edit Description
                    System.out.print(COURSE.EDIT_DESCRIPTION);
                    newValue = DefaultLogic.handleInput();
                    Database.modifyColumnByUniqueId("string", "course", "description", newValue, "course_id", uuidInput);
                    break;
                case 3:// Edit Type
                    System.out.print(COURSE.EDIT_TYPE);
                    newValue = DefaultLogic.handleInput();
                    Database.modifyColumnByUniqueId("string", "course", "type", newValue, "course_id", uuidInput);
                    break;
                case 4:// Edit Date
                    System.out.print(COURSE.EDIT_DATE);
                    newValue = DefaultLogic.handleInput();
                    Database.modifyColumnByUniqueId("date", "course", "date", newValue, "course_id", uuidInput);
                    break;
                case 5:// Edit Cost
                    System.out.print(COURSE.EDIT_COST);
                    newValue = DefaultLogic.handleInput();
                    Database.modifyColumnByUniqueId("float", "course", "cost", newValue, "course_id", uuidInput);
                    break;
                case 6:// Edit Range Number
                    System.out.print(COURSE.EDIT_RANGE_NUMBER);
                    newValue = DefaultLogic.handleInput();
                    Database.modifyColumnByUniqueId("int", "course", "range_number", newValue, "course_id", uuidInput);
                    break;
                case 7:// Edit Range Morning
                    System.out.print(COURSE.EDIT_RANGE_AM);
                    newValue = DefaultLogic.handleInput();
                    Database.modifyColumnByUniqueId("boolean", "course", "range_am", newValue, "course_id", uuidInput);
                    break;
                case 8:// Edit Range Evening(True or False)
                    System.out.print(COURSE.EDIT_RANGE_PM);
                    newValue = DefaultLogic.handleInput();
                    Database.modifyColumnByUniqueId("boolean", "course", "range_pm", newValue, "course_id", uuidInput);
                    break;
                case 9:// Return to Course Management
                    courseManagementMenu();
                    break;
                default:
            }
            resultSet = CourseDatabaseLogic.getCourseForEditing(inputConvertedToUUID);
            if (resultSet.next() && resultSet != null) {
                modifyCourse(uuidInput);
                System.out.println("====================================================================");
            }

        }catch (Exception e) {
            System.out.println("LOG: ERROR - modifyCourse() in CourseController.java");
            System.out.println(e);
        }
    }

    public static void deleteCourse() throws SQLException {
        System.out.print(COURSE.DELETE_COURSE);
        seeAllCourses();
        System.out.print(COURSE.DELETE_COURSE_ENTER_ID);
        String uuidInput = DefaultLogic.handleInput();
        Database.deleteRowByUniqueIdWithTableName("course", "course_id", uuidInput);
    }

}
