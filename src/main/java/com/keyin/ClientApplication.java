package com.keyin;

import com.keyin.entity.Aircraft;
import com.keyin.entity.Airport;

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
            System.out.println("6. Exit");
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
