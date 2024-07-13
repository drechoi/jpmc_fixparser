package com.example.andre.fixparser.demo.orders;

import com.example.andre.fixparser.api.records.ByteBufferRecord;

import java.util.HashMap;
import java.util.Map;

public class NewSingleOrderHandler extends OrderHandler {

    static final Map<Integer, Field> fieldMap = new HashMap();

    static {
        fieldMap.put(Field.OrderQty.tag, Field.OrderQty);
    }

//
//    @Override
//    public void reset() {
//        // TODO: Implement this
//    }
//
//    @Override
//    public void handleFixTag(int tag, ByteBuffer buffer, int index, int length) {
//        // TODO: Implement this
//
//    }
//
//    @Override
//    public Record getRecord() {
//        // TODO: Implement this
//        return null;
//    }

    @Override
    ByteBufferRecord obtainNewRecord() {
        return NewSingleOrderRecord.SimpleNewOrderRecordPool.obtain();
    }

    @Override
    Field getFieldByTag(int tag) {
        return fieldMap.get(tag);
    }
}
