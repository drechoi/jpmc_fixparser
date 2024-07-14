package com.example.andre.fixparser.demo.simple;

import com.example.andre.fixparser.api.handlers.FixMessageHandler;

import java.nio.ByteBuffer;

public class SimpleKeyValueHandler implements FixMessageHandler<SimpleKeyValueRecord> {
    protected SimpleKeyValueRecord currentRecord = null;

    @Override
    public void reset(){
        currentRecord = new SimpleKeyValueRecord();
    }

    @Override
    public void handleFixTag(int tag, ByteBuffer buffer, int index, int length) {
        currentRecord.map.put(String.valueOf(tag), new String(buffer.array(), index, length));
    }

    @Override
    public SimpleKeyValueRecord getResult(){
        return currentRecord;
    }
}
