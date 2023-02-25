package main.database.garage;
import main.models.garage.BikeModel;
import main.models.garage.RepairModel;
import main.startUpApplication;
import java.sql.*;

public class GarageDatabaseLogic {

    static String spacePipe = " | ";
    private static Connection connection = startUpApplication.connection;
    public static void retrieveTotalAvailableBikes() {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT VIN,license_plate, brand_name, bike_type, CC FROM bike WHERE is_operational = TRUE";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void retrieveAvailableStreetBikes() {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT VIN,license_plate, brand_name, bike_type, CC FROM bike WHERE is_operational = TRUE AND bike_type = \"street\"";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void retrieveAvailableDirtBikes() {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT VIN,license_plate, brand_name, bike_type, CC FROM bike WHERE is_operational = TRUE AND bike_type = \"dirt\"";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void assignBikeToCourse(int VIN, int courseID) {

        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            final String query = "INSERT INTO bike_assignment VALUES(?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseID);
            preparedStatement.setInt(2,VIN);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Successfully Assigned Bike: " + VIN + " to Course: "+ courseID);

        } catch (SQLException e) {
            System.out.println("Failed to Assign Bike: " + VIN + " to Course: "+ courseID);
            System.out.println(e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void removeBikeFromCourse(int VIN, int courseID) {

        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            final String query = "DELETE FROM bike_assignment WHERE bike_VIN = ? AND course_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, VIN);
            preparedStatement.setInt(2,courseID);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Successfully Removed Bike: " + VIN + " from Course: "+ courseID);

        } catch (SQLException e) {
            System.out.println("Failed to Remove Bike: " + VIN + " from Course: "+ courseID);
            System.out.println(e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void submitNewBikeDataToDatabase(BikeModel bike) {

        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            final String query = "INSERT INTO bike VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, bike.getVIN());
            preparedStatement.setInt(2, bike.getLicensePlate());
            preparedStatement.setString(3, bike.getBrandName());
            preparedStatement.setBoolean(4, bike.getIsOperational());
            preparedStatement.setString(5, bike.getBikeType());
            preparedStatement.setInt(6, bike.getCC());
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Successfully Added Bike: " + bike.getVIN() + " to Garage");

        } catch (SQLException e) {
            System.out.println("Failed to Add Bike: " + bike.getVIN() + " to Garage");
            System.out.println(e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void removeBikeFromDatabase(int VIN) {

        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            final String query = "DELETE FROM bike WHERE VIN =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, VIN);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Successfully Removed Bike: " + VIN + " from Garage");

        } catch (SQLException e) {
            System.out.println("Failed to Remove Bike: " + VIN + " from Garage");
            System.out.println(e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void submitWorkOrderToDatabase(RepairModel repair) throws SQLException {

        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            final String query = "INSERT INTO repair VALUES (?,?, NULL,?, NULL)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, repair.getRepairID());
            preparedStatement.setDate(2, (Date) repair.getProblemDate());
            preparedStatement.setString(3, repair.getProblemDesc());
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Successfully Created Work Order: " + repair.getRepairID());

        } catch (SQLException e) {
            System.out.println("Failed to Create Work Order: " + repair.getRepairID());
            System.out.println(e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }
            throw new SQLException(); //Used to stop the sequence of submitting work order data
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void assignBikeToWorkOrder(int VIN, int repairID) throws SQLException {

        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            final String query = "INSERT INTO manages_repair VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, VIN);
            preparedStatement.setInt(2,repairID);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Successfully Tagged Bike: " + VIN + " with Work Order: " + repairID);

        } catch (SQLException e) {
            System.out.println("Failed to Tag Bike: " + VIN + " with Work Order: " + repairID);
            System.out.println(e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            throw new SQLException(); //Used to stop the sequence of submitting bike assignment
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
    public static void setBikeOperationalStatus(int VIN, boolean status) throws SQLException {

        PreparedStatement preparedStatement = null;
        String operationalStatus;
        if(status)
            operationalStatus = "Available";
        else
            operationalStatus = "Unavailable";

        try {
            connection.setAutoCommit(false);
            final String query = "UPDATE bike SET is_operational = ? WHERE VIN = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, VIN);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Bike: " + VIN + " is now " + operationalStatus);

        } catch (SQLException e) {
            System.out.println("Failed to Update Bike: " + VIN + " Operational Status to: " + operationalStatus);
            System.out.println(e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            throw new SQLException(); //Used to stop the sequence of setting bike operational status
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void completeWorkOrderInDatabase(RepairModel repair) throws SQLException {

        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            final String query = "UPDATE repair SET repair_date = ?, repair_cost = ?, problem_desc = ? WHERE repair_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, (Date) repair.getRepairDate());
            preparedStatement.setFloat(2, repair.getRepairCost());
            preparedStatement.setString(3, repair.getProblemDesc());
            preparedStatement.setInt(4,repair.getRepairID());
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Successfully Completed Work Order: " + repair.getRepairID());

        } catch (SQLException e) {
            System.out.println("Failed to Complete Work Order: " + repair.getRepairID());
            System.out.println(e.getMessage());
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            throw new SQLException(); //Used to stop the sequence of completing a work order
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void retrieveActiveWorkOrders() {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT repair_id,problem_date,problem_desc FROM repair WHERE repair_date IS NULL";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void retrieveTotalClosedWorkOrders() {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT * FROM repair WHERE repair_date IS NOT NULL";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void retrieveBikeWorkOrders(int VIN) {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT * FROM repair INNER JOIN manages_repair ON repair.repair_id = manages_repair.repair_id WHERE manages_repair.bike_VIN = ? AND repair.repair_date IS NOT NULL";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,VIN);
            rs = preparedStatement.executeQuery();
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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static String formatOutput(String output) {
        String indent = "                  "; // 20 spaces
        return output += indent.substring(0, indent.length() - output.length());
    }

}
