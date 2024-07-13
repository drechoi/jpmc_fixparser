package com.example.andre.fixparser.demo.orders;

public class Field {
    public final int tag;
    public final DATA_TYPE dataType;

    // TODO: remove this offset, this should be in record
    public final int offset;
    public final int length;

    public Field(int tag, DATA_TYPE dataType, int offset, int length) {
        this.tag = tag;
        this.dataType = dataType;
        this.offset = offset;
        this.length = length;
    }

//* New Order Single (35=D)
//*
//* Account (1) = C123
//* ClOrdID (11) = 314bb362:109f840f9c0
//* Currency (15)
//* OrderQty (38) = 100
//* OrdType (40) = 2
//* Price (44) = 1.10317
//* SendingTime (52)
//* Side (54) = 1
//* Symbol (55) =EUM20
//* TransactTime (60) = 20171102-10:15:40.383
//* CheckSum (10) = …
//*
    public static Field Account = new Field(1, DATA_TYPE.BYTE_ARRAY, 0, 10);
    public static Field ClOrdID = new Field(11, DATA_TYPE.BYTE_ARRAY, 0, 10);
    public static Field Currency = new Field(15, DATA_TYPE.BYTE_ARRAY, 0, 10);

//    public static Field SecurityType = new Field(0, DATA_TYPE.BYTE_ARRAY, 0, 10);
    public static Field OrderQty = new Field(38, DATA_TYPE.INTEGER, 100, 4);
    public static Field OrdType = new Field(40, DATA_TYPE.BYTE_ARRAY, 0, 10);
    public static Field Price = new Field(44, DATA_TYPE.BYTE_ARRAY, 0, 10);
    public static Field Side = new Field(54, DATA_TYPE.BYTE_ARRAY, 0, 10);
    public static Field Symbol = new Field(55, DATA_TYPE.BYTE_ARRAY, 0, 10);
    public static Field TransactTime = new Field(60, DATA_TYPE.BYTE_ARRAY, 0, 10);

    // TODO: this is tmp one, refactor later
    public enum DATA_TYPE {
        INTEGER,
        BYTE_ARRAY,
        PRICE,
    }
}
