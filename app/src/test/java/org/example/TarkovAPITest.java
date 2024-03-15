package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class TarkovAPITest {
    
    @Test
    public void testGetJsonString() throws IOException, InterruptedException {
        TarkovAPI tarkovAPI = new TarkovAPI();
        String queryId = "123"; // Replace with your desired query ID
        String expectedJsonString = "{\"id\":123, \"name\":\"example\", \"shortName\":\"ex\"}"; // Replace with your expected JSON string
        
        String actualJsonString = tarkovAPI.getJsonString(queryId);
        
        assertEquals(expectedJsonString, actualJsonString);
        System.out.println(actualJsonString); // Print out the JSON string
    }
}