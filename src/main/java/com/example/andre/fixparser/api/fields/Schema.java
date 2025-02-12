package com.example.andre.fixparser.api.fields;

import java.util.Set;

/**
 * Record with Fields based on Schema object
 * provide offset for byte buffer storage
 */
public interface Schema {
    int getFieldOffset(Field field);
    Set<Field> getAllFields();
}
