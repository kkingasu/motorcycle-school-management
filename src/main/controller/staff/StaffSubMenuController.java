package main.controller.staff;

import main.business_logic.DefaultLogic;
import main.controller.MainController;
import main.database.staff.StaffDatabaseLogic;
import main.ui.staff.STAFF;

import java.util.Date;

import static main.controller.staff.StaffController.handleViewAllStaffInput;

public class StaffSubMenuController {

    public static void staffEditStaffSubMenu(String staffId) {
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch (inputConvertedToInteger) {
                case 1:
                    handleEditStaffNameInput(staffId);
                    break;
                case 2:
                    handleEditStaffAddressInput(staffId);
                    break;
                case 3:
                    handleEditStaffDobInput(staffId);
                    break;
                case 4:
                    handleEditStaffPhoneNumberInput(staffId);
                    break;
                default:
                    StaffController.staffMenu();
                    // code block
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void staffViewStaffSubMenu() {
        System.out.print(STAFF.VIEW_STAFF);
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch (inputConvertedToInteger) {
                case 1:
                    handleViewAllStaffInput();
                    break;
                case 2:
                    handleDisplayStaffInformationInput();
                    break;
                case 3:
                    StaffController.staffMenu();
                default:
                    MainController.initializeMainMenu();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void handleEditStaffNameInput(String staffId){
        System.out.print(STAFF.EDIT_NAME);
        System.out.print("Enter New Staff Name:");
        String name = (DefaultLogic.handleInput());
        StaffDatabaseLogic.updateStaffNameToDatabase(staffId, name);
        StaffController.staffMenu();
    }

    public static void handleEditStaffAddressInput(String staffId){
        System.out.print(STAFF.EDIT_ADDRESS);
        System.out.print("Enter New Staff Address:");
        String address = (DefaultLogic.handleInput());
        StaffDatabaseLogic.updateStaffAddressToDatabase(staffId, address);
        StaffController.staffMenu();
    }
    public static void handleEditStaffDobInput(String staffId){
        System.out.print(STAFF.EDIT_DOB);
        System.out.print("Enter New Staff DOB(mm/dd/yyyy):");
        String dateInput = DefaultLogic.handleInput();
        // Send input to validator
        Date dateFormat = null;
        while (dateFormat == null) {
            try {
                dateFormat = validateDateInput(dateInput);
            }catch (Exception e) {
                System.out.print("Try again, please use format(mm/dd/yyyy):");
                dateInput = DefaultLogic.handleInput();
                dateFormat = validateDateInput(dateInput);
            }
        }
        StaffDatabaseLogic.updateStaffDobToDatabase(staffId, dateFormat);
        StaffController.staffMenu();
    }
    public static Date validateDateInput(String date) {
        Date parsedDate = new Date(date);
        if (parsedDate != null) {
            return parsedDate;
        } else {
            return null;
        }
    }
    public static void handleEditStaffPhoneNumberInput(String staffId){
        System.out.print(STAFF.EDIT_PHONE_NUMBER);
        System.out.print("Enter New Staff Phone Number:");
        int phoneNumber = (Integer.parseInt(DefaultLogic.handleInput()));
        StaffDatabaseLogic.updateStaffPhoneNumberToDatabase(staffId, phoneNumber);
        StaffController.staffMenu();
    }
    public static void handleDisplayStaffInformationInput(){
        System.out.print(STAFF.STAFF_INFORMATION);
        System.out.print("Enter Staff ID Of The Staff To View:");
        String staffId = DefaultLogic.handleInput();
        if(StaffDatabaseLogic.validateStaffExists(staffId)){
            StaffDatabaseLogic.displayStaffInformation(staffId);
        }
        else{
            System.out.print("The Staff Id That Has Been Entered Does Not Exist In The Database\n");
        }

        StaffSubMenuController.staffViewStaffSubMenu();
    }

}
