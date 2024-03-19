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
          if (itemsArray.isEmpty()) {
              throw new JSONException("No items found in the JSON data");
          }
          JSONObject firstItem = (JSONObject) itemsArray.get(0); // Assuming there is only one item
          if (!firstItem.containsKey("avg24hPrice")) {
              throw new JSONException("Cannot find avg24hPrice in the JSON data");
          }
          Long avg24hPrice = (Long) firstItem.get("avg24hPrice"); // Extracting avg24hPrice as a Long
          return String.valueOf(avg24hPrice); // Converting Long to String
      } catch (ParseException | JSONException e) {
          e.printStackTrace();
              System.err.println("Error: " + e.getMessage());
          return null;
      }
  }
}