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
import org.apache.http.HttpEntity;
//import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
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
            if (response.statusCode()==200) {
                System.out.println("***** " + response.body());
            } else {
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
            if (response.statusCode()==200) {
                System.out.println("***** " + response.body());
            } else {
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
            if (response.statusCode()==200) {
                System.out.println("***** " + response.body());
            } else {
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
            if (response.statusCode()==200) {
                System.out.println("***** " + response.body());
            } else {
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

//    public Airport getAirportById(long id) throws IOException {
//        String URL = String.format("http://localhost:8080/airport_search?id=%s", id);
//        HttpGet request = new HttpGet(URL);
//
//        CloseableHttpResponse response = httpClient.execute(request);
//        HttpEntity entity = response.getEntity();
//
//        if (response.getStatusLine().getStatusCode() != 200) {
//            System.out.println(response.getStatusLine().getStatusCode() + ": Connection is bad.");
//            return null;
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        List<Airport> airportList;
//
//        airportList = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<Airport>>() {});
//
//        return airportList.get(0);
//    }
//
//    public Passenger getPassengerById(long id) throws IOException {
//        String URL = String.format("http://localhost:8080/passenger_search?id=%s", id);
//        HttpGet request = new HttpGet(URL);
//
//        CloseableHttpResponse response = httpClient.execute(request);
//        HttpEntity entity = response.getEntity();
//
//        if (response.getStatusLine().getStatusCode() != 200) {
//            System.out.println(response.getStatusLine().getStatusCode() + ": Connection is bad.");
//            return null;
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        List<Passenger> passengerList;
//
//        passengerList = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<Passenger>>() {});
//
//        return passengerList.get(0);
//    }
//    public City getCityById(long id) throws IOException {
//        String URL = String.format("http://localhost:8080/city_by_id?id=%s", id);
//        HttpGet request = new HttpGet(URL);
//
//        CloseableHttpResponse response = httpClient.execute(request);
//        HttpEntity entity = response.getEntity();
//
//        if (response.getStatusLine().getStatusCode() != 200) {
//            System.out.println(response.getStatusLine().getStatusCode() + ": Connection is bad.");
//            return null;
//        }
//
//        ObjectMapper mapper = new ObjectMapper();
//        City city;
//
//        city = mapper.readValue(EntityUtils.toString(entity), new TypeReference<City>() {});
//
//        return city;
//    }
}
