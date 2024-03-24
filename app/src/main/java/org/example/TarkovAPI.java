package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TarkovAPI {
    public String getJsonString(String queryId) throws IOException, InterruptedException {
        // Build the GraphQL query as a string
        String query = new Query(queryId).toString();

        // Create an instance of HttpClient for making HTTP requests
        HttpClient client = HttpClient.newBuilder().build();

        // Build the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tarkov.dev/graphql"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

        // Send the HTTP request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Extract the response body as a string
        String jsonString = response.body();

        // Print the response body
        return jsonString;
    }

}
