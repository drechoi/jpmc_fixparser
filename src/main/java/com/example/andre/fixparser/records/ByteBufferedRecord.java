package com.example.andre.fixparser.records;

import com.example.andre.fixparser.Record;

import java.nio.ByteBuffer;

public abstract class ByteBufferedRecord implements Record {
    final ByteBuffer buffer;

    public ByteBufferedRecord(int bufferSize) {
        buffer = ByteBuffer.allocateDirect(bufferSize);
    }


    public void setIntAttribute(NewSingleOrder.Field field, int value) {
        buffer.putInt(field.offset, value);
    }

    public int getIntAttribute(NewSingleOrder.Field field) {
        return buffer.getInt(field.offset);
    }
}
