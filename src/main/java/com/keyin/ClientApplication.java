package com.keyin;

import com.keyin.entity.Aircraft;
import com.keyin.entity.Airport;
import com.keyin.entity.City;
import com.keyin.entity.Passenger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientApplication {
    private static final RESTClient restClient = new RESTClient();
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ClientApplication <option> [additional arguments]");
            printMenuAndUsage();
            return;
        }

        String option = args[0];

        switch (option) {
            case "--showall":
                if (args.length < 2) {
                    System.out.println("Missing sub-option. Please provide a sub-option.");
                } else {
                    String subOption = args[1];
                    switch (subOption) {
                        case "aircraft":
                            getAllAircraft();
                            break;
                        case "airports":
                            // getAllAirports();
                            break;
                        case "passengers":
                            getAllPassenger();
                            break;
                        case "cities":
                            // getAllCities();
                            break;
                        default:
                            System.out.println("Invalid sub-option. Please choose a valid sub-option.");
                    }
                }
                break;
            case "--show":
                if (args.length < 4) {
                    System.out.println("Missing sub-option or ID. Please provide a sub-option and ID.");
                } else {
                    String subCommand = args[2];
                    try {
                        long id = Long.parseLong(args[3]);
                        switch (subCommand) {
                            case "airport":
                                // showAirportsById(id);
                                break;
                            case "passenger":
                                // showAircraftByPassenger(id);
                                break;
                            case "flightplan":
                                // showTakeOffAndLandingAirports(id);
                                break;
                            case "airportpassenger":
                                // showAirportsUsedByPassenger(id);
                                break;
                            default:
                                System.out.println("Invalid sub-option. Please choose a valid sub-option.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID. ID must be an integer.");
                    }
                }
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }

    public static void printMenuAndUsage() {
        System.out.println("Cities and Airports Information System - CLI");
        System.out.println("Usage: ClientApplication <option> [additional arguments]");
        System.out.println("Options:");
        System.out.println(" --showall airports                   shows list of all airports");
        System.out.println(" --showall aircraft                   shows list of all aircraft");
        System.out.println(" --showall passengers                 shows list of all passengers");
        System.out.println(" --showall cities                     shows list of all cities");
        System.out.println(" --show airport <id>                  shows the airports in a specific city");
        System.out.println(" --show passenger <id>                shows which aircraft a specific passenger used");
        System.out.println(" --show flightplan <id>               shows the take-off and landing cities of an aircraft");
        System.out.println(" --show airportpassenger <id>         shows which airports are used by a specific passenger");
    }





//    public static void getAirportsByCity() {
//        System.out.println("Enter the desired city:");
//        Scanner scanner = new Scanner(System.in);
//        String city = scanner.nextLine();
//
//        String airportsJson = restClient.getAllAirportsAsJson();
//        System.out.println("Airports in " + city + ": " + airportsJson);
//    }
//
//    public static void getAircraftByPassenger() {
//        System.out.println("Enter the desired passenger:");
//        Scanner scanner = new Scanner(System.in);
//        String passenger = scanner.nextLine();
//
//        String aircraftJson = restClient.getAllAircraftAsJson();
//        System.out.println("Aircraft for passenger " + passenger + ": " + aircraftJson);
//    }
//
//    public static void getTakeOffAndLandingAirports() {
//        System.out.println("Enter the desired aircraft:");
//        Scanner scanner = new Scanner(System.in);
//        String aircraft = scanner.nextLine();
//
//        List airportsJson = restClient.getAllAirportsAsJson();
//        System.out.println("Airports for aircraft " + aircraft + ": " + airportsJson);
//    }
//
//    public static void getAirportsUsedByPassenger() {
//        System.out.println("Enter the desired passenger:");
//        Scanner scanner = new Scanner(System.in);
//        String passenger = scanner.nextLine();
//
//        List airportsJson = restClient.getAllAirportsAsJson();
//        System.out.println("Airports used by passenger " + passenger + ": " + airportsJson);
//    }


    public static void getAllPassenger() {
        List<Passenger> passengerList;
        try {
            passengerList = restClient.getAllPassenger();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        int passengerCount = 0;
        for (Passenger passenger : passengerList) {
            System.out.printf("\nPassenger(%s):\n********\n", passengerCount);
            System.out.println(passenger.passengerToString());
            passengerCount++;
        }
    }

    public static void getAllAircraft() {
        List<Aircraft> airCraftList;
        try {
            airCraftList = restClient.getAllAircraft();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        int aircraftCount = 0;
        for (Aircraft aircraft : airCraftList) {
            System.out.printf("\nAircraft(%s):\n********\n", aircraftCount);
            System.out.println("\tID:                 " + aircraft.getId());
            System.out.println("\tType:               " + aircraft.getType());
            System.out.println("\tAirline Name:       " + aircraft.getAirlineName());
            System.out.println("\tPassenger Capacity: " + aircraft.getNumberOfPassengers());
            System.out.println("\tAirport ID List:    ");
            int airportCount = 0;
            for (long id : aircraft.getAirportIdList()) {
                System.out.printf("\t\tAirport (%s)\n", airportCount);
                getAirportById(id);
                airportCount++;
            }
            System.out.println("\tPassenger ID List:  " + aircraft.getPassengerIdList());
            aircraftCount++;
        }
    }

    public static void getAllCity() {
        List<City> cityList;
        try {
            cityList = restClient.getAllCity();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        int cityCount = 0;
        for (City city : cityList) {
            System.out.printf("\nCity(%s):\n********\n", cityCount);
            System.out.println("\tID:                 " + city.getId());
            System.out.println("\tCity Name:          " + city.getName());
            System.out.println("\tState:              " + city.getState());
            System.out.println("\tPopulation:         " + city.getPopulation());
            System.out.println("\tAirport ID List:       ");
            int airportCount = 0;
            for (long id : city.getAirportIdList()) {
                System.out.printf("\t\tAirport (%s)\n", airportCount);
                getAirportById(id);
                airportCount++;
            }
            cityCount ++;
        }
    }

    public static void getCityById(long id) {
        City city;
        try {
            city = restClient.getCityById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println("\t\t\t" + city);
    }

    public static void getAllAirport() {
        List<Airport> airportList;
        try {
            airportList = restClient.getAllAirport();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        int airportCount = 0;
        for (Airport airport : airportList) {
            System.out.printf("\nAirport(%s):\n********\n", airportCount);
            System.out.println("\tID:                 " + airport.getId());
            System.out.println("\tName:               " + airport.getName());
            System.out.println("\tCode:       " + airport.getCode());
            System.out.println("\tAirport ID List:    ");
            getCityById(airport.getCityId());
            airportCount++;
        }
    }

    public static void getPassengerById(long id) {
        Passenger passenger;
        try {
            passenger = restClient.getPassengerById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println("\t\t\t" + passenger);
    }

    public static void getAirportById(long id) {
        Airport airport;
        try {
            airport = restClient.getAirportById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println("\t\t\t" + airport);
    }
}
