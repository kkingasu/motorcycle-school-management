package main.models;

import java.util.Date;

public class StaffModel {
    String name;
    String address;
    Date dob;
    int phoneNumber;
    boolean classAvailability;
    boolean streetBikeAvailability;
    boolean dirtBikeAvailability;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public Date getDob() {return dob;}

    public void setDob(Date dob) {this.dob = dob;}

    public int getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber;}

    public boolean getClassAvailability() {return classAvailability;}

    public void setClassAvailability(boolean classAvailability) {this.classAvailability = classAvailability;}

    public boolean getStreetBikeAvailability() {return streetBikeAvailability;}

    public void setStreetBikeAvailability(boolean streetBikeAvailability) {this.streetBikeAvailability = streetBikeAvailability;}

    public boolean getDirtBikeAvailability() {return dirtBikeAvailability;}

    public void setDirtBikeAvailability(boolean dirtBikeAvailability) {this.dirtBikeAvailability = dirtBikeAvailability;}
}
