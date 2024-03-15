package org.example;
public class JsonParser {
    public static String getAvg24hPrice(String jsonString) {
        int startIndex = jsonString.indexOf("\"avg24hPrice\":") + 14;
        int endIndex = jsonString.indexOf("}", startIndex);
        String avg24hPriceString = jsonString.substring(startIndex, endIndex);
        return avg24hPriceString;
    }
}