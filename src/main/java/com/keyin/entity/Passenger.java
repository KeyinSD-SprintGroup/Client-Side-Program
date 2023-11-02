package com.keyin.entity;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Long> aircraftIdList;

    public List<Long> getAircraftIdList() {
        if (aircraftIdList == null) {
            aircraftIdList = new ArrayList<>();
        }
        return aircraftIdList;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String passengerToString() {
        return
                "ID:" + " " + id + "\n" +
                "Name:" + " " + firstName + "  " + lastName + '\n' +
                "Phone Number:" + " " + phoneNumber;
    }
}
