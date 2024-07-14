package com.example.andre.fixparser.api.handlers;

import com.example.andre.fixparser.api.fields.Field;
import com.example.andre.fixparser.api.records.ByteBufferRecord;

import java.util.function.Supplier;

public class FieldMapperHandler extends ByteBufferRecordFixMessageHandler {
    private final FieldMapper fieldMapper;

    public FieldMapperHandler(FieldMapper fieldMapper, Supplier<ByteBufferRecord> recordSupplier) {
        super(recordSupplier);
        this.fieldMapper = fieldMapper;
    }

    @Override
    protected Field getFieldByTag(int tag){
        return fieldMapper.getField(tag);
    }
}
