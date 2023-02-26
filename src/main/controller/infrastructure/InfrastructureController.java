package main.controller.infrastructure;

import main.business_logic.DefaultLogic;
import main.controller.MainController;
import main.ui.infrastructure.INFRASTRUCTURE;

public class InfrastructureController {

    public static void infrastructureMenu() {
        System.out.print(INFRASTRUCTURE.MAIN_MENU);
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = DefaultLogic.convertStringToInteger(input);
            switch (inputConvertedToInteger) {
                case 1: // Manage Classrooms
                    InfrastructureSubMenuController.infrastructureClassroomSubMenu();
                    break;
                case 2: // Manage Ranges
                    InfrastructureSubMenuController.infrastructureRangeSubMenu();
                    break;
                case 3: // Return to main menu
                    MainController.initializeMainMenu();
                    break;
                default:
                    MainController.initializeMainMenu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
