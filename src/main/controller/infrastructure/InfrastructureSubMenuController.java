package main.controller.infrastructure;

import main.business_logic.DefaultLogic;
import main.controller.MainController;
import main.database.infrastructure.InfrastructureDatabaseLogic;
import main.models.infrastructure.InfrastructureClassroomModel;
import main.models.infrastructure.InfrastructureRangeModel;
import main.ui.infrastructure.INFRASTRUCTURE;

public class InfrastructureSubMenuController {
    public static void infrastructureClassroomSubMenu() {
        System.out.print(INFRASTRUCTURE.MANAGE_CLASSROOMS);
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = DefaultLogic.convertStringToInteger(input);
            switch (inputConvertedToInteger) {
                case 1: // Create Classroom
                    handleCreateClassroomInput();
                    break;
                case 2: // Display all Classroom
                    handleDisplayClassroomsInput();
                    break;
                case 3: // Update Classroom
                    handleUpdateClassroomInput();
                    break;
                case 4: // Delete Classroom
                    handleDeleteClassroomInput();
                    break;
                case 5: // Return to Infrastructure Menu
                    InfrastructureController.infrastructureMenu();
                    break;
                default: //
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void infrastructureRangeSubMenu() {
        System.out.print(INFRASTRUCTURE.MANAGE_RANGES);
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = DefaultLogic.convertStringToInteger(input);
            switch (inputConvertedToInteger) {
                case 1: // Create Range
                    handleCreateRangeInput();
                    break;
                case 2: // Display all Range
                    handleDisplayRangesInput();
                    break;
                case 3: // Update Range
                    handleUpdateRangeInput();
                    break;
                case 4: // Delete Range
                    handleDeleteRangeInput();
                    break;
                case 5: // Return to Infrastructure Menu
                    InfrastructureController.infrastructureMenu();
                    break;
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleCreateClassroomInput() {
        System.out.println(INFRASTRUCTURE.CREATE_CLASSROOM);
        InfrastructureClassroomModel classroomModel = new InfrastructureClassroomModel();
        System.out.print("Enter Classroom Id:");
        classroomModel.setClassroom_id(DefaultLogic.handleInput());
        System.out.print("Enter Classroom Availability (Y/N):");
        classroomModel.setIs_available(DefaultLogic.convertStringToBoolean(DefaultLogic.handleInput()));
        InfrastructureDatabaseLogic.submitCreateClassroomDataToDatabase(classroomModel);
        MainController.initializeMainMenu();
    }

    public static void handleDisplayClassroomsInput() {
        System.out.println(INFRASTRUCTURE.DISPLAY_CLASSROOM);
        InfrastructureDatabaseLogic.submitDisplayAllClassroomDataToDatabase();
        MainController.initializeMainMenu();
    }

    public static void handleUpdateClassroomInput() {
        System.out.println(INFRASTRUCTURE.UPDATE_CLASSROOM);
        InfrastructureClassroomModel classroomModel = new InfrastructureClassroomModel();
        System.out.print("Enter Classroom Id:");
        classroomModel.setClassroom_id(DefaultLogic.handleInput());
        System.out.print("Enter Classroom Availability (Y/N):");
        classroomModel.setIs_available(DefaultLogic.convertStringToBoolean(DefaultLogic.handleInput()));
        InfrastructureDatabaseLogic.submitUpdateClassroomDataToDatabase(classroomModel);
        MainController.initializeMainMenu();
    }

    public static void handleDeleteClassroomInput() {
        System.out.println(INFRASTRUCTURE.DELETE_CLASSROOM);
        InfrastructureClassroomModel classroomModel = new InfrastructureClassroomModel();
        System.out.print("Enter Classroom Id:");
        classroomModel.setClassroom_id(DefaultLogic.handleInput());
        InfrastructureDatabaseLogic.submitDeleteClassroomDataToDatabase(classroomModel);
        MainController.initializeMainMenu();
    }

    public static void handleCreateRangeInput() {
        System.out.println(INFRASTRUCTURE.CREATE_RANGE);
        InfrastructureRangeModel rangeModel = new InfrastructureRangeModel();
        System.out.print("Enter Classroom Id:");
        rangeModel.setRange_number(DefaultLogic.convertStringToInteger(DefaultLogic.handleInput()));
        System.out.print("Enter Classroom Availability (Y/N):");
        rangeModel.setIs_available(DefaultLogic.convertStringToBoolean(DefaultLogic.handleInput()));
        InfrastructureDatabaseLogic.submitCreateRangeDataToDatabase(rangeModel);
        InfrastructureSubMenuController.infrastructureRangeSubMenu();
    }

    public static void handleDisplayRangesInput() {
        System.out.println(INFRASTRUCTURE.DISPLAY_RANGE);
        InfrastructureDatabaseLogic.submitDisplayAllRangeDataToDatabase();
        InfrastructureSubMenuController.infrastructureRangeSubMenu();
    }

    public static void handleUpdateRangeInput() {
        System.out.println(INFRASTRUCTURE.UPDATE_RANGE);
        InfrastructureRangeModel rangeModel = new InfrastructureRangeModel();
        System.out.print("Enter Range Id:");
        rangeModel.setRange_number(DefaultLogic.convertStringToInteger(DefaultLogic.handleInput()));
        System.out.print("Enter Range Availability (Y/N):");
        rangeModel.setIs_available(DefaultLogic.convertStringToBoolean(DefaultLogic.handleInput()));
        InfrastructureDatabaseLogic.submitUpdateRangeDataToDatabase(rangeModel);
        InfrastructureSubMenuController.infrastructureRangeSubMenu();
    }

    public static void handleDeleteRangeInput() {
        System.out.println(INFRASTRUCTURE.DELETE_RANGE);
        InfrastructureRangeModel rangeModel = new InfrastructureRangeModel();
        System.out.print("Enter Range Id:");
        rangeModel.setRange_number(DefaultLogic.convertStringToInteger(DefaultLogic.handleInput()));
        InfrastructureDatabaseLogic.submitDeleteRangeDataToDatabase(rangeModel);
        InfrastructureSubMenuController.infrastructureRangeSubMenu();
    }

}
