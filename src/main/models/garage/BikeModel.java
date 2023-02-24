package main.models.garage;

import java.text.SimpleDateFormat;
public class BikeModel {

    int VIN;
    int licensePlate;
    String brandName;
    Boolean isOperational;
    String bikeType;
    int CC;

    public void setVIN(int VIN) {
        this.VIN = VIN;
    }

    public int getVIN() {
        return this.VIN;
    }

    public void setLicensePlate(int licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getLicensePlate() {
        return this.licensePlate;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setIsOperational(boolean isOperational) {
        this.isOperational = isOperational;
    }

    public boolean getIsOperational() {
        return this.isOperational;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public String getBikeType() {
        return this.bikeType;
    }

    public void setCC(int CC) {
        this.CC = CC;
    }

    public int getCC() {
        return this.CC;
    }

}
