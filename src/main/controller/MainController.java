package main.controller;

import main.business_logic.DefaultLogic;
import main.controller.course.CourseController;
import main.controller.infrastructure.InfrastructureController;
import main.controller.staff.StaffController;
import main.controller.student.StudentController;
import main.ui.MAIN;
import main.ui.course.COURSE;
import main.ui.student.STUDENT;
import main.ui.infrastructure.INFRASTRUCTURE;
import main.controller.infrastructure.InfrastructureController;
import main.ui.staff.STAFF;

public class MainController {

    public static void initializeMainMenu () {
        System.out.print(MAIN.MAIN_MENU);
        handleInitialMainMenuInput();
    }
    public static void handleInitialMainMenuInput() {

        try {
            // Scanner Input
            String input = DefaultLogic.handleInput();
            // Convert to int for switch statement
            int convertInputToInteger = Integer.parseInt(input);


            switch(convertInputToInteger) {
                case 1: // Course
                    // print menu
                    System.out.print(COURSE.MAIN_MENU);
                    // print menu logic
                    CourseController.courseMenu();
                    break;
                case 2: // Student

                    StudentController.studentMenu();
                    break;
                case 3: // Garage
                    break;
                case 4: // Staff
                    StaffController.staffMenu();
                    break;
                case 5: // Infrastructure
                    // print menu

                    // print menu logic
                    InfrastructureController.infrastructureMenu();
                    break;
                default:
                    InfrastructureController.infrastructureMenu();
            }
        }catch (Exception e) {

        }

    }
}
