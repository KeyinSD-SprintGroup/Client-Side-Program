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
    private static RESTClient restClient;
    public static RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }
        return restClient;
    }
    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

//    public static void main(String[] args) {
//        ClientApplication cliApp = new ClientApplication();
//
//        cliApp.setRestClient(new RESTClient());
//
//        System.out.println("\ngetFlightPlan()*****\n");
//        cliApp.getFlightPlan(0);

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ClientApplication <option> [additional arguments]");
            printMenuAndUsage();
            return;
        }

        String option = args[0];

        switch (option) {
            case "--showall":
                System.out.println("Showall used. Args: " + Arrays.toString(args));
                if (args.length < 2) {
                    System.out.println("Missing sub-option. Please provide a sub-option.");
                } else {
                    String subOption = args[1];
                    switch (subOption) {
                        case "aircraft":
                            generateAircraftReport();
                            break;
                        case "airports":
                            generateAirportReport();
                            break;
                        case "passengers":
                            generatePassengerReport();
                            break;
                        case "cities":
                            generateCityReport();
                            break;
                        default:
                            System.out.println("Invalid sub-option. Please choose a valid sub-option.");
                    }
                }
                break;
            case "--show":
                System.out.println("Show used. Args: " + Arrays.toString(args));
                if (args.length < 3) {
                    System.out.println("Missing sub-option or ID. Please provide a sub-option and ID.");
                } else {
                    String subCommand = args[1];
                    try {
                        long id = Long.parseLong(args[2]);
                        switch (subCommand) {
                            case "airport":
                                getAirportsByCity(id);
                                break;
                            case "passenger":
                                showAircraftByPassenger(id);
                                break;
                            case "flightplan":
                                getFlightPlan(id);
                                break;
                            case "airportpassenger":
                                showAirportsByPassenger(id);
                                break;
                            default:
                                System.out.println("Invalid sub-option. Please choose a valid sub-option.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
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




    public static String generateAircraftReport() {
        List<Aircraft> aircrafts = getRestClient().getAllAircraft();

        StringBuffer report = new StringBuffer();

        for (Aircraft  aircraft : aircrafts) {
            report.append(aircraft.toString());
            if (aircrafts.indexOf(aircraft) != (aircrafts.size() - 1)) {
                report.append("\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }

    public static String generateAirportReport() {
        List<Airport> airports = getRestClient().getAllAirports();

        StringBuffer report = new StringBuffer();

        for (Airport airport : airports) {
            report.append(airport.toString());
            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append("\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }

    public static String generateCityReport() {
        List<City> cities = getRestClient().getAllCity();

        StringBuffer report = new StringBuffer();

        for (City city : cities) {
            report.append(city.toString());
            if (cities.indexOf(city) != (cities.size() - 1)) {
                report.append("\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }

    public static String generatePassengerReport() {
        List<Passenger> passengers = getRestClient().getAllPassengers();

        StringBuffer report = new StringBuffer();

        for (Passenger passenger : passengers) {
            report.append(passenger.toString());
            if (passengers.indexOf(passenger) != (passengers.size() - 1)) {
                report.append("\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }

    public String getCityById(long id) {
        City city = getRestClient().getCityById(id);
//        System.out.println(city);
        return city.toString();
    }

    public static String getPassengerById(long id) {
        Passenger passenger = getRestClient().getPassengerById(id);

//        System.out.println(passenger);
        return passenger.toString();
    }

    public static String getAirportById(long id) {
        Airport airport = getRestClient().getAirportById(id);

//        System.out.println(airport);
        return airport.toString();
    }


    public static String getFlightPlan(long id) {
        List<Long> airportIdList = getRestClient().getAircraftById(id).getAirportIdList();

        StringBuffer report = new StringBuffer();

        for (Long airportID : airportIdList) {
            report.append(getRestClient().getAirportById(airportID).toString());

            if (airportIdList.indexOf(airportID) != (airportIdList.size() - 1)) {
                report.append("\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }

    public static String getAirportsByCity(long id) {
        List<Long> airportIdList = getRestClient().getCityById(id).getAirportIdList();

        StringBuffer report = new StringBuffer();

        for (Long airportID : airportIdList) {
            report.append(getRestClient().getAirportById(airportID).toString());

            if (airportIdList.indexOf(airportID) != (airportIdList.size() - 1)) {
                report.append("\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }

    public static String showAirportsByPassenger(long id) {
        List<Long> aircraftIdList = getRestClient().getPassengerById(id).getAircraftIdList();

        StringBuffer report = new StringBuffer();

        List<Long> airportIdList = new ArrayList<>();
        for (Long aircraftId : aircraftIdList) {
            List<Long> airportIdTempList = getRestClient().getAircraftById(aircraftId).getAirportIdList();

            for (Long airportId : airportIdTempList) {
                if (!airportIdList.contains(airportId)) {
                    airportIdList.add(airportId);
                }
            }
        }

        for (Long airportId : airportIdList) {
            report.append(getRestClient().getAirportById(airportId).toString());

            if (airportIdList.indexOf(airportId) != (airportIdList.size() - 1)) {
                report.append("\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }

    public static String showAircraftByPassenger(long id) {
        List<Long> aircraftIdList = getRestClient().getPassengerById(id).getAircraftIdList();

        StringBuffer report = new StringBuffer();

        for (Long aircraftId : aircraftIdList) {
            report.append(getRestClient().getAircraftById(aircraftId).toString());

            if (aircraftIdList.indexOf(aircraftId) != (aircraftIdList.size() - 1)) {
                report.append("\n");
            }
        }

        System.out.println(report);

        return report.toString();
    }


}
