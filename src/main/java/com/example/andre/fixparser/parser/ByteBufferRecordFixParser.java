package com.example.andre.fixparser.parser;

import com.example.andre.fixparser.Record;
import com.example.andre.fixparser.records.ByteBufferedRecord;
import com.example.andre.fixparser.records.NewSingleOrder;
import com.example.andre.fixparser.utils.ByteArrayUtils;
import com.example.andre.fixparser.utils.Constants;

import java.nio.ByteBuffer;
import java.util.Properties;

public class ByteBufferRecordFixParser implements FixParser<ByteBufferedRecord> {

    // TODO: implement this
    public FixParser<ByteBufferedRecord> createParser(Properties config){

        return null;
    }

    // TODO: seaparator should be configurable
    byte separator = '|';

    private final ByteBuffer parserBuffer = ByteBuffer.allocate(50);

    @Override
    public ByteBufferedRecord parse(byte[] fixMessage) {

        NewSingleOrder newSingleOrder = NewSingleOrder.NewOrderRecordPool.obtain();

        parserBuffer.clear();
        int index = 0;
        int currentTag = 0;

        // loop thr the msg
        for(byte b : fixMessage){
            if (b == separator){
                // parserBuffer contain everything between '=' and separator
//                parserBuffer.get(value,0, index);

                handleTag(newSingleOrder, currentTag, parserBuffer,0, index);

                index = 0;
                parserBuffer.clear();
            }else if(b == '='){
                // parserBuffer contain tag value before '='
                currentTag = ByteArrayUtils.parseInt(parserBuffer, 0, index,10);

                parserBuffer.clear();
                index = 0;
            } else {
                parserBuffer.put(index++,b);
            }
        }

        return newSingleOrder;
    }

    private void handleTag(ByteBufferedRecord record, int tag, ByteBuffer buffer, int index, int length){
//        System.out.println("Parser key : " + tag );
//        byte[] tmp = new byte[100];
//        buffer.get(tmp, index,length);
//        System.out.println("Parser value : " + new String(tmp).trim() );


        NewSingleOrder.Field field = getFieldByTag(tag);

        if(field != null){
            switch (field.dataType){
                case Constants.DataType.INTEGER:
                    record.setIntAttribute(getFieldByTag(tag), ByteArrayUtils.parseInt(buffer, index, length));
                    break;
            }
        }




    }

    NewSingleOrder.Field getFieldByTag(int tag){
        // TODO: maybe do dictionary
        switch(tag){
            case 38:
                return NewSingleOrder.Field.OrderQty;
            default:
                return null;
        }
    }

}
