package com.example.andre.fixparser.api.handlers;

import com.example.andre.fixparser.api.fields.Field;

/**
 * Fix tag to field Mapper
 */
public interface FieldMapper {
    Field getField(int fixTag);
}
