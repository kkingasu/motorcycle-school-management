package main.models;

import java.util.Date;

public class StaffModel {
    String name;
    String address;
    Date dob;
    int phoneNumber;
    Boolean classAvailability;
    Boolean streetBikeAvailability;
    Boolean dirtBikeAvailability;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public Date getDob() {return dob;}

    public void setDob(Date dob) {this.dob = dob;}

    public int getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber;}

    public Boolean getClassAvailability() {return classAvailability;}

    public void setClassAvailability(Boolean classAvailability) {this.classAvailability = classAvailability;}

    public Boolean getStreetBikeAvailability() {return streetBikeAvailability;}

    public void setStreetBikeAvailability(Boolean streetBikeAvailability) {this.streetBikeAvailability = streetBikeAvailability;}

    public Boolean getDirtBikeAvailability() {return dirtBikeAvailability;}

    public void setDirtBikeAvailability(Boolean dirtBikeAvailability) {this.dirtBikeAvailability = dirtBikeAvailability;}
}
