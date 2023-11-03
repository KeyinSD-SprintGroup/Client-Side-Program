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

    public void appendAircraft(long id) {
        if (aircraftIdList == null) {
            aircraftIdList = new ArrayList<>();
        }
        aircraftIdList.add(id);
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

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", aircraftIdList=" + aircraftIdList +
                '}';
    }
}
