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
//    private static final RESTClient restClient = new RESTClient();
    private RESTClient restClient;

    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }
        return restClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    public static void main(String[] args) {
        ClientApplication cliApp = new ClientApplication();

        cliApp.setRestClient(new RESTClient());

        Scanner scanner = new Scanner(System.in);
        printMenuAndUsage();

        String input = scanner.nextLine();
        String[] inputParts = input.split(" ");

        if (inputParts.length < 2 || !"ClientApplication".equals(inputParts[0])) {
            System.out.println("Invalid input format. The input must start with 'ClientApplication'.");
            return;
        }

        String option = inputParts[1];

        if ("--showall".equals(option)) {
            if (inputParts.length < 3) {
                System.out.println("Missing sub-option. Please provide a sub-option.");
            } else {
                String subOption = inputParts[2];
                switch (subOption) {
                    case "aircraft":
                        cliApp.generateAircraftReport();
                        break;
                    case "airports":
                        cliApp.generateAirportReport();
                        break;
                    case "passengers":
                        cliApp.generatePassengerReport();
                        break;
                    case "cities":
                        cliApp.generateCityReport();
                        break;
                    default:
                        System.out.println("Invalid sub-option. Please choose a valid sub-option.");
                }
            }
        } else if ("--show".equals(option)) {
            if (inputParts.length < 4) {
                System.out.println("Missing sub-option or ID. Please provide a sub-option and ID.");
            } else {
                String subCommand = inputParts[2];
                try {
                    long id = Long.parseLong(inputParts[3]);
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
        } else {
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

    public String generateAircraftReport() {
        List<Aircraft> aircrafts = getRestClient().getAllAircraft();

        StringBuffer report = new StringBuffer();

        for (Aircraft  aircraft : aircrafts) {
            report.append(aircraft.getType());
            report.append(" - ");
            report.append(aircraft.getAirlineName());

            if (aircrafts.indexOf(aircraft) != (aircrafts.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    public String generateAirportReport() {
        List<Airport> airports = getRestClient().getAllAirports();

        StringBuffer report = new StringBuffer();

        for (Airport airport : airports) {
            report.append(airport.getName());
            report.append(" - ");
            report.append(airport.getCode());

            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    public String generateCityReport() {
        List<City> cities = getRestClient().getAllCity();

        StringBuffer report = new StringBuffer();

        for (City city : cities) {
            report.append(city.getName());
            report.append(" - ");
            report.append(city.getState());

            if (cities.indexOf(city) != (cities.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    public String generatePassengerReport() {
        List<Passenger> passengers = getRestClient().getAllPassengers();

        StringBuffer report = new StringBuffer();

        for (Passenger passenger : passengers) {
            report.append(passenger.getFirstName() + " " + passenger.getLastName());
            report.append(" - ");
            report.append(passenger.getPhoneNumber());

            if (passengers.indexOf(passenger) != (passengers.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
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
//    public static void getCityById(long id) {
//        City city;
//        try {
//            city = restClient.getCityById(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//        System.out.println("\t\t\t" + city);
//    }
//
//    public static void getPassengerById(long id) {
//        Passenger passenger;
//        try {
//            passenger = restClient.getPassengerById(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//        System.out.println("\t\t\t" + passenger);
//    }
//
//    public static void getAirportById(long id) {
//        Airport airport;
//        try {
//            airport = restClient.getAirportById(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//        System.out.println("\t\t\t" + airport);
//    }
}
