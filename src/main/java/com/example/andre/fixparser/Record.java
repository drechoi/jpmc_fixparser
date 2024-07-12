package com.example.andre.fixparser;

public interface Record {
    <T> T getAttribute(int tag, Class<T> clazz);
}
