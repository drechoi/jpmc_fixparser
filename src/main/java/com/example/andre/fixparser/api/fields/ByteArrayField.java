package com.example.andre.fixparser.api.fields;

public class ByteArrayField extends Field{
    public ByteArrayField(byte[] name, int length) {
        super(name, DataType.BYTE_ARRAY, length);
    }
}
