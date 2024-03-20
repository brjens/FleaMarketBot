package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonParserTest {

    
   @Test
    void testSalewaData() {
        String jsonString = "{\"data\":{\"items\":[{\"name\":\"Salewa first aid kit\",\"avg24hPrice\":34317}]}}";
        String expectedPrice = "34317";

        String actualPrice = JsonParser.getItemString(jsonString, "avg24hPrice");

        assertEquals(expectedPrice, actualPrice);
    }


   @Test
    void testCrackersData() {
        String jsonString = "{\"data\":{\"items\":[{\"name\":\"Millitary Crackers\",\"avg24hPrice\":16575}]}}";
        String expectedPrice = "16575";

        String actualPrice = JsonParser.getItemString(jsonString, "avg24hPrice");

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testRetrieveName() {
        String jsonString = "{\"data\":{\"items\":[{\"name\":\"Salewa first aid kit\",\"avg24hPrice\":34317}]}}";
        String expectedName = "Salewa first aid kit";

        String actualName = JsonParser.getItemString(jsonString, "name");

        assertEquals(expectedName, actualName);
    }

}