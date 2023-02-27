package main.models;

import java.util.Date;

public class StaffModel {
    String name;
    String address;
    Date dob;
    int phoneNumber;
    String classAvailability;
    String streetBikeAvailability;
    String dirtBikeAvailability;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public Date getDob() {return dob;}

    public void setDob(Date dob) {this.dob = dob;}

    public int getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getClassAvailability() {return classAvailability;}

    public void setClassAvailability(String classAvailability) {this.classAvailability = classAvailability;}

    public String getStreetBikeAvailability() {return streetBikeAvailability;}

    public void setStreetBikeAvailability(String streetBikeAvailability) {this.streetBikeAvailability = streetBikeAvailability;}

    public String getDirtBikeAvailability() {return dirtBikeAvailability;}

    public void setDirtBikeAvailability(String dirtBikeAvailability) {this.dirtBikeAvailability = dirtBikeAvailability;}
}
