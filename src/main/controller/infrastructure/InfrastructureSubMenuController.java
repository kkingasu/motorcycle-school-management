package main.controller.infrastructure;

import main.business_logic.DefaultLogic;
import main.controller.MainController;
import main.database.infrastructure.InfrastructureDatabaseLogic;
import main.models.infrastructure.InfrastructureClassroomModel;
import main.models.infrastructure.InfrastructureRangeModel;
import main.ui.infrastructure.INFRASTRUCTURE;

public class InfrastructureSubMenuController {
    public static void infrastructureClassroomSubMenu() {
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = DefaultLogic.convertStringToInteger(input);
            switch (inputConvertedToInteger) {
                case 1: // Create Classroom
                    // Print create classrooms menu
                    System.out.println(INFRASTRUCTURE.CREATE_CLASSROOM);
                    handleCreateClassroomInput();
                    break;
                case 2: // Display all Classroom
                    // Print all classrooms menu
                    System.out.println(INFRASTRUCTURE.DISPLAY_CLASSROOM);
                    handleDisplayClassroomsInput();
                    break;
                case 3: // Update Classroom
                    // Print update classroom menu
                    System.out.println(INFRASTRUCTURE.UPDATE_CLASSROOM);
                    handleUpdateClassroomInput();
                    break;
                case 4: // Delete Classroom
                    // Print delete classroom menu
                    System.out.println(INFRASTRUCTURE.DELETE_CLASSROOM);
                    handleDeleteClassroomInput();
                    break;
                default: //
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void infrastructureRangeSubMenu() {
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = DefaultLogic.convertStringToInteger(input);
            switch (inputConvertedToInteger) {
                case 1: // Create Range
                    // Print create range menu
                    System.out.println(INFRASTRUCTURE.CREATE_RANGE);
                    handleCreateRangeInput();
                    break;
                case 2: // Display all Range
                    // Print all ranges menu
                    System.out.println(INFRASTRUCTURE.DISPLAY_RANGE);
                    handleDisplayRangesInput();
                    break;
                case 3: // Update Range
                    // Print update range menu
                    System.out.println(INFRASTRUCTURE.UPDATE_RANGE);
                    handleUpdateRangeInput();
                    break;
                case 4: // Delete Range
                    // Print delete range menu
                    System.out.println(INFRASTRUCTURE.DELETE_RANGE);
                    handleDeleteRangeInput();
                    break;
                default: //
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleCreateClassroomInput() {
        InfrastructureClassroomModel classroomModel = new InfrastructureClassroomModel();
        System.out.print("Enter Classroom Id:");
        classroomModel.setClassroom_id(DefaultLogic.handleInput());
        System.out.print("Enter Classroom Availability (Y/N):");
        classroomModel.setIs_available(DefaultLogic.convertStringToBoolean(DefaultLogic.handleInput()));
        InfrastructureDatabaseLogic.submitCreateClassroomDataToDatabase(classroomModel);
        MainController.initializeMainMenu();
    }

    public static void handleDisplayClassroomsInput() {
        InfrastructureDatabaseLogic.submitDisplayAllClassroomDataToDatabase();
        MainController.initializeMainMenu();
    }

    public static void handleUpdateClassroomInput() {
        InfrastructureClassroomModel classroomModel = new InfrastructureClassroomModel();
        System.out.print("Enter Classroom Id:");
        classroomModel.setClassroom_id(DefaultLogic.handleInput());
        System.out.print("Enter Classroom Availability (Y/N):");
        classroomModel.setIs_available(DefaultLogic.convertStringToBoolean(DefaultLogic.handleInput()));
        InfrastructureDatabaseLogic.submitUpdateClassroomDataToDatabase(classroomModel);
        MainController.initializeMainMenu();
    }

    public static void handleDeleteClassroomInput() {
        InfrastructureClassroomModel classroomModel = new InfrastructureClassroomModel();
        System.out.print("Enter Classroom Id:");
        classroomModel.setClassroom_id(DefaultLogic.handleInput());
        InfrastructureDatabaseLogic.submitDeleteClassroomDataToDatabase(classroomModel);
        MainController.initializeMainMenu();
    }

    public static void handleCreateRangeInput() {
        InfrastructureRangeModel rangeModel = new InfrastructureRangeModel();
        System.out.print("Enter Classroom Id:");
        rangeModel.setRange_number(DefaultLogic.convertStringToInteger(DefaultLogic.handleInput()));
        System.out.print("Enter Classroom Availability (Y/N):");
        rangeModel.setIs_available(DefaultLogic.convertStringToBoolean(DefaultLogic.handleInput()));
        InfrastructureDatabaseLogic.submitCreateRangeDataToDatabase(rangeModel);
        MainController.initializeMainMenu();
    }

    public static void handleDisplayRangesInput() {
        InfrastructureDatabaseLogic.submitDisplayAllRangeDataToDatabase();
        MainController.initializeMainMenu();
    }

    public static void handleUpdateRangeInput() {
        InfrastructureRangeModel rangeModel = new InfrastructureRangeModel();
        System.out.print("Enter Classroom Id:");
        rangeModel.setRange_number(DefaultLogic.convertStringToInteger(DefaultLogic.handleInput()));
        System.out.print("Enter Classroom Availability (Y/N):");
        rangeModel.setIs_available(DefaultLogic.convertStringToBoolean(DefaultLogic.handleInput()));
        InfrastructureDatabaseLogic.submitUpdateRangeDataToDatabase(rangeModel);
        MainController.initializeMainMenu();
    }

    public static void handleDeleteRangeInput() {
        InfrastructureRangeModel rangeModel = new InfrastructureRangeModel();
        System.out.print("Enter Classroom Id:");
        rangeModel.setRange_number(DefaultLogic.convertStringToInteger(DefaultLogic.handleInput()));
        InfrastructureDatabaseLogic.submitDeleteRangeDataToDatabase(rangeModel);
        MainController.initializeMainMenu();
    }

}
