package com.example.andre.fixparser.api.fields;

public abstract class Field {
    public final byte[] fieldName;
    public final DataType dataType;
    public final int length;

    public Field(byte[] fieldName, DataType dataType, int length) {
        this.fieldName = fieldName;
        this.dataType = dataType;
        this.length = length;
    }
}
