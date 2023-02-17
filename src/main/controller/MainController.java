package main.controller;

import main.business_logic.DefaultLogic;
import main.controller.course.CourseController;
import main.ui.course.COURSE;

public class MainController {
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
                    break;
                case 3: // Garage
                    break;
                case 4: // Staff
                    break;
                case 5: // Infrastructure
                    break;
                default:
                    // return to main menu
            }
        }catch (Exception e) {

        }

    }
}
