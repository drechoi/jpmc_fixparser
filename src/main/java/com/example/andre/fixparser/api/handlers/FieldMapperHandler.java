package com.example.andre.fixparser.api.handlers;

import com.example.andre.fixparser.api.fields.Field;
import com.example.andre.fixparser.api.records.ByteBufferRecord;
import com.example.andre.fixparser.api.utils.Constants;

import java.nio.ByteBuffer;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 *
 * Simply using a fix tag and find the corresponding field
 * set the value to the byte buffer result
 *
 * */
public class FieldMapperHandler extends ByteBufferRecordFixMessageHandler {
    private final FieldMapper fieldMapper;
    private final Logger logger = Logger.getLogger(FieldMapperHandler.class.getName());

    public FieldMapperHandler(FieldMapper fieldMapper, Supplier<ByteBufferRecord> recordSupplier) {
        super(recordSupplier);
        this.fieldMapper = fieldMapper;
    }

    @Override
    public void handleFixTag(int tag, ByteBuffer buffer, int index, int length) {
        if (currentRecord == null){
            throw new RuntimeException("Failed to handle fix tag");
        }

        Field field = fieldMapper.getField(tag);

        if(field == null){
            if(Constants.DEBUG) logger.info("[DEBUG] Unhandled tag: " + tag);
            return;
        }

        currentRecord.setAttribute(field, buffer.array(), index, length);
    }
}
