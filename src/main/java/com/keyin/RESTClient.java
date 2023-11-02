package com.keyin;

import java.net.URI;
import java.net.http.HttpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.entity.Aircraft;
import com.keyin.entity.Airport;
import com.keyin.entity.City;
import com.keyin.entity.Passenger;


import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {
    public List<Aircraft> getAllAircraft() {
        List<Aircraft> aircrafts = new ArrayList<Aircraft>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/aircraft")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            aircrafts = mapper.readValue(response.body(), new TypeReference<List<Aircraft>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return aircrafts;
    }

    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<Airport>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/airport")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            airports = mapper.readValue(response.body(), new TypeReference<List<Airport>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return airports;
    }

    public List<City> getAllCity() {
        List<City> cities = new ArrayList<City>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/city")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            cities = mapper.readValue(response.body(), new TypeReference<List<City>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return cities;
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> passengers = new ArrayList<Passenger>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/passenger")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            passengers = mapper.readValue(response.body(), new TypeReference<List<Passenger>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    public Aircraft getAircraftById(long id) {
        Aircraft aircraft;
        String URL = String.format("http://localhost:8080/aircraft_by_id?id=%s", id);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            aircraft = mapper.readValue(response.body(), new TypeReference<Aircraft>(){});

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return aircraft;
    }

    public Airport getAirportById(long id) {
        Airport airport;
        String URL = String.format("http://localhost:8080/airport_by_id?id=%s", id);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            airport = mapper.readValue(response.body(), new TypeReference<Airport>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return airport;
    }

    //
    public Passenger getPassengerById(long id) {
        Passenger passenger;
        String URL = String.format("http://localhost:8080/passenger_by_id?id=%s", id);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            passenger = mapper.readValue(response.body(), new TypeReference<Passenger>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return passenger;
    }

    public City getCityById(long id) {
        City city;
        String URL = String.format("http://localhost:8080/city_by_id?id=%s", id);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            city = mapper.readValue(response.body(), new TypeReference<City>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return city;
    }
}