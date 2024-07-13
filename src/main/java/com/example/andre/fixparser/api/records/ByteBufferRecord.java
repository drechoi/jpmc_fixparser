package com.example.andre.fixparser.api.records;

import com.example.andre.fixparser.demo.orders.Field;

import java.nio.ByteBuffer;

// TODO: refactor this
public abstract class ByteBufferRecord implements Record {
    final ByteBuffer buffer;

    public ByteBufferRecord(int bufferSize) {
        buffer = ByteBuffer.allocateDirect(bufferSize);
    }


    public void setIntAttribute(Field field, int value) {
        buffer.putInt(getOffset(field), value);
    }

    public int getIntAttribute(Field field) {
        return buffer.getInt(getOffset(field));
    }

    public abstract int getOffset(Field field);
}
