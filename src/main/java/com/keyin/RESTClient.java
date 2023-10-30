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
import java.lang.reflect.Type;
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

        int counter = 0;

        aircraftList = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<Aircraft>>() {});
        System.out.println(aircraftList.size() + " found");

        return aircraftList;
    }

    public List<City> getAllCity() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/city");

        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println(response.getStatusLine().getStatusCode() + ": Connection is bad.");
            return new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        List<City> cityList = new ArrayList<>();

        int counter = 0;

        cityList = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<City>>() {});
        System.out.println(cityList.size() + " found");

        return cityList;
    }

    public Airport getAirportById(long id) throws IOException {
        String URL = String.format("http://localhost:8080/airport_search?id=%s", id);
        HttpGet request = new HttpGet(URL);

        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println(response.getStatusLine().getStatusCode() + ": Connection is bad.");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Airport> airportList;

        airportList = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<Airport>>() {});

        return airportList.get(0);
    }
}