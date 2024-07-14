package com.example.andre.fixparser.api.handlers;

import com.example.andre.fixparser.api.fields.Field;
import com.example.andre.fixparser.api.records.ByteBufferRecord;
import com.example.andre.fixparser.api.utils.Constants;

import java.nio.ByteBuffer;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Handler for byteBufferRecord
 */
public abstract class ByteBufferRecordFixMessageHandler implements FixMessageHandler<ByteBufferRecord> {
    private final Logger logger = Logger.getLogger(ByteBufferRecordFixMessageHandler.class.getName());
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
    public void handleFixTag(int tag, ByteBuffer buffer, int index, int length) {
        if (currentRecord == null){
            throw new RuntimeException("Failed to handle fix tag");
        }

        Field field = getFieldByTag(tag);

        if(field == null){
            if(Constants.DEBUG) logger.info("[DEBUG] Unhandled tag: " + tag);
            return;
        }

        currentRecord.setAttribute(field, buffer.array(), index, length);
    }

    protected abstract Field getFieldByTag(int tag);

    @Override
    public ByteBufferRecord getResult() {
        return currentRecord;
    }
}
