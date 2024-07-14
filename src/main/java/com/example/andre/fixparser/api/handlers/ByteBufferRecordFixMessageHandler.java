package com.example.andre.fixparser.api.handlers;

import com.example.andre.fixparser.api.records.ByteBufferRecord;

import java.util.function.Supplier;

/**
 * Handler for byteBufferRecord
 */
public abstract class ByteBufferRecordFixMessageHandler implements FixMessageHandler<ByteBufferRecord> {
    protected ByteBufferRecord currentRecord = null;
    private final Supplier<ByteBufferRecord> recordSupplier;

    public ByteBufferRecordFixMessageHandler(Supplier<ByteBufferRecord> recordSupplier) {
        this.recordSupplier = recordSupplier;
    }

    @Override
    public void reset() {
        currentRecord = recordSupplier.get();
    }

    @Override
    public ByteBufferRecord getResult() {
        return currentRecord;
    }
}
