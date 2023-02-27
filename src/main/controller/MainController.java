package main.controller;

import main.business_logic.DefaultLogic;
import main.controller.course.CourseController;
import main.controller.garage.GarageController;
import main.ui.course.COURSE;
import main.ui.garage.GARAGE;
import main.controller.student.StudentController;
import main.ui.MAIN;
import main.controller.infrastructure.InfrastructureController;

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
                    // print menu logic
                    CourseController.coursesAndEnrollmentsMenu();
                    break;
                case 2: // Student

                    StudentController.studentMenu();
                    break;
                case 3: // Garage
                    //print menu logic
                    GarageController.garageMenu();
                    break;
                case 4: // Staff
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
