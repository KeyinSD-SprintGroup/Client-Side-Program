package com.keyin.entity;

import java.util.ArrayList;
import java.util.List;

public class City {
    private long id;
    private String name;
    private String state;
    private int population;
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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                '}';
    }
}
