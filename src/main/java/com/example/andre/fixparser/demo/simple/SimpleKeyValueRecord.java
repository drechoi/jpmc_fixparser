package com.example.andre.fixparser.demo.simple;

import com.example.andre.fixparser.api.records.Record;

import java.util.Map;

public class SimpleKeyValueRecord implements Record {
    private final Map<Integer, String> map;

    public SimpleKeyValueRecord(Map<Integer, String> map) {
        this.map = map;
    }

    @Override
    public <T> T getAttribute(int tag, Class<T> clazz){
        if (clazz.equals(Integer.class)) {
            return (T) Integer.decode(map.get(tag));
        }

        return (T) map.get(tag);
    }

    @Override
    public String toString() {
        return "SimpleKeyValueRecord{" +
                "attributes=" + map +
                '}';
    }
}
