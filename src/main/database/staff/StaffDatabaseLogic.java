package main.database.staff;

import main.database.SQLPrinter;
import main.models.StaffModel;
import main.startUpApplication;

import java.sql.*;
import java.util.UUID;

public class StaffDatabaseLogic {
    private static Connection connection = startUpApplication.connection;
    static String spacePipe = " | ";

    public static void submitCreateStaffDataToDatabase(StaffModel staffModel) {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        UUID staffUUID = null;

        try {
            final String query = "INSERT INTO person(name, address, dob, phone_number) VALUES (?,?,?,?) RETURNING person_id";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, staffModel.getName());
            preparedStatement.setString(2, staffModel.getAddress());
            java.sql.Date sqlDate = new java.sql.Date(staffModel.getDob().getTime());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setFloat(4, staffModel.getPhoneNumber());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                staffUUID = (UUID) rs.getObject("person_id");
            }

            final String query3 = "INSERT INTO staff(staff_id, can_teach_dirt_bike,can_teach_street_bike, can_teach_classroom) VALUES (?,?,?,?)";
            preparedStatement3 = connection.prepareStatement(query3);
            preparedStatement3.setObject(1, staffUUID);
            preparedStatement3.setBoolean(2, staffModel.getDirtBikeAvailability());
            preparedStatement3.setBoolean(3, staffModel.getStreetBikeAvailability());
            preparedStatement3.setBoolean(4, staffModel.getClassAvailability());

            final String query2 = "INSERT INTO staff(staff_id) VALUES (?)";
            preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setObject(1, staffUUID);
            preparedStatement2.executeUpdate();

            final String query3 = "INSERT INTO staff(staff_id, can_teach_dirt_bike,can_teach_street_bike, can_teach_classroom)";
            preparedStatement3 = connection.prepareStatement(query3);
            preparedStatement3.setObject(1, staffUUID);
            preparedStatement3.setObject(2, staffModel.getDirtBikeAvailability());
            preparedStatement3.setObject(3, staffModel.getStreetBikeAvailability());
            preparedStatement3.setObject(4, staffModel.getClassAvailability());
            preparedStatement3.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Log: FAILED - Create Staff : submitCreateStaffDataToDatabase().");
        } finally {
            try {
                if (preparedStatement != null) {

                    preparedStatement.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (preparedStatement3 != null) {
                    preparedStatement3.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Submitting Staff Data......");

    }

    }
    public static void updateStaffNameToDatabase(String staffId, String name){
        boolean updateFailed = false;
        PreparedStatement ps = null;
        try {
            final String preparedQuery = "UPDATE person SET name = ? WHERE person_id = ?";
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, name);
            ps.setObject(2, UUID.fromString(staffId));
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
            System.out.println("UPDATE FAILED - Staff ID: " +staffId +" : updateStaffNameToDatabase().");
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
        System.out.println("Updating Staff Data......");
    }
    public static void updateStaffAddressToDatabase(String staffId, String address){
        boolean updateFailed = false;
        PreparedStatement ps = null;
        try {
            final String preparedQuery = "UPDATE person SET address = ? WHERE person_id = ?";
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, address);
            ps.setObject(2, UUID.fromString(staffId));
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
            System.out.println("UPDATE FAILED - Staff ID: " +staffId +" : updateStaffNameToDatabase().");
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
        System.out.println("Updating Staff Data......");
    }
    public static void updateStaffDobToDatabase(String staffId, java.util.Date dateFormat){
        boolean updateFailed = false;
        PreparedStatement ps = null;
        try {
            final String preparedQuery = "UPDATE person SET dob = ? WHERE person_id = ?";
            ps = connection.prepareStatement(preparedQuery);
            ps.setDate(1, new java.sql.Date(dateFormat.getTime()));
            ps.setObject(2, UUID.fromString(staffId));
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
            System.out.println("UPDATE FAILED - Staff ID: " +staffId +" : updateStaffNameToDatabase().");
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
        System.out.println("Updating Staff Data......");
    }
    public static void updateStaffPhoneNumberToDatabase(String staffId, int phoneNumber){
        boolean updateFailed = false;
        PreparedStatement ps = null;
        try {
            final String preparedQuery = "UPDATE person SET phone_number = ? WHERE person_id = ?";
            ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, phoneNumber);
            ps.setObject(2, UUID.fromString(staffId));
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
            System.out.println("UPDATE FAILED - Staff ID: " +staffId +" : updateStaffNameToDatabase().");
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
        System.out.println("Updating Staff Data......");
    }
    public static void deleteStaffDataFromDatabase(String staffId) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        boolean deleteFailed = false;
        try {
            final String query = "DELETE FROM staff WHERE staff_id = ?";
            ps = connection.prepareStatement(query);
            ps.setObject(1, UUID.fromString(staffId));
            ps.executeUpdate();

            final String query2 = "DELETE FROM person WHERE person_id = ?";
            ps2 = connection.prepareStatement(query2);
            ps2.setObject(1, UUID.fromString(staffId));
            ps2.executeUpdate();


        }catch (Exception e){
            System.out.println(e);
            System.out.println("DELETE FAILED - Staff ID: " +staffId +" : deleteStaffDataFromDatabase().");
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
        System.out.println("Deleting Staff Data......");
    }



    public static boolean validateStaffExists(String staffId){

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            final String preparedQuery = "Select * From person JOIN staff on staff_id = person_id Where person_id = ?";
            ps = connection.prepareStatement(preparedQuery, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ps.setObject(1, UUID.fromString(staffId));
            rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Failed to Validate If Staff Is In Database: validateStaffExists()");
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
    public static void displayStaffInformation(String staffId){
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            final String query = "SELECT staff_id, name, address, dob, phone_number FROM staff JOIN person ON person.person_id = staff.staff_id WHERE staff_id = ?";
            ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ps.setObject(1, UUID.fromString(staffId));

            rs =  ps.executeQuery();
            if (rs != null) {
                SQLPrinter.printResultSet(rs);
            }
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
            System.out.println("VIEW STAFF FAILED - Staff ID: " +staffId +" : displayStaffInformation().");
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
    public static void viewAllStaffs(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            final String query = "SELECT staff_id, name, address, dob, phone_number FROM staff JOIN person ON person.person_id = staff.staff_id";
            stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs =  stmt.executeQuery();
            if (rs != null) {
                SQLPrinter.printResultSet(rs);
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("VIEW All STAFF FAILED: viewAllStaff().");
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


}
