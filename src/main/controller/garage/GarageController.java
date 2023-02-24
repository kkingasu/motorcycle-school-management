package main.controller.garage;

import main.business_logic.DefaultLogic;
import main.business_logic.ValidationLogic;
import main.controller.MainController;
import main.database.garage.GarageDatabaseLogic;
import main.ui.MAIN;
import main.ui.garage.GARAGE;
import main.models.garage.BikeModel;
import main.models.garage.RepairModel;

import java.sql.SQLOutput;
import java.util.Date;

public class GarageController {
    //================================MENUS================================
    public static void garageMenu() {
        try
        {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch(inputConvertedToInteger) {
                case 1: // Manage Bikes
                    // Print Manage Bikes Menu
                    System.out.println(GARAGE.MANGAGE_BIKES_MENU);
                    manageBikesMenu();
                    break;
                case 2: // Manage Bike Maintenance
                    //Print Manage Bike Maintenance Menu
                    System.out.println(GARAGE.MANAGE_BIKE_MAINTENANCE_MENU);
                    manageBikeMaintenanceMenu();
                    break;
                default:
                    // Return back to Main Menu
                    System.out.print(MAIN.MAIN_MENU);
                    MainController.handleInitialMainMenuInput();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void manageBikesMenu() {
        try
        {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch(inputConvertedToInteger) {
                case 1: // View Available Bikes
                    // Print Available Bikes Menu
                    System.out.println(GARAGE.AVAILABLE_BIKES_MENU);
                    availableBikesMenu();
                    break;
                case 2: // Assign Bike to Course
                    handleAssignBikeToCourse();
                    break;
                case 3: // Remove Bike from Course
                    handleRemoveBikeFromCourse();
                    break;
                case 4: // Add New Bike to Garage
                    handleAddNewBikeToGarage();
                    break;
                case 5: // Remove Bike from Garage
                    handleRemoveBikeFromGarage();
                    break;
                case 6: // Go back to Garage Menu
                    System.out.println(GARAGE.MAIN_MENU);
                    garageMenu();
                    break;
                default:
                    // Return back to Main Menu
                    System.out.print(MAIN.MAIN_MENU);
                    MainController.handleInitialMainMenuInput();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void availableBikesMenu() {
        try
        {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch(inputConvertedToInteger) {
                case 1: // Total Available Bike Report
                    handleTotalAvailableBikeReport();
                    break;
                case 2: // Available Street Bike Report
                    handleTotalAvailableStreetBikeReport();
                    break;
                case 3: // Available Dirt Bike Report
                    handleTotalAvailableDirtBikeReport();
                    break;
                case 4: // Return to Manage Bikes Menu
                    System.out.println(GARAGE.MANGAGE_BIKES_MENU);
                    manageBikesMenu();
                    break;
                default:
                    // Return back to Main Menu
                    System.out.print(MAIN.MAIN_MENU);
                    MainController.handleInitialMainMenuInput();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void manageBikeMaintenanceMenu() {
        try
        {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch(inputConvertedToInteger) {
                case 1: // Submit Work Order
                    handleSubmitWorkOrder();
                    break;
                case 2: // Complete Work Order
                    handleCompleteWorkOrder();
                    break;
                case 3: // View Total Active Work Orders Report
                    handleViewTotalWorkOrders();
                    break;
                case 4: // View Closed Work Order Reports
                    // Print Closed Work Order Menu
                    System.out.println(GARAGE.CLOSED_WORK_ORDER_MENU);
                    closedWorkOrderMenu();
                    break;
                case 5: //Return to Garage Menu
                    System.out.println(GARAGE.MAIN_MENU);
                    garageMenu();
                    break;
                default:
                    // Return back to Main Menu
                    System.out.print(MAIN.MAIN_MENU);
                    MainController.handleInitialMainMenuInput();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void closedWorkOrderMenu() {
        try
        {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch(inputConvertedToInteger) {
                case 1: // Total Closed Work Order Report
                    handleViewTotalClosedWorkOrders();
                    break;
                case 2: // Individual Bike Work Order Report
                    handleIndividualBikeReport();
                    break;
                case 3: // Return to Manage Bike Maintenance Menu
                    System.out.println(GARAGE.MANAGE_BIKE_MAINTENANCE_MENU);
                    manageBikeMaintenanceMenu();
                default:
                    // Return back to Main Menu
                    System.out.print(MAIN.MAIN_MENU);
                    MainController.handleInitialMainMenuInput();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    //================================HANDLERS================================
    public static void handleAssignBikeToCourse() {
        System.out.print("Enter VIN Number: ");
        int VIN = Integer.parseInt(DefaultLogic.handleInput());
        System.out.print("Enter Course ID: ");
        int courseID = Integer.parseInt(DefaultLogic.handleInput());

        GarageDatabaseLogic.assignBikeToCourse(VIN, courseID);
        GarageController.garageMenu();
    }

    public static void handleRemoveBikeFromCourse() {
        System.out.print("Enter VIN Number: ");
        int VIN = Integer.parseInt(DefaultLogic.handleInput());
        System.out.print("Enter Course ID: ");
        int courseID = Integer.parseInt(DefaultLogic.handleInput());

        GarageDatabaseLogic.removeBikeFromCourse(VIN, courseID);
        GarageController.garageMenu();
    }

    public static void handleAddNewBikeToGarage() {
        BikeModel bike = new BikeModel();

        System.out.print("Enter License Plate Number: ");
        bike.setLicensePlate(Integer.parseInt(DefaultLogic.handleInput()));
        System.out.print("Enter VIN Number: ");
        bike.setVIN(Integer.parseInt(DefaultLogic.handleInput()));
        System.out.print("Enter Brand Name: ");
        bike.setBrandName(DefaultLogic.handleInput());
        System.out.print("Enter CC: ");
        bike.setCC(Integer.parseInt(DefaultLogic.handleInput()));
        System.out.print("Enter Bike Type: ");
        bike.setBikeType(DefaultLogic.handleInput());
        System.out.print("Is the Bike Operational? (True or False): ");
        bike.setIsOperational(Boolean.parseBoolean(DefaultLogic.handleInput()));

        GarageDatabaseLogic.submitNewBikeDataToDatabase(bike);
        GarageController.garageMenu();
    }

    public static void handleRemoveBikeFromGarage() {
        System.out.print("Enter VIN of Bike to Remove: ");
        int VIN = Integer.parseInt(DefaultLogic.handleInput());

        GarageDatabaseLogic.removeBikeFromDatabase(VIN);
        GarageController.garageMenu();
    }

    public static void handleTotalAvailableBikeReport() {
        GarageDatabaseLogic.retrieveTotalAvailableBikes();
        GarageController.garageMenu();
    }

    public static void handleTotalAvailableStreetBikeReport() {
        GarageDatabaseLogic.retrieveAvailableStreetBikes();
        GarageController.garageMenu();
    }

    public static void handleTotalAvailableDirtBikeReport() {
        GarageDatabaseLogic.retrieveAvailableStreetBikes();
        GarageController.garageMenu();
    }

    public static void handleSubmitWorkOrder() {
        RepairModel repair = new RepairModel();

        System.out.print("Enter VIN: ");
        repair.setVIN(Integer.parseInt(DefaultLogic.handleInput()));
        System.out.print("Enter Work Order Number: ");
        repair.setRepairID(Integer.parseInt(DefaultLogic.handleInput()));

        System.out.print("Enter Problem Date(yyyy-MM-dd): ");
        String dateInput = DefaultLogic.handleInput();
        // Send input to validator
        Date dateFormat = null;
        while (dateFormat == null) {
            try {
                dateFormat = ValidationLogic.validateDateInput(dateInput);
            }catch (Exception e) {
                System.out.print("Try again, please use format(yyyy-MM-dd):");
                dateInput = DefaultLogic.handleInput();
                dateFormat = ValidationLogic.validateDateInput(dateInput);
            }
        }
        repair.setProblemDate(dateFormat);

        System.out.print("Is the Bike Operational? (True or False): ");
        repair.setOperationalStatus(Boolean.parseBoolean(DefaultLogic.handleInput()));
        System.out.print("Enter short description of the problem: ");
        repair.setProblemDesc(DefaultLogic.handleInput());

        GarageDatabaseLogic.submitWorkOrderToDatabase(repair);
        GarageDatabaseLogic.assignBikeToWorkOrder(repair.getVIN(), repair.getRepairID());
        GarageDatabaseLogic.setBikeOperationalStatus(repair.getVIN(), repair.getOperationalStatus());
        GarageController.garageMenu();

    }

    public static void handleCompleteWorkOrder() {
        RepairModel repair = new RepairModel();

        System.out.print("Enter the Work Order Number: ");
        repair.setRepairID(Integer.parseInt(DefaultLogic.handleInput()));

        System.out.print("Enter the Repair Date(yyyy-MM-dd): ");
        String dateInput = DefaultLogic.handleInput();
        // Send input to validator
        Date dateFormat = null;
        while (dateFormat == null) {
            try {
                dateFormat = ValidationLogic.validateDateInput(dateInput);
            }catch (Exception e) {
                System.out.print("Try again, please use format(yyyy-MM-dd):");
                dateInput = DefaultLogic.handleInput();
                dateFormat = ValidationLogic.validateDateInput(dateInput);
            }
        }
        repair.setRepairDate(dateFormat);

        System.out.print("Enter the VIN: ");
        repair.setVIN(Integer.parseInt(DefaultLogic.handleInput()));
        System.out.print("Is the Bike Fixed? (True or False): ");
        repair.setOperationalStatus(Boolean.parseBoolean(DefaultLogic.handleInput()));
        System.out.println("Enter the repair cost: ");
        repair.setRepairCost(Float.parseFloat(DefaultLogic.handleInput()));
        System.out.println("Enter a short description of the repair performed: ");
        repair.setProblemDesc(DefaultLogic.handleInput());

        GarageDatabaseLogic.completeWorkOrderInDatabase(repair);
        GarageDatabaseLogic.setBikeOperationalStatus(repair.getVIN(), repair.getOperationalStatus());
        GarageController.garageMenu();
    }

    public static void handleViewTotalWorkOrders() {
        GarageDatabaseLogic.retrieveActiveWorkOrders();
        GarageController.garageMenu();
    }

    public static void handleViewTotalClosedWorkOrders() {
        GarageDatabaseLogic.retrieveTotalClosedWorkOrders();
        GarageController.garageMenu();
    }

    public static void handleIndividualBikeReport() {
        System.out.print("Enter the VIN of the Bike for the Work Order Report: ");
        int VIN = Integer.parseInt(DefaultLogic.handleInput());

        GarageDatabaseLogic.retrieveBikeWorkOrders(VIN);
        GarageController.garageMenu();
    }

}
