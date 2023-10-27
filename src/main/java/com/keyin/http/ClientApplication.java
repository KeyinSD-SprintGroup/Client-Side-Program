package com.keyin.http;

import com.keyin.http.RESTClient;

import java.util.Scanner;

public class ClientApplication {

    public static void main(String[] args) {
        RESTClient restClient = new RESTClient("http://localhost:8080");

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
        System.out.println("5. Exit");
        System.out.println("---------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                getAirportsByCity(restClient);
                break;
            case 2:
                getAircraftByPassenger(restClient);
                break;
            case 3:
                getTakeOffAndLandingAirports(restClient);
                break;
            case 4:
                getAirportsUsedByPassenger(restClient);
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public static void getAirportsByCity(RESTClient restClient) {
        System.out.println("Enter the desired city:");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();

        String airportsJson = restClient.getAllAirportsAsJson();
        System.out.println("Airports in " + city + ": " + airportsJson);
    }

    public static void getAircraftByPassenger(RESTClient restClient) {
        System.out.println("Enter the desired passenger:");
        Scanner scanner = new Scanner(System.in);
        String passenger = scanner.nextLine();

        String aircraftJson = restClient.getAllAircraftAsJson();
        System.out.println("Aircraft for passenger " + passenger + ": " + aircraftJson);
    }

    public static void getTakeOffAndLandingAirports(RESTClient restClient) {
        System.out.println("Enter the desired aircraft:");
        Scanner scanner = new Scanner(System.in);
        String aircraft = scanner.nextLine();

        String airportsJson = restClient.getAllAirportsAsJson();
        System.out.println("Airports for aircraft " + aircraft + ": " + airportsJson);
    }

    public static void getAirportsUsedByPassenger(RESTClient restClient) {
        System.out.println("Enter the desired passenger:");
        Scanner scanner = new Scanner(System.in);
        String passenger = scanner.nextLine();

        String airportsJson = restClient.getAllAirportsAsJson();
        System.out.println("Airports used by passenger " + passenger + ": " + airportsJson);
    }
}
