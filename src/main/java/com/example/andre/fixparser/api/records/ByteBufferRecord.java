package com.example.andre.fixparser.api.records;

import com.example.andre.fixparser.api.fields.Field;
import com.example.andre.fixparser.api.utils.ByteArrayUtils;
import com.example.andre.fixparser.api.utils.Constants;

import java.nio.ByteBuffer;
import java.util.logging.Logger;

import static java.lang.Math.min;


/**
 * Record stored in byteBuffer
 * */
public abstract class ByteBufferRecord implements Record {
    private final Logger logger = Logger.getLogger(ByteBufferRecord.class.getSimpleName());
    private final ByteBuffer buffer;

    // for demo reason. this buffer size is not configurable
    protected ByteBufferRecord() {
        buffer = ByteBuffer.allocateDirect(Constants.MAX_FIX_MSG_LENGTH);
    }

    public final void setAttribute(Field field, byte[] value, int index, int length) {
        switch(field.dataType){
            case INTEGER:
                buffer.putInt(getFieldOffset(field), ByteArrayUtils.parseInt(value, index, length));
                break;
            case PRICE:
            case BYTE_ARRAY:
                if(length > field.length){
                    logger.warning("Content too long, is trimming" + new String(value, index, length));
                }

                for(int i=0;i< min(length,field.length); i++){
                    buffer.put(getFieldOffset(field) + i, value[index+i]);
                }
                break;
            default:
                break;
        }
    }

    public final int getIntAttribute(Field field) {
        return buffer.getInt(getFieldOffset(field));
    }

    public final byte[] getByteArrayAttribute(Field field, byte[] bytes) {
        for(int i=0;i<field.length; i++){
            bytes[i] = buffer.get(getFieldOffset(field) + i);
        }
        return bytes;
    }

    /**
     * Price as long is not supported.
     * please use getByteArrayAttribute
     * */
    public final long getPriceAttribute(Field field) {
        throw new UnsupportedOperationException("Price is not really supported");
//        return buffer.getLong(getFieldOffset(field));
    }

    public abstract int getFieldOffset(Field field);
}
