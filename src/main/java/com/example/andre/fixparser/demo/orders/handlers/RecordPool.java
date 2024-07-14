package com.example.andre.fixparser.demo.orders.handlers;

import com.example.andre.fixparser.api.records.ByteBufferRecord;
import com.example.andre.fixparser.api.utils.Constants;

import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 *
 * Very simple fix sized object pool.
 * Pre-allocating all memory in the initial phrase.
 *
 * Not a real object pool. No release object mechanism implemented
 * either throw exception or reuse old object when all the object used up.
 *
 * */
public class RecordPool {
    private final Logger logger = Logger.getLogger(RecordPool.class.getSimpleName());
    private int currentIndex = 0;
    private final ByteBufferRecord[] objectPool;

    public RecordPool(int poolSize, Supplier<ByteBufferRecord> supplier) {
        objectPool = new ByteBufferRecord[poolSize];

        for(int i=0; i<poolSize; i++){
            objectPool[i] = supplier.get();
        }
    }

    public ByteBufferRecord obtain(){
        if(currentIndex >= objectPool.length ) {
            if(Constants.OBJECT_POOL_MODE == 1) {
                throw new RuntimeException("object pool out of object");
            } else {
                // reuse old byte buffer.
                currentIndex = 0;
                if(Constants.DEBUG){
                    logger.warning("object pool out of object, overwriting the old objects");
                }
            }
        }

        return objectPool[currentIndex ++];
    }
}
