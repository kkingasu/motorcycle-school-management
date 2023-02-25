package main.controller.infrastructure;

import main.business_logic.DefaultLogic;
import main.ui.infrastructure.INFRASTRUCTURE;

public class InfrastructureController {

    public static void infrastructureMenu() {
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = DefaultLogic.convertStringToInteger(input);
            switch (inputConvertedToInteger) {
                case 1: // Manage Classrooms
                    // Print manage classrooms menu
                    System.out.println(INFRASTRUCTURE.MANAGE_CLASSROOMS);
                    InfrastructureSubMenuController.infrastructureClassroomSubMenu();
                    break;
                case 2: // Manage Ranges
                    // Print manage ranges menu
                    System.out.println(INFRASTRUCTURE.MANAGE_RANGES);
                    InfrastructureSubMenuController.infrastructureRangeSubMenu();
                    break;
                default: //
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
