package org.example;

public class Query {

    String response;

    public Query() {
        response = "";
    }

    public Query(String queryId) {
        setPriceResponse(queryId);
    }

    public void setPriceResponse(String queryId) {
        // Construct the GraphQL query string with the variable using string formatting
        response = String.format("{\"query\": \"{ items(name: \\\"%s\\\") {name avg24hPrice } }\"}", queryId);
    }

    public String toString() {
        return response;
    }
}
