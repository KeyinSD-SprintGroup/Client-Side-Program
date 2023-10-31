package com.keyin;

import com.keyin.entity.Aircraft;
import com.keyin.entity.Airport;
import com.keyin.entity.City;
import com.keyin.entity.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientApplication {
    private static final RESTClient restClient = new RESTClient();
    public static void main(String[] args) {

        boolean running = true;
        while (running){
            System.out.println("---------------------------------------------------------");
            System.out.println("Welcome to the Cities and Airports Information System!");
            System.out.println("---------------------------------------------------------");
            System.out.println();
            System.out.println("---------------------------------------------------------");
            System.out.println("Choose an option:");
            System.out.println("1. List airports by city");
            System.out.println("2. List aircraft by passenger");
            System.out.println("3. List take-off and landing airports for aircraft");
            System.out.println("4. List airports used by passenger");
            System.out.println("5. List all aircraft");
            System.out.println("6. List all airports");
            System.out.println("7. List all cities");
            System.out.println("8. List all passengers");
            System.out.println("9. Exit");
            System.out.println("---------------------------------------------------------");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
//                    getAirportsByCity();
                    break;
                case 2:
//                    getAircraftByPassenger();
                    break;
                case 3:
//                    getTakeOffAndLandingAirports();
                    break;
                case 4:
//                    getAirportsUsedByPassenger();
                    break;
                case 5:
                    getAllAircraft();
                    break;
                case 6:
//                    getAllAirport();
                    break;
                case 7:
                    getAllCity();
                    break;
                case 8:
                    getAllPassenger();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
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
