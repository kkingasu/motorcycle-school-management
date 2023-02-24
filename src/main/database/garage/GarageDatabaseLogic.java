package main.database.garage;
import main.models.garage.BikeModel;
import main.models.garage.RepairModel;
import main.startUpApplication;
import java.sql.*;

public class GarageDatabaseLogic {

    static String spacePipe = " | ";
    private static Connection connection = startUpApplication.connection;
    public static void retrieveTotalAvailableBikes() {
        try {
            final String query = "SELECT VIN,license_plate, brand_name, bike_type, CC FROM bike WHERE is_operational = TRUE";
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);

            if(rs != null) {
                System.out.print(formatOutput("VIN") + spacePipe);
                System.out.print(formatOutput("License Plate") + spacePipe);
                System.out.print(formatOutput("Brand Name") + spacePipe);
                System.out.print(formatOutput("Bike Type") + spacePipe);
                System.out.println(formatOutput("CC") + spacePipe);

                while(rs.next()) {
                    String VIN = rs.getString("VIN");
                    String licensePlate = rs.getString("license_plate");
                    String brandName = rs.getString("brand_name");
                    String bikeType = rs.getString("bike_type");
                    String CC = rs.getString("CC");
                    System.out.print(formatOutput(VIN) + spacePipe);
                    System.out.print(formatOutput(licensePlate) + spacePipe);
                    System.out.print(formatOutput(brandName) + spacePipe);
                    System.out.print(formatOutput(bikeType) + spacePipe);
                    System.out.println(formatOutput(CC) + spacePipe);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void retrieveAvailableStreetBikes() {

        try {
            final String query = "SELECT VIN,license_plate, brand_name, bike_type, CC FROM bike WHERE is_operational = TRUE AND bike_type = \"street\"";
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);

            if(rs != null) {
                System.out.print(formatOutput("VIN") + spacePipe);
                System.out.print(formatOutput("License Plate") + spacePipe);
                System.out.print(formatOutput("Brand Name") + spacePipe);
                System.out.print(formatOutput("Bike Type") + spacePipe);
                System.out.println(formatOutput("CC") + spacePipe);

                while(rs.next()) {
                    String VIN = rs.getString("VIN");
                    String licensePlate = rs.getString("license_plate");
                    String brandName = rs.getString("brand_name");
                    String bikeType = rs.getString("bike_type");
                    String CC = rs.getString("CC");
                    System.out.print(formatOutput(VIN) + spacePipe);
                    System.out.print(formatOutput(licensePlate) + spacePipe);
                    System.out.print(formatOutput(brandName) + spacePipe);
                    System.out.print(formatOutput(bikeType) + spacePipe);
                    System.out.println(formatOutput(CC) + spacePipe);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void retrieveAvailableDirtBikes() {
        try {
            final String query = "SELECT VIN,license_plate, brand_name, bike_type, CC FROM bike WHERE is_operational = TRUE AND bike_type = \"dirt\"";
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);

            if(rs != null) {
                System.out.print(formatOutput("VIN") + spacePipe);
                System.out.print(formatOutput("License Plate") + spacePipe);
                System.out.print(formatOutput("Brand Name") + spacePipe);
                System.out.print(formatOutput("Bike Type") + spacePipe);
                System.out.println(formatOutput("CC") + spacePipe);

                while(rs.next()) {
                    String VIN = rs.getString("VIN");
                    String licensePlate = rs.getString("license_plate");
                    String brandName = rs.getString("brand_name");
                    String bikeType = rs.getString("bike_type");
                    String CC = rs.getString("CC");
                    System.out.print(formatOutput(VIN) + spacePipe);
                    System.out.print(formatOutput(licensePlate) + spacePipe);
                    System.out.print(formatOutput(brandName) + spacePipe);
                    System.out.print(formatOutput(bikeType) + spacePipe);
                    System.out.println(formatOutput(CC) + spacePipe);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void assignBikeToCourse(int VIN, int courseID) {

        try {
            final String query = "INSERT INTO bike_assignment VALUES(?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseID);
            preparedStatement.setInt(2,VIN);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Successfully Assigned Bike: " + VIN + " to Course: "+ courseID);

    }

    public static void removeBikeFromCourse(int VIN, int courseID) {

        try {
            final String query = "DELETE FROM bike_assignment WHERE bike_VIN = ? AND course_id = ?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, VIN);
            preparedStatement.setInt(2,courseID);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Successfully Removed Bike: " + VIN + " from Course: "+ courseID);

    }

    public static void submitNewBikeDataToDatabase(BikeModel bike) {

        try {
            final String query = "INSERT INTO bike VALUES (?,?,?,?,?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, bike.getVIN());
            preparedStatement.setInt(2, bike.getLicensePlate());
            preparedStatement.setString(3, bike.getBrandName());
            preparedStatement.setBoolean(4, bike.getIsOperational());
            preparedStatement.setString(5, bike.getBikeType());
            preparedStatement.setInt(6, bike.getCC());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Successfully Added Bike: " + bike.getVIN() + " to Garage");

    }

    public static void removeBikeFromDatabase(int VIN) {

        try {
            final String query = "DELETE FROM bike WHERE VIN =?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, VIN);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Successfully Removed Bike: " + VIN + " from Garage");

    }

    public static void submitWorkOrderToDatabase(RepairModel repair) {

        try {
            final String query = "INSERT INTO repair VALUES (?,?, NULL,?, NULL)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, repair.getRepairID());
            preparedStatement.setDate(2, (Date) repair.getProblemDate());
            preparedStatement.setString(3, repair.getProblemDesc());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Successfully Created Work Order: " + repair.getRepairID());

    }

    public static void assignBikeToWorkOrder(int VIN, int repairID) {

        try {
            final String query = "INSERT INTO manages_repair VALUES (?,?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, VIN);
            preparedStatement.setInt(2,repairID);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Successfully Tagged Bike: " + VIN + " with Work Order: " + repairID);

    }
    public static void setBikeOperationalStatus(int VIN, boolean status) {

        String operationalStatus;
        if(status)
            operationalStatus = "Available";
        else
            operationalStatus = "Unavailable";

        try {
            final String query = "UPDATE bike SET is_operational = ? WHERE VIN = ?";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, VIN);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Bike: " + VIN + " is now " + operationalStatus);

    }

    public static void completeWorkOrderInDatabase(RepairModel repair) {

        try {
            final String query = "UPDATE repair SET repair_date = ?, repair_cost = ?, problem_desc = ? WHERE repair_id = ?;";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, (Date) repair.getRepairDate());
            preparedStatement.setFloat(2, repair.getRepairCost());
            preparedStatement.setString(3, repair.getProblemDesc());
            preparedStatement.setInt(4,repair.getRepairID());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Successfully Completed Work Order: " + repair.getRepairID());

    }

    public static void retrieveActiveWorkOrders() {

        try {
            final String query = "SELECT repair_id,problem_date,problem_desc FROM repair WHERE repair_date IS NULL";
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);

            if(rs != null) {
                System.out.print(formatOutput("Work Order Number") + spacePipe);
                System.out.print(formatOutput("Problem Date") + spacePipe);
                System.out.println(formatOutput("Description") + spacePipe);

                while(rs.next()) {
                    String repairID = rs.getString("repair_id");
                    String problemDate = rs.getString("problem_date");
                    String problemDesc = rs.getString("problem_desc");
                    System.out.print(formatOutput(repairID) + spacePipe);
                    System.out.print(formatOutput(problemDate) + spacePipe);
                    System.out.println(formatOutput(problemDesc) + spacePipe);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void retrieveTotalClosedWorkOrders() {

        try {
            final String query = "SELECT * FROM repair WHERE repair_date IS NOT NULL";
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery(query);

            if(rs != null) {
                System.out.print(formatOutput("Work Order Number") + spacePipe);
                System.out.print(formatOutput("Problem Date") + spacePipe);
                System.out.print(formatOutput("Repair Date") + spacePipe);
                System.out.print(formatOutput("Description") + spacePipe);
                System.out.println(formatOutput("Repair Cost") + spacePipe);

                while(rs.next()) {
                    String repairID = rs.getString("repair_id");
                    String problemDate = rs.getString("problem_date");
                    String repairDate = rs.getString("repair_date");
                    String problemDesc = rs.getString("problem_desc");
                    String repairCost = rs.getString("repair_cost");
                    System.out.print(formatOutput(repairID) + spacePipe);
                    System.out.print(formatOutput(problemDate) + spacePipe);
                    System.out.print(formatOutput(repairDate) + spacePipe);
                    System.out.print(formatOutput(problemDesc) + spacePipe);
                    System.out.println(formatOutput(repairCost) + spacePipe);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void retrieveBikeWorkOrders(int VIN) {

        try {
            final String query = "SELECT * FROM repair INNER JOIN manages_repair ON repair.repair_id = manages_repair.repair_id WHERE manages_repair.bike_VIN = ? AND repair.repair_date IS NOT NULL";
            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,VIN);
            final ResultSet rs = preparedStatement.executeQuery();
            if(rs != null) {
                System.out.print(formatOutput("Work Order Number") + spacePipe);
                System.out.print(formatOutput("Problem Date") + spacePipe);
                System.out.print(formatOutput("Repair Date") + spacePipe);
                System.out.print(formatOutput("Description") + spacePipe);
                System.out.println(formatOutput("Repair Cost") + spacePipe);

                while(rs.next()) {
                    String repairID = rs.getString("repair_id");
                    String problemDate = rs.getString("problem_date");
                    String repairDate = rs.getString("repair_date");
                    String problemDesc = rs.getString("problem_desc");
                    String repairCost = rs.getString("repair_cost");
                    System.out.print(formatOutput(repairID) + spacePipe);
                    System.out.print(formatOutput(problemDate) + spacePipe);
                    System.out.print(formatOutput(repairDate) + spacePipe);
                    System.out.print(formatOutput(problemDesc) + spacePipe);
                    System.out.println(formatOutput(repairCost) + spacePipe);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String formatOutput(String output) {
        String indent = "                  "; // 20 spaces
        return output += indent.substring(0, indent.length() - output.length());
    }

}
