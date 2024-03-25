package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {

    public static String getItemString(String jsonString, String propertyName) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject dataObject = (JSONObject) jsonObject.get("data");
            JSONArray itemsArray = (JSONArray) dataObject.get("items");
            if (itemsArray.isEmpty()) {
                throw new JSONException("No items found in the JSON data");
            }
            JSONObject firstItem = (JSONObject) itemsArray.get(0); // Assuming there is only one item
            if (!firstItem.containsKey(propertyName)) {
                throw new JSONException("Cannot find" + propertyName + "in the JSON data");
            }
            Object propertyValue = firstItem.get(propertyName); // Extracting avg24hPrice as a Long
            return String.valueOf(propertyValue); // Converting Long to String
        } catch (ParseException | JSONException e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
}