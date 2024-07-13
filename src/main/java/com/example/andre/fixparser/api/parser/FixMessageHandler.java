package com.example.andre.fixparser.api.parser;

import com.example.andre.fixparser.api.records.Record;

import java.nio.ByteBuffer;

public interface FixMessageHandler {
    void reset();

    void handleFixTag(int tag, ByteBuffer buffer, int index, int length);

    Record getRecord();
}
