package com.example.andre.fixparser.parser;

import com.example.andre.fixparser.Record;

public interface FixParser <T extends Record> {
    T parse(byte[] fixMessage);
}
