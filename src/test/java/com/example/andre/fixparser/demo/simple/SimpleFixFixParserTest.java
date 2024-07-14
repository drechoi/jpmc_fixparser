package com.example.andre.fixparser.demo.simple;

import com.example.andre.fixparser.FixParserTestBase;
import com.example.andre.fixparser.api.FixParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimpleFixFixParserTest extends FixParserTestBase {
    private final FixParser<SimpleKeyValueRecord> parser = new SimpleFixParser();


    @Test
    public void testParseNewOrder(){
        SimpleKeyValueRecord result = parser.parse(newOrderMessage);

        assertNotNull(result);
        assertEquals(result.getAttribute(1), "A8466653547");
        assertEquals(result.getAttribute(54), "1");

        // System.out.println(result);
    }


    @Test
    public void testParseCancelOrder(){
        SimpleKeyValueRecord result = parser.parse(cancelOrderMessage);

        assertNotNull(result);
        assertEquals(result.getAttribute(1), "A8466653547");
        assertEquals(result.getAttribute(54), "1");

        System.out.println(result);
    }



}