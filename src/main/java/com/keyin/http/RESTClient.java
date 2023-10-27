package com.keyin.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RESTClient {
    private final String baseUrl;
    private final HttpClient httpClient;

    public RESTClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getAllAirportsAsJson() {
        return fetchDataFromServer("/airport");
    }

    public String getAllAircraftAsJson() {
        return fetchDataFromServer("/aircraft");
    }

    public String getAllCitiesAsJson() {
        return fetchDataFromServer("/city");
    }

    public String getAllPassengersAsJson() {
        return fetchDataFromServer("/passenger");
    }

    private String fetchDataFromServer(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + endpoint))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
