package com.example.andre.fixparser.records;

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

public class NewSingleOrder extends ByteBufferedRecord {
    private static final short BUFFERSIZE = 3000;

    // TODO:
    // will create new Field class outside
    @Deprecated
    public enum Field {
        Account(0, 0, 10),
        ClOrdID,
        Currency,
        Symbol,
        SecurityType,
        OrderQty(0, 100, 4),
        OrdType,
        Price,
        Side,
        TransactTime,
        OrderCapacity;

        public final int dataType;
        public final int offset;
        public final int length;

        Field(){
            this(0,0,0);
        }

        // TODO: check if those int need to optimize
        Field(int dataType, int offset, int length) {
            this.dataType = dataType;
            this.offset = offset;
            this.length = length;
        }
    }


    private NewSingleOrder(){
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
    public static class NewOrderRecordPool{
        private static int currentIndex = 0;
        private static int POOL_SIZE = 1000;

        private static NewSingleOrder[] objectPool = new NewSingleOrder[POOL_SIZE];


        static {
            for (int i=0; i < objectPool.length; i++){
                objectPool[i] = new NewSingleOrder();
            }

        }

        public static NewSingleOrder obtain(){
            return objectPool[currentIndex ++];
        }
    }
}
