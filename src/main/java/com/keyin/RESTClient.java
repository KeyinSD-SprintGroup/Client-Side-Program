package com.keyin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.entity.Aircraft;
import com.keyin.entity.Airport;
import com.keyin.entity.City;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {
    final static CloseableHttpClient httpClient = HttpClients.createDefault();

    public List<Aircraft> getAllAircraft() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/aircraft");

        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println(response.getStatusLine().getStatusCode() + ": Connection is bad.");
            return new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Aircraft> aircraftList = new ArrayList<>();

        aircraftList = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<Aircraft>>() {});
        System.out.println(aircraftList.size() + " found");

        return aircraftList;
    }

    public Airport getAirportById(long id) throws IOException {
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
        String URL = String.format("http://localhost:8080/airport_by_id?id=%s", id);
        HttpGet request = new HttpGet(URL);

        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println(response.getStatusLine().getStatusCode() + ": Connection is bad.");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        Airport airport;

        airport = mapper.readValue(EntityUtils.toString(entity), new TypeReference<Airport>() {});

        return airport;
    }

    public List<Airport> getAllAirport() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/airport");

        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println(response.getStatusLine().getStatusCode() + ": Connection is bad.");
            return new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Airport> airportList;

        airportList = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<Airport>>() {});
        System.out.println(airportList.size() + " found");

        return airportList;
    }

    public City getCityById(long id) throws IOException {
        String URL = String.format("http://localhost:8080/city_by_id?id=%s", id);
        HttpGet request = new HttpGet(URL);

        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println(response.getStatusLine().getStatusCode() + ": Connection is bad.");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        City city;

        city = mapper.readValue(EntityUtils.toString(entity), new TypeReference<City>() {});

        return city;
    }
}