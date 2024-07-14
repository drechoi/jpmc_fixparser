package com.example.andre.fixparser.api;

import com.example.andre.fixparser.api.utils.Constants;
import com.example.andre.fixparser.demo.simple.SimpleKeyValueHandler;
import com.example.andre.fixparser.demo.simple.SimpleKeyValueRecord;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class FixParserTest {

    @Test
    public void testParserWithoutHandler(){
        byte[] fixMessage = "35=0|".getBytes(StandardCharsets.UTF_8);

        FixParser<?> parser = new FixParser<>();

        Exception exception = assertThrows(RuntimeException.class, () -> parser.parse(fixMessage));

        assertTrue(exception.getMessage().startsWith("Handler is required."));
    }

    @Test
    public void testParseMessageWithoutTag35(){
        byte[] fixMessage = "8=FIX4.4|9=1|10=0|".getBytes(StandardCharsets.UTF_8);

        FixParser<SimpleKeyValueRecord> parser = new FixParser<>();
        parser.registerHandler(Constants.WILDCARD_BYTE, new SimpleKeyValueHandler());

        SimpleKeyValueRecord result = parser.parse(fixMessage);

        // no exception, but return null
        assertNull(result);
    }
}