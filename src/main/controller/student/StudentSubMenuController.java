package main.controller.student;

import main.business_logic.DefaultLogic;
import main.controller.MainController;
import main.database.student.StudentDatabaseLogic;
import main.ui.student.STUDENT;

import java.util.Date;

import static main.controller.student.StudentController.handleViewAllStudentInput;

public class StudentSubMenuController {
    public static void studentEditStudentSubMenu(String studentId) {
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch (inputConvertedToInteger) {
                case 1:
                    System.out.print(STUDENT.EDIT_NAME);
                   handleEditStudentNameInput(studentId);
                    break;
                case 2:
                    System.out.print(STUDENT.EDIT_ADDRESS);
                    handleEditStudentAddressInput(studentId);
                    break;
                case 3:
                    System.out.print(STUDENT.EDIT_DOB);
                    handleEditStudentDobInput(studentId);
                    break;
                case 4:
                    System.out.print(STUDENT.EDIT_PHONE_NUMBER);
                   handleEditStudentPhoneNumberInput(studentId);
                    break;
                default:
                    // code block
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void studentViewStudentSubMenu() {
        System.out.print(STUDENT.VIEW_STUDENT);
        try {
            String input = DefaultLogic.handleInput();
            int inputConvertedToInteger = Integer.parseInt(input);
            switch (inputConvertedToInteger) {
                case 1:
                    System.out.print(STUDENT.VIEW_ALL_STUDENTS);
                    handleViewAllStudentInput();
                    break;
                case 2:
                    System.out.print(STUDENT.STUDENT_INFORMATION);
                    handleDisplayStudentInformationInput();
                    break;
                case 3:
                    System.out.print(STUDENT.STUDENT_REPORT);
                    handleDisplayStudentReportInput();
                    break;
                case 4:
                    StudentController.studentMenu();
                default:
                    // code block
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void handleEditStudentNameInput(String studentId){
        System.out.print("Enter New Student Name:");
        String name = (DefaultLogic.handleInput());
        StudentDatabaseLogic.updateStudentNameToDatabase(studentId, name);
        StudentController.studentMenu();
    }

    public static void handleEditStudentAddressInput(String studentId){
        System.out.print("Enter New Student Address:");
        String address = (DefaultLogic.handleInput());
        StudentDatabaseLogic.updateStudentAddressToDatabase(studentId, address);
        StudentController.studentMenu();
    }
    public static void handleEditStudentDobInput(String studentId){
        System.out.print("Enter New Student DOB(mm/dd/yyyy):");
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
        StudentDatabaseLogic.updateStudentDobToDatabase(studentId, dateFormat);
        StudentController.studentMenu();
    }
    public static Date validateDateInput(String date) {
        Date parsedDate = new Date(date);
        if (parsedDate != null) {
            return parsedDate;
        } else {
            return null;
        }
    }
    public static void handleEditStudentPhoneNumberInput(String studentId){
        System.out.print("Enter New Student Phone Number:");
        int phoneNumber = (Integer.parseInt(DefaultLogic.handleInput()));
        StudentDatabaseLogic.updateStudentPhoneNumberToDatabase(studentId, phoneNumber);
        StudentController.studentMenu();
    }
    public static void handleDisplayStudentInformationInput(){
        System.out.print("Enter Student ID Of The Student To View:");
        String studentId = DefaultLogic.handleInput();
       if(StudentDatabaseLogic.validateStudentExists(studentId)){
           StudentDatabaseLogic.displayStudentInformation(studentId);
       }
       else{
           System.out.print("The Student Id That Has Been Entered Does Not Exist In The Database\n");
       }

        StudentSubMenuController.studentViewStudentSubMenu();
    }
    public static void handleDisplayStudentReportInput(){
        System.out.print("Enter Student ID Of The Student Report To View:");
        String studentId = DefaultLogic.handleInput();
        if(StudentDatabaseLogic.validateStudentExists(studentId)){
            StudentDatabaseLogic.displayStudentReport(studentId);
        }
          else{
            System.out.print("The Student Id That Has Been Entered Does Not Exist In The Database");
            MainController.handleInitialMainMenuInput();
        }
        StudentSubMenuController.studentViewStudentSubMenu();
    }
}
