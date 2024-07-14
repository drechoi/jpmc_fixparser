package com.example.andre.fixparser.demo.orders.handlers;

import com.example.andre.fixparser.api.records.ByteBufferRecord;

import java.util.function.Supplier;

/**
 *
 * Simple fix sized object pool.
 * No release object mechanism implemented
 *
 * */
public class RecordPool {
    private int currentIndex = 0;
    private final ByteBufferRecord[] objectPool;

    public RecordPool(int poolSize, Supplier<ByteBufferRecord> supplier) {
        objectPool = new ByteBufferRecord[poolSize];

        for(int i=0; i<poolSize; i++){
            objectPool[i] = supplier.get();
        }
    }

    public ByteBufferRecord obtain(){
        if(currentIndex > objectPool.length ) {
            throw new RuntimeException("object pool out of object");
        }
        return objectPool[currentIndex ++];
    }
}
