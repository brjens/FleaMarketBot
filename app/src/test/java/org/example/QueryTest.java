package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.checkerframework.checker.units.qual.t;


public class QueryTest {

    @Test
    void queryTest(){
        String queryId = "foo";
        String queryClasString= new Query(queryId).toString();

        System.out.println(queryClasString);

        String expectedString = "{\"query\": \"{ items(name: \\\"foo\\\") {name avg24hPrice } }\"}";

        assertEquals(expectedString, queryClasString);
    }

    @Test
    void emptyStringTest(){
        String queryId = "";
    }
    
}
