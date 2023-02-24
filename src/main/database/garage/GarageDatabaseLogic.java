package main.database.garage;
import main.models.garage.BikeModel;
import main.models.garage.RepairModel;
import main.startUpApplication;
import java.sql.*;

public class GarageDatabaseLogic {

    private static Connection connection = startUpApplication.connection;
    public static void retrieveTotalAvailableBikes() {

    }

    public static void retrieveAvailableStreetBikes() {

    }

    public static void retrieveAvailableDirtBikes() {

    }

    public static void assignBikeToCourse(int VIN, int courseID) {

    }

    public static void removeBikeFromCourse(int VIN, int courseID) {

    }

    public static void submitNewBikeDataToDatabase(BikeModel bike) {

    }

    public static void removeBikeFromDatabase(int VIN) {

    }

    public static void submitWorkOrderToDatabase(RepairModel repair) {

    }

    public static void assignBikeToWorkOrder(int VIN, int repairID) {

    }
    public static void setBikeOperationalStatus(int VIN, boolean status) {

    }

    public static void completeWorkOrderInDatabase(RepairModel repair) {

    }

    public static void retrieveActiveWorkOrders() {

    }

    public static void retrieveTotalClosedWorkOrders() {

    }

    public static void retrieveBikeWorkOrders(int VIN) {

    }



}
