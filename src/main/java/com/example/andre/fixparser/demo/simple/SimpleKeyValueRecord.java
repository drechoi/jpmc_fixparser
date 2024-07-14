package com.example.andre.fixparser.demo.simple;

import com.example.andre.fixparser.api.records.Record;

import java.util.HashMap;
import java.util.Map;

public class SimpleKeyValueRecord implements Record {
    final Map<String, String> map;

    public SimpleKeyValueRecord() {
        this.map = new HashMap<>();
    }

    public String getAttribute(int tag){
        return map.get(String.valueOf(tag));
    }
    
    public String getAttribute(String tag){
        return map.get(tag);
    }

    @Override
    public String toString() {
        return "SimpleKeyValueRecord{" +
                "attributes=" + map +
                '}';
    }
}
