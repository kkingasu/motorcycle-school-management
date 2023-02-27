package main.database.garage;
import main.database.SQLPrinter;
import main.models.garage.BikeModel;
import main.models.garage.RepairModel;
import main.startUpApplication;
import main.ui.garage.GARAGE;

import java.sql.*;
import java.util.UUID;

public class GarageDatabaseLogic {

    private static Connection connection = startUpApplication.connection;
    public static void retrieveTotalAvailableBikes() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT VIN,license_plate, brand_name, bike_type, CC FROM bike WHERE is_operational = TRUE AND VIN NOT IN (SELECT bike_VIN FROM bike_assignment)";
            stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery();
            SQLPrinter.printResultSet(rs);

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

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT VIN,license_plate, brand_name, bike_type, CC FROM bike WHERE is_operational = TRUE AND bike_type = \"street\" AND VIN NOT IN (SELECT bike_VIN FROM bike_assignment)";
            stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
            SQLPrinter.printResultSet(rs);

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

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT VIN,license_plate, brand_name, bike_type, CC FROM bike WHERE is_operational = TRUE AND bike_type = \"dirt\" AND VIN NOT IN (SELECT bike_VIN FROM bike_assignment)";
            stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
            SQLPrinter.printResultSet(rs);

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

    public static void assignBikeToCourse(String VIN, String courseID) {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        try {
            connection.setAutoCommit(false);
            final String query = "INSERT INTO bike_assignment VALUES(?,?)";
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1,courseID);
            preparedStatement.setObject(1, UUID.fromString(courseID));
            //preparedStatement.setInt(2,VIN);
            preparedStatement.setObject(2, UUID.fromString(VIN));
            preparedStatement.executeUpdate();

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
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Successfully Assigned Bike: " + VIN + " to Course: "+ courseID);

    }

    public static void removeBikeFromCourse(String VIN, String courseID) {

        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            final String query = "DELETE FROM bike_assignment WHERE bike_VIN = ? AND course_id = ?";
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, VIN);
            preparedStatement.setObject(1,UUID.fromString(VIN));
            preparedStatement.setObject(2,UUID.fromString(courseID));
            preparedStatement.executeUpdate();
            connection.commit();

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
        System.out.println("Successfully Removed Bike: " + VIN + " from Course: "+ courseID);

    }

    public static void submitNewBikeDataToDatabase(BikeModel bike) {

        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            final String query = "INSERT INTO bike(license_plate,brand_name, is_operational, bike_type, cc) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setObject(1, bike.getVIN());
            preparedStatement.setInt(1, bike.getLicensePlate());
            preparedStatement.setString(2, bike.getBrandName());
            preparedStatement.setBoolean(3, bike.getIsOperational());
            preparedStatement.setString(4, bike.getBikeType());
            preparedStatement.setInt(5, bike.getCC());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Failed to Add Bike to Garage");
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
        System.out.println("Successfully Added Bike to Garage");

    }

    public static void removeBikeFromDatabase(String VIN) {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        try {
            connection.setAutoCommit(false);
            final String query = "DELETE FROM bike_assignment_history WHERE bike_VIN =?";
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, VIN);
            preparedStatement.setObject(1,UUID.fromString(VIN));
            preparedStatement.executeUpdate();
            final String query2 = "DELETE FROM bike WHERE VIN =?";
            preparedStatement2 = connection.prepareStatement(query2);
            //preparedStatement2.setInt(1, VIN);
            preparedStatement2.setObject(1,UUID.fromString(VIN));
            preparedStatement2.executeUpdate();
            connection.commit();

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
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Successfully Removed Bike: " + VIN + " from Garage");

    }

    public static void submitWorkOrderToDatabase(RepairModel repair) {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        UUID repairID = null;
        ResultSet rs = null;

        //For displaying bike status
        String operationalStatus;
        if(repair.getOperationalStatus())
            operationalStatus = "Available";
        else
            operationalStatus = "Unavailable";

        try {
            connection.setAutoCommit(false);
            final String query = "INSERT INTO repair(problem_date, repair_date, problem_desc, repair_cost ) VALUES (?, NULL,?, NULL) RETURNING repair_id";
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, repair.getRepairID());
            preparedStatement.setDate(1, (Date) repair.getProblemDate());
            preparedStatement.setString(2, repair.getProblemDesc());
            rs = preparedStatement.executeQuery();

            if(rs.next()) {
                repairID = (UUID) rs.getObject("repair_id");
            }

            final String query2 = "INSERT INTO manages_repair VALUES (?,?)";
            preparedStatement2 = connection.prepareStatement(query2);
            //preparedStatement2.setInt(1, repair.getVIN());
            preparedStatement2.setObject(1,UUID.fromString(repair.getVIN()));
            //preparedStatement2.setInt(2, repair.getRepairID());
            preparedStatement2.setObject(2,repairID);
            preparedStatement2.executeUpdate();
            final String query3 = "UPDATE bike SET is_operational = ? WHERE VIN = ?";
            preparedStatement3 = connection.prepareStatement(query3);
            preparedStatement3.setBoolean(1, repair.getOperationalStatus());
            //preparedStatement3.setInt(2, repair.getVIN());
            preparedStatement3.setObject(2,UUID.fromString(repair.getVIN()));
            preparedStatement3.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            System.out.println("Failed to Create Work Order: " + repairID);
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
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (preparedStatement3 != null) {
                    preparedStatement3.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Successfully Created Work Order: " + repairID);
        System.out.println("Bike: " + repair.getVIN() + " is now " + operationalStatus);
    }

    public static void completeWorkOrderInDatabase(RepairModel repair) {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        //For displaying bike status
        String operationalStatus;
        if(repair.getOperationalStatus())
            operationalStatus = "Available";
        else
            operationalStatus = "Unavailable";

        try {
            connection.setAutoCommit(false);
            final String query = "UPDATE repair SET repair_date = ?, repair_cost = ?, problem_desc = ? WHERE repair_id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, (Date) repair.getRepairDate());
            preparedStatement.setFloat(2, repair.getRepairCost());
            preparedStatement.setString(3, repair.getProblemDesc());
            //preparedStatement.setInt(4,repair.getRepairID());
            preparedStatement.setObject(4,UUID.fromString(repair.getRepairID()));
            preparedStatement.executeUpdate();
            final String query2 = "UPDATE bike SET is_operational = ? WHERE VIN = ?";
            preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setBoolean(1, repair.getOperationalStatus());
            //preparedStatement2.setInt(2, repair.getVIN());
            preparedStatement2.setObject(2,UUID.fromString(repair.getVIN()));
            preparedStatement2.executeUpdate();

            connection.commit();

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
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Successfully Completed Work Order: " + repair.getRepairID());
        System.out.println("Bike: " + repair.getVIN() + " is now " + operationalStatus);
    }

    public static void retrieveActiveWorkOrders() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT repair_id,problem_date,problem_desc FROM repair WHERE repair_date IS NULL";
            stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery();
            SQLPrinter.printResultSet(rs);

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

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final String query = "SELECT * FROM repair WHERE repair_date IS NOT NULL";
            stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery();
            SQLPrinter.printResultSet(rs);

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

    public static void retrieveBikeWorkOrders(String VIN) {

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        try {
            final String query = "SELECT repair.repair_id,repair.problem_date,repair.repair_date,repair.problem_desc,repair.repair_cost,manages_repair.bike_VIN FROM repair INNER JOIN manages_repair ON repair.repair_id = manages_repair.repair_id WHERE manages_repair.bike_VIN = ? AND repair.repair_date IS NOT NULL";
            preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //preparedStatement.setInt(1,VIN);
            preparedStatement.setObject(1,UUID.fromString(VIN));
            rs = preparedStatement.executeQuery();
            SQLPrinter.printResultSet(rs);
            System.out.println(GARAGE.INDIVIDUAL_BIKE_COURSE_HISTORY);
            final String query2 = "SELECT course_id FROM bike_assignment_history WHERE bike_VIN = ?";
            preparedStatement2 = connection.prepareStatement(query2, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement2.setObject(1,UUID.fromString(VIN));
            rs2 = preparedStatement2.executeQuery();
            SQLPrinter.printResultSet(rs2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
