package com.example.andre.fixparser.api.records;

public interface Record {
    <T> T getAttribute(int tag, Class<T> clazz);
}
