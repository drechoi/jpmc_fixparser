package com.example.andre.fixparser.api.handlers;

import com.example.andre.fixparser.api.fields.Field;

import java.util.HashMap;
import java.util.Map;

public class FieldMapperImpl implements FieldMapper {
    private final Map<Integer, Field> tag2FieldMap = new HashMap<>();

    public void addTaggedField(int tag, Field field){
        tag2FieldMap.put(tag, field);
    }

    @Override
    public Field getField(int fixTag) {
        return tag2FieldMap.get(fixTag);
    }
}
