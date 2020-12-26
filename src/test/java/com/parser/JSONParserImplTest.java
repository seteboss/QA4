package com.parser;


import org.junit.Test;
import util.MockData;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JSONParserImplTest {

    private JSONParser jsonParser;

    @Test
    public void testGettingJsonString() {

        jsonParser = new JSONParserImpl();

        String jsonString = MockData.getMockResponseBody2();
        List<String> repos = jsonParser.parsReport(jsonString);
        assertEquals("Fitenko", repos.get(0));
        assertEquals("tnko", repos.get(1));
        assertEquals("ST-MAN", repos.get(2));
        System.out.println("finished.");
    }


}
