package com.example.andre.fixparser.api.handlers;

import com.example.andre.fixparser.api.fields.Field;

/**
 *
 * kind of Fix message dictionary,
 * mapping fix tags to result object fields
 *
 */
public interface FieldMapper {
    Field getField(int fixTag);
}
