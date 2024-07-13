package com.example.andre.fixparser.demo.simple;

import com.example.andre.fixparser.api.parser.FixMessageHandler;
import com.example.andre.fixparser.api.records.Record;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class SimpleKeyValueHandler implements FixMessageHandler {
    private Map<Integer, String> currentParsedMap = null;

    @Override
    public void reset(){
        currentParsedMap = new HashMap<>();
    }

    @Override
    public void handleFixTag(int tag, ByteBuffer buffer, int index, int length) {
        currentParsedMap.put(tag, new String(buffer.array(), index, length));
    }

    @Override
    public Record getRecord(){
        return new SimpleKeyValueRecord(currentParsedMap);
    }
}
