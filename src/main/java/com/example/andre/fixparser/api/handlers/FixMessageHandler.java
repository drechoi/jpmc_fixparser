package com.example.andre.fixparser.api.handlers;

import com.example.andre.fixparser.api.records.Record;

import java.nio.ByteBuffer;

public interface FixMessageHandler<T extends Record> {
    // handler setup
    // will be called before handle any fix tag
    void reset();

    /**
     * logic here
     *
     * @tag: fix tag
     * @buffer,@index,@length: value
     *
     * */
    void handleFixTag(int tag, ByteBuffer buffer, int index, int length);

    // get the result record
    T getResult();
}
