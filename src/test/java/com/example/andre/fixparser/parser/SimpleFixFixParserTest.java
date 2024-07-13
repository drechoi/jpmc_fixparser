package com.example.andre.fixparser.parser;

import com.example.andre.fixparser.api.parser.FixParser;
import com.example.andre.fixparser.api.records.Record;
import com.example.andre.fixparser.demo.simple.SimpleFixParser;

import com.example.andre.fixparser.demo.simple.SimpleKeyValueRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleFixFixParserTest extends FixParserTestBase {
    private final FixParser parser = new SimpleFixParser();

    @Test
    public void testParseNewOrder(){
        Record result = parser.parse(newOrderMessage);

        Assertions.assertInstanceOf(SimpleKeyValueRecord.class, result);

        assertEquals(result.getAttribute(1, String.class), "A8466653547");
        assertEquals(result.getAttribute(54, Integer.class), 1);

        System.out.println(result);
    }


    @Test
    public void testParseCancelOrder(){
        Record result = parser.parse(cancelOrderMessage);

        Assertions.assertInstanceOf(SimpleKeyValueRecord.class, result);

        assertEquals(result.getAttribute(1, String.class), "A8466653547");
        assertEquals(result.getAttribute(54, Integer.class), 1);

        // TODO: create generic assert in the base class
    }



}