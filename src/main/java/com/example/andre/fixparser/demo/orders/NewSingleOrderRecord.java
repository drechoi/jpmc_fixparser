package com.example.andre.fixparser.demo.orders;

import com.example.andre.fixparser.api.records.ByteBufferRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * New Order Single (35=D)
 *
 * Account (1)
 * ClOrdID (11)
 * Currency (15)
 * Symbol (55)
 * SecurityType (167)
 * OrderQty (38)
 * OrdType (40)
 * Price (44)
 * Side (54)
 * TransactTime (60)
 * OrderCapacity (528
 * CheckSum (10)
 */

public class NewSingleOrderRecord extends ByteBufferRecord {
    private static final short BUFFERSIZE = 3000;

    private static final Map<Field, Integer> offsets = new HashMap<>();

    static int currentOffset = 0;
    static {
        initOffset(Field.Account);
        initOffset(Field.ClOrdID);
        initOffset(Field.Currency);
        initOffset(Field.Symbol);
        initOffset(Field.OrderQty);
    }

    static void initOffset(Field field){
        offsets.put(field, currentOffset);
        currentOffset += field.length;
    }

    @Override
    public int getOffset(Field field){
        return offsets.get(field);
    }

//
//    // TODO:
//    // will create new Field class outside
//    @Deprecated
//    public enum Field {
//        Account(0, 0, 10),
//        ClOrdID,
//        Currency,
//        Symbol,
//        SecurityType,
//        OrderQty(0, 100, 4),
//        OrdType,
//        Price,
//        Side,
//        TransactTime,
//        OrderCapacity;
//
//        public final int dataType;
//        public final int offset;
//        public final int length;
//
//        Field(){
//            this(0,0,0);
//        }
//
//        // TODO: check if those int need to optimize
//        Field(int dataType, int offset, int length) {
//            this.dataType = dataType;
//            this.offset = offset;
//            this.length = length;
//        }
//    }


    private NewSingleOrderRecord(){
        super(BUFFERSIZE);
    }

    /**
     * TODO: need handle boxing/unboxing
     * */
    @Override
    public <T> T getAttribute(int tag, Class<T> clazz) {

        if(clazz.equals(Integer.class)){
            // TODO: fix boxing and hard coded qty field
            return (T) Integer.valueOf(getIntAttribute(Field.OrderQty));
        }

        return null;
    }




    public byte[] getByteArrayAttribute(Field field) {
        return null;
    }

    public Price getPriceAttribute(Field field) {
        return null;
    }



    public static class Price{

    }



    // TODO: refactor this
    static class SimpleNewOrderRecordPool{
        private static int currentIndex = 0;
        private static int POOL_SIZE = 1000;

        private static NewSingleOrderRecord[] objectPool = new NewSingleOrderRecord[POOL_SIZE];


        static {
            for (int i=0; i < objectPool.length; i++){
                objectPool[i] = new NewSingleOrderRecord();
            }

        }

        public static NewSingleOrderRecord obtain(){
            if(currentIndex > objectPool.length ) {
                throw new RuntimeException("object pool out of object");
            }

            return objectPool[currentIndex ++];
        }
    }
}
