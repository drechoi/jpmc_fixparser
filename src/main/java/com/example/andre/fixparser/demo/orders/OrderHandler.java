package com.example.andre.fixparser.demo.orders;

import com.example.andre.fixparser.api.parser.FixMessageHandler;
import com.example.andre.fixparser.api.records.ByteBufferRecord;
import com.example.andre.fixparser.api.records.Record;
import com.example.andre.fixparser.api.utils.ByteArrayUtils;
import com.example.andre.fixparser.demo.orders.Field;

import java.nio.ByteBuffer;
import java.util.logging.Logger;

public abstract class OrderHandler implements FixMessageHandler {
    Logger logger = Logger.getLogger("OrderHandler");
    ByteBufferRecord currentRecord = null;

    @Override
    public void reset() {
        currentRecord = obtainNewRecord();
    }

    @Override
    public void handleFixTag(int tag, ByteBuffer buffer, int index, int length) {

        Field field = getFieldByTag(tag);

        if(field == null){
            logger.warning("Unknown Field with tag: " + tag);
            return;
        }
        // TODO: Implement this
        switch (field.dataType){
            case INTEGER:
                currentRecord.setIntAttribute(getFieldByTag(tag), ByteArrayUtils.parseInt(buffer, index, length));
                break;
        }

    }

    @Override
    public Record getRecord() {
        return currentRecord;
    }

    abstract ByteBufferRecord obtainNewRecord();
    abstract Field getFieldByTag(int tag);
}
