package com.keyin;

import com.keyin.entity.Aircraft;
import com.keyin.entity.Airport;
import com.keyin.entity.City;
import com.keyin.entity.Passenger;

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
//                                break;
                            case "airports":
                                generateAirportReport();
//                                break;
                            case "passengers":
                                generatePassengerReport();
//                                break;
                            case "cities":
                                generateCityReport();
//                                break;
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




        public static String generateAircraftReport() {
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

    public static String generateAirportReport() {
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

    public static String generateCityReport() {
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

    public static String generatePassengerReport() {
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

    public String getCityById(long id) {
        City city = getRestClient().getCityById(id);
        System.out.println(city);
        return city.toString();
    }

    public String getPassengerById(long id) {
        Passenger passenger = getRestClient().getPassengerById(id);

        System.out.println(passenger);
        return passenger.toString();
    }

    public static String getAirportById(long id) {
        Airport airport = getRestClient().getAirportById(id);

//        System.out.println(airport);
        return airport.toString();
    }


    public static void getFlightPlan(long id) {
        List<Long> airportIdList = getRestClient().getAircraftById(id).getAirportIdList();
        for (int i = 0; i < airportIdList.size(); i++) {
            System.out.printf("Airport(%s)\n", i);
            System.out.println(getAirportById(airportIdList.get(i)));
        }
    }

    public static void getAirportsByCity(long id) {
        List<Long> airportIdList = getRestClient().getCityById(id).getAirportIdList();
        for (int i = 0; i < airportIdList.size(); i++) {
            System.out.printf("Airport(%s)\n", i);
            System.out.println(getAirportById(airportIdList.get(i)));
        }
    }

    public static void showAirportsByPassenger(long id) {
        List<Long> aircraftIdList = getRestClient().getPassengerById(id).getAircraftIdList();
        int count = 0;
        for (Long aircraftId : aircraftIdList) {
            List<Long> airportList = getRestClient().getAircraftById(aircraftId).getAirportIdList();
            for (Long airportId : airportList) {
                System.out.printf("Airport(%s)\n", count);
                System.out.println(getRestClient().getAirportById(airportId));
                count ++;
            }
        }
    }
}
