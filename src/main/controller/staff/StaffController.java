package main.controller.staff;

import main.business_logic.DefaultLogic;
import main.controller.MainController;
import main.database.staff.StaffDatabaseLogic;
import main.ui.staff.STAFF;
import main.models.StaffModel;

import java.util.Date;

public class StaffController {

    public static void staffMenu() {
        System.out.print(STAFF.MAIN_MENU);
        String staffId;
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch (inputConvertedToInteger) {
                case 1:
                    System.out.print(STAFF.CREATE_STAFF);
                    handleCreateStaffInput();
                    break;
                case 2:

                    System.out.print("Enter The Staff ID To Edit:");
                    staffId =  DefaultLogic.handleInput();
                    if(StaffDatabaseLogic.validateStaffExists(staffId)){
                        System.out.print(STAFF.EDIT_STAFF);
                        StaffSubMenuController.staffEditStaffSubMenu(staffId);
                    }
                    else{
                        System.out.print("The Staff Id That Has Been Entered Does Not Exist In The Database\n");
                        StaffController.staffMenu();
                    }
                    break;
                case 3:
                    System.out.println(STAFF.REMOVE_STAFF);
                    System.out.print("Enter The Staff ID Of The Staff To Remove:");
                    staffId = DefaultLogic.handleInput();
                    if(StaffDatabaseLogic.validateStaffExists(staffId)){
                        handleRemoveStaffInput(staffId);
                    }
                    else{
                        System.out.print("The Staff Id That Has Been Entered Does Not Exist In The Database\n");
                        StaffController.staffMenu();
                    }

                    break;
                case 4:

                    StaffSubMenuController.staffViewStaffSubMenu();
                    break;
                case 5:
                    MainController.initializeMainMenu();
                    break;
                default:
                    // code block
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void handleCreateStaffInput(){
        StaffModel staffModel = new StaffModel();
        //TODO: ENSURE THE ID is autogenerated
        System.out.println();
        System.out.print("Enter Staff Name:");
        staffModel.setName(DefaultLogic.handleInput());
        System.out.print("Enter Staff Address:");
        staffModel.setAddress(DefaultLogic.handleInput());
        System.out.print("Enter Staff DOB(mm/dd/yyyy):");
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
        staffModel.setDob(dateFormat);
        System.out.print("Enter Staff Phone Number:");
        staffModel.setPhoneNumber(Integer.parseInt(DefaultLogic.handleInput()));

        System.out.print("Enter Staff Availability to teach in the classroom (T/F:");
        staffModel.setClassAvailability(DefaultLogic.handleInput());

        System.out.print("Enter Staff Availability to coach dirt bikes (T/F)");
        staffModel.setDirtBikeAvailability(DefaultLogic.handleInput());

        System.out.print("Enter Staff Availability to coach street bikes (T/F):");
        staffModel.setStreetBikeAvailability(DefaultLogic.handleInput());

        StaffDatabaseLogic.submitCreateStaffDataToDatabase(staffModel);
        StaffController.staffMenu();
    }
    public static Date validateDateInput(String date) {
        Date parsedDate = new Date(date);
        if (parsedDate != null) {
            return parsedDate;
        } else {
            parsedDate = null;
            return parsedDate;
        }
    }

    public static void handleRemoveStaffInput(String staffId){
        StaffDatabaseLogic.deleteStaffDataFromDatabase(staffId);
        StaffController.staffMenu();
    }
    public static void handleViewAllStaffInput(){
        System.out.print(STAFF.VIEW_ALL_STAFF);
        StaffDatabaseLogic.viewAllStaffs();
        StaffSubMenuController.staffViewStaffSubMenu();
    }
}
