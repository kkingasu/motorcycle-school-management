package main.controller.infrastructure;

import main.business_logic.DefaultLogic;

public class InfrastructureController {

    public static void infrastructureMenu() {
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = DefaultLogic.convertStringToInteger(input);
            switch (inputConvertedToInteger) {
                case 1: // Manage Classrooms
                    //
                    // SubMenuInfrastructureController (input)
                case 2: // Manage Ranges

                default: //
            }
        } catch () {

        }
    }
}
