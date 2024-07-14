package com.example.andre.fixparser.api.fields;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Record with Fields based on Schema object
 */
public class SchemaImpl implements Schema{
    private final Map<Field, Integer> offsetMap = new HashMap<>();

    protected SchemaImpl(Field[] fields){
        int offset = 0;

        for(Field field : fields){
            offsetMap.put(field, offset);
            offset += field.length;
        }
    }

    @Override
    public int getFieldOffset(Field field){
        return offsetMap.get(field);
    }

    @Override
    public Set<Field> getAllFields(){
        return offsetMap.keySet();
    }
}


