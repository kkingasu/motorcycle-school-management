package main.database.student;


import main.controller.student.StudentController;
import main.controller.student.StudentSubMenuController;
import main.models.StudentModel;
import main.startUpApplication;

import java.sql.*;
import java.util.UUID;

public class StudentDatabaseLogic {
    private static Connection connection = startUpApplication.connection;
    static String spacePipe = " | ";

    public static void submitCreateStudentDataToDatabase(StudentModel studentModel) {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        UUID studentUUID = null;
        //TODO: Add logic to submit data to database, should probably make a db factory for doing different request...
        try {
            final String query = "INSERT INTO person(name, address, dob, phone_number) VALUES (?,?,?,?) RETURNING person_id";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentModel.getName());
            preparedStatement.setString(2, studentModel.getAddress());
            java.sql.Date sqlDate = new java.sql.Date(studentModel.getDob().getTime());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setFloat(4, studentModel.getPhoneNumber());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                studentUUID = (UUID) rs.getObject("person_id");
            }
            final String query2 = "INSERT INTO student(student_id) VALUES (?)";
            preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setObject(1, studentUUID);
            preparedStatement2.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create Student : submitCreateStudentDataToDatabase().");
        } finally {
            try {
                if (preparedStatement != null) {

                    preparedStatement.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Submitting Student Data......");
    }
    public static void updateStudentNameToDatabase(String studentId, String name){
        boolean updateFailed = false;
        PreparedStatement ps = null;
        try {
            final String preparedQuery = "UPDATE person SET name = ? WHERE person_id = ?";
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, name);
            ps.setObject(2, UUID.fromString(studentId));
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
            System.out.println("UPDATE FAILED - Student ID: " +studentId +" : updateStudentNameToDatabase().");
            updateFailed = true;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(updateFailed) {return;}
        System.out.println("Updating Student Data......");
    }
    public static void updateStudentAddressToDatabase(String studentId, String address){
        boolean updateFailed = false;
        PreparedStatement ps = null;
        try {
            final String preparedQuery = "UPDATE person SET address = ? WHERE person_id = ?";
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, address);
            ps.setObject(2, UUID.fromString(studentId));
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
            System.out.println("UPDATE FAILED - Student ID: " +studentId +" : updateStudentNameToDatabase().");
            updateFailed = true;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(updateFailed) {return;}
        System.out.println("Updating Student Data......");
    }
    public static void updateStudentDobToDatabase(String studentId, java.util.Date dateFormat){
        boolean updateFailed = false;
        PreparedStatement ps = null;
        try {
            final String preparedQuery = "UPDATE person SET dob = ? WHERE person_id = ?";
            ps = connection.prepareStatement(preparedQuery);
            ps.setDate(1, new java.sql.Date(dateFormat.getTime()));
            ps.setObject(2, UUID.fromString(studentId));
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
            System.out.println("UPDATE FAILED - Student ID: " +studentId +" : updateStudentNameToDatabase().");
            updateFailed = true;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(updateFailed) {return;}
        System.out.println("Updating Student Data......");
    }
    public static void updateStudentPhoneNumberToDatabase(String studentId, int phoneNumber){
        boolean updateFailed = false;
        PreparedStatement ps = null;
        try {
            final String preparedQuery = "UPDATE person SET phone_number = ? WHERE person_id = ?";
            ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, phoneNumber);
            ps.setObject(2, UUID.fromString(studentId));
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
            System.out.println("UPDATE FAILED - Student ID: " +studentId +" : updateStudentNameToDatabase().");
            updateFailed = true;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(updateFailed) {return;}
        System.out.println("Updating Student Data......");
    }
    public static void deleteStudentDataFromDatabase(String studentId) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        boolean deleteFailed = false;
        try {
            final String query = "DELETE FROM student WHERE student_id = ?";
            ps = connection.prepareStatement(query);
            ps.setObject(1, UUID.fromString(studentId));
            ps.executeUpdate();

            final String query2 = "DELETE FROM person WHERE person_id = ?";
            ps2 = connection.prepareStatement(query2);
            ps2.setObject(1, UUID.fromString(studentId));
            ps2.executeUpdate();


        }catch (Exception e){
            System.out.println(e);
            System.out.println("DELETE FAILED - Student ID: " +studentId +" : deleteStudentDataFromDatabase().");
            deleteFailed = true;
        }  finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        if(deleteFailed){return;}
        System.out.println("Deleting Student Data......");
        }



    public static boolean validateStudentExists(String studentId){
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            final String preparedQuery = "Select * From person JOIN student on student_id = person_id Where person_id = ?";
            ps = connection.prepareStatement(preparedQuery);
            try{
                ps.setObject(1, UUID.fromString(studentId));
            }catch(Exception e){
                System.out.println("Invalid UUID format");
                StudentController.studentMenu();
            }

            rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Failed to Validate If Student Is In Database: validateStudentExists()");
            return false;
        }finally {
            try {
            if (ps != null) {
                ps.close();
            }
            if(rs!= null){
                rs.close();
            }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
    public static void displayStudentInformation(String studentId){
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            final String query = "SELECT student_id, name, address, dob, phone_number FROM student JOIN person ON person.person_id = student.student_id WHERE student_id = ?";
            ps = connection.prepareStatement(query);
            try{
                ps.setObject(1, UUID.fromString(studentId));
            }catch(Exception e){
                System.out.println("Invalid UUID format");
                StudentSubMenuController.studentViewStudentSubMenu();
            }

            rs =  ps.executeQuery();
            if (rs != null) {
                System.out.print(formatOutputUUID("id") + spacePipe);
                System.out.print(formatOutput("Name") + spacePipe);
                System.out.print(formatOutput("Address") + spacePipe);
                System.out.print(formatOutput("DOB") + spacePipe);
                System.out.println(formatOutput("Phone Number") + spacePipe);
                System.out.println("--------------------------------------------------------------------------------------------------------");
                while(rs.next()){
                    String resultSetStudentId = rs.getString("student_id");
                    String resultSetStudentName = rs.getString("name");
                    String resultSetStudentAddress = rs.getString("address");
                    String resultSetStudentDob = rs.getString("dob");
                    String resultSetStudentPhoneNumber = rs.getString("phone_number");
                    System.out.print(formatOutputUUID(resultSetStudentId) + spacePipe);
                    System.out.print(formatOutput(resultSetStudentName) + spacePipe);
                    System.out.print(formatOutput(resultSetStudentAddress) + spacePipe);
                    System.out.print(formatOutput(resultSetStudentDob) + spacePipe);
                    System.out.print(formatOutput(resultSetStudentPhoneNumber) + spacePipe);
                    System.out.println("");
                }
                System.out.println("--------------------------------------------------------------------------------------------------------");
            }
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
            System.out.println("VIEW STUDENT FAILED - Student ID: " +studentId +" : displayStudentInformation().");
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if(rs!= null){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void displayStudentReport(String studentId){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
           // final String query = "SELECT student.student_id, person.name, course.course_id, course.name, course.type, course.date, cost, course_fee_paid, test_date, final_score, exercise_1_score, exercise_2_score, exercise_3_score, exercise_4_score, exercise_5_score FROM student JOIN person ON person.person_id = student.student_id JOIN enrollment ON enrollment.student_id = student.student_id JOIN course ON course.course_id = enrollment.course_id WHERE student.student_id = ?";
            final String query = "SELECT student.student_id, person.name, course.course_id, course.name, course.type, course_fee_paid, final_score, exercise_1_score, exercise_2_score, exercise_3_score, exercise_4_score, exercise_5_score FROM student JOIN person ON person.person_id = student.student_id JOIN enrollment ON enrollment.student_id = student.student_id JOIN course ON course.course_id = enrollment.course_id WHERE student.student_id = ?";
            ps = connection.prepareStatement(query);
            try{
                ps.setObject(1, UUID.fromString(studentId));
            }catch(Exception e){
                System.out.println("Invalid UUID format");
                StudentController.studentMenu();
            }
            rs =  ps.executeQuery();
            if (rs.isBeforeFirst()) {
                System.out.print(formatOutputUUID("id") + spacePipe);
                System.out.print(formatOutput("Name") + spacePipe);
                System.out.print(formatOutputUUID("Course ID") + spacePipe);
                System.out.print(formatOutputShorter("Course Name") + spacePipe);
                System.out.print(formatOutputShorter("Course Type") + spacePipe);
               // System.out.println(formatOutput("Course Date") + spacePipe);
               // System.out.println(formatOutput("Course Cost") + spacePipe);
                System.out.print(formatOutputShorter("Course Fee Paid") + spacePipe);
               // System.out.println(formatOutput("Test Date") + spacePipe);
                System.out.print(formatOutputShorter("Final Score") + spacePipe);
                System.out.print(formatOutputShorter("Exercise 1 Score") + spacePipe);
                System.out.print(formatOutputShorter("Exercise 2 Score") + spacePipe);
                System.out.print(formatOutputShorter("Exercise 3 Score") + spacePipe);
                System.out.print(formatOutputShorter("Exercise 4 Score") + spacePipe);
                System.out.println(formatOutputShorter("Exercise 5 Score") + spacePipe);
                System.out.println("--------------------------------------------------------------------------------------------------------");
                while(rs.next()){
                    String resultSetStudentId = rs.getString("student_id");
                    String resultSetStudentName = rs.getString("name");
                    String resultSetCourseId = rs.getString("course_id");
                    String resultSetCourseName = rs.getString("name");
                    String resultSetCourseType = rs.getString("type");
                  //  String resultSetCourseDate = rs.getString("date");
                  //  String resultSetCourseCost = rs.getString("cost");
                    String resultSetCourseFeePaid = rs.getString("course_fee_paid");
                  //  String resultSetTestDate = rs.getString("test_date");
                    String resultSetFinalScore = rs.getString("final_score");
                    String resultSetExercise1Score = rs.getString("exercise_1_score");
                    String resultSetExercise2Score = rs.getString("exercise_2_score");
                    String resultSetExercise3Score = rs.getString("exercise_3_score");
                    String resultSetExercise4Score = rs.getString("exercise_4_score");
                    String resultSetExercise5Score = rs.getString("exercise_5_score");
                    System.out.print(formatOutputUUID(resultSetStudentId) + spacePipe);
                    System.out.print(formatOutput(resultSetStudentName) + spacePipe);
                    System.out.print(formatOutputUUID(resultSetCourseId) + spacePipe);
                    System.out.print(formatOutputShorter(resultSetCourseName) + spacePipe);
                    System.out.print(formatOutputShorter(resultSetCourseType) + spacePipe);
                  //  System.out.print(formatOutput(resultSetCourseDate) + spacePipe);
                  //  System.out.print(formatOutput(resultSetCourseCost) + spacePipe);
                    System.out.print(formatOutputShorter(resultSetCourseFeePaid) + spacePipe);
                   // System.out.print(formatOutput(resultSetTestDate) + spacePipe);
                    System.out.print(formatOutputShorter(resultSetFinalScore) + spacePipe);
                    System.out.print(formatOutputShorter(resultSetExercise1Score) + spacePipe);
                    System.out.print(formatOutputShorter(resultSetExercise2Score) + spacePipe);
                    System.out.print(formatOutputShorter(resultSetExercise3Score) + spacePipe);
                    System.out.print(formatOutputShorter(resultSetExercise4Score) + spacePipe);
                    System.out.print(formatOutputShorter(resultSetExercise5Score) + spacePipe);
                    System.out.println("");
                }
                System.out.println("--------------------------------------------------------------------------------------------------------");
            }
            else{
                System.out.println("This Student Has No Information To Show");
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("VIEW STUDENT REPORT FAILED - Student ID: " +studentId +" : displayStudentReport().");
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if(rs!= null){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void viewAllStudents(){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            final String query = "SELECT student_id, name, address, dob, phone_number FROM student JOIN person ON person.person_id = student.student_id";
            stmt = connection.createStatement();
            rs =  stmt.executeQuery(query);
            if (rs != null) {
                System.out.print(formatOutputUUID("id") + spacePipe);
                System.out.print(formatOutput("Name") + spacePipe);
                System.out.print(formatOutput("Address") + spacePipe);
                System.out.print(formatOutput("DOB") + spacePipe);
                System.out.println(formatOutput("Phone Number") + spacePipe);
                System.out.println("--------------------------------------------------------------------------------------------------------");
                while(rs.next()){
                    String resultSetStudentId = rs.getString("student_id");
                    String resultSetStudentName = rs.getString("name");
                    String resultSetStudentAddress = rs.getString("address");
                    String resultSetStudentDob = rs.getString("dob");
                    String resultSetStudentPhoneNumber = rs.getString("phone_number");
                    System.out.print(formatOutputUUID(resultSetStudentId) + spacePipe);
                    System.out.print(formatOutput(resultSetStudentName) + spacePipe);
                    System.out.print(formatOutput(resultSetStudentAddress) + spacePipe);
                    System.out.print(formatOutput(resultSetStudentDob) + spacePipe);
                    System.out.print(formatOutput(resultSetStudentPhoneNumber) + spacePipe);
                    System.out.println("");
                }
                System.out.println("--------------------------------------------------------------------------------------------------------");
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("VIEW All STUDENTS FAILED: viewAllStudents().");
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if(rs!= null){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static String formatOutputUUID(String output) {
        String indent = "                                      "; // 40 spaces
        return output += indent.substring(0, indent.length() - output.length());
    }
    public static String formatOutput(String output) {
        String indent = "                  "; // 20 spaces
        return output += indent.substring(0, indent.length() - output.length());
    }
    public static String formatOutputShorter(String output) {
        String indent = "                "; // 16 spaces
        return output += indent.substring(0, indent.length() - output.length());
    }
}
