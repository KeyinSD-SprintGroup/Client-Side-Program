package com.keyin.entity;


import java.util.ArrayList;
import java.util.List;

public class Aircraft {
    private long id;
    private String type;
    private String airlineName;
    private Integer numberOfPassengers;
    private List<Long> passengerIdList;
    private List<Long> airportIdList;

    public void appendAirport(long id) {
        if (airportIdList == null) {
            airportIdList = new ArrayList<>();
        }
        airportIdList.add(id);
    }

    public List<Long> getAirportIdList() {
        if (airportIdList == null) {
            airportIdList = new ArrayList<>();
        }
        return airportIdList;
    }
    public void appendPassenger(long id) {
        if (passengerIdList == null) {
            passengerIdList = new ArrayList<>();
        }
        passengerIdList.add(id);
    }
    public List<Long> getPassengerIdList() {
        if (passengerIdList == null) {
            passengerIdList = new ArrayList<>();
        }
        return passengerIdList;
    }
    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        if (numberOfPassengers < 0) {
            throw new IllegalArgumentException("Number of passengers must be 0 or greater");
        }
        this.numberOfPassengers = numberOfPassengers;
    }
}
