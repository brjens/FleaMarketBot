package org.example;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {

    public static String getAvg24hPrice(String jsonString) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject dataObject = (JSONObject) jsonObject.get("data");
            JSONArray itemsArray = (JSONArray) dataObject.get("items");
            JSONObject firstItem = (JSONObject) itemsArray.get(0); // Assuming there is only one item
            Long avg24hPrice = (Long) firstItem.get("avg24hPrice"); // Extracting avg24hPrice as a Long
            return String.valueOf(avg24hPrice); // Converting Long to String
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}