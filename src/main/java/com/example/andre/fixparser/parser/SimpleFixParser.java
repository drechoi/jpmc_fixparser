package com.example.andre.fixparser.parser;

import com.example.andre.fixparser.Record;
import com.example.andre.fixparser.records.SimpleKeyValueRecord;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Simple implementation, for structure / interface check
 *
 * no performance tuning
 * no repeating group handling
 *
 */
public class SimpleFixParser implements FixParser<SimpleKeyValueRecord> {
    @Override
    public SimpleKeyValueRecord parse(byte[] fixMessage) {
        Map<Integer, String> parsedMap = new HashMap<>();


        String message = new String(fixMessage);
        String[] split;

        for(String tagPairs : message.split("\\|")){
            if (tagPairs.contains("=") ){
                split = tagPairs.split("=");

                parsedMap.put(Integer.parseInt(split[0]), split[1]);
            }
        }

        return new SimpleKeyValueRecord(parsedMap);
    }
}
