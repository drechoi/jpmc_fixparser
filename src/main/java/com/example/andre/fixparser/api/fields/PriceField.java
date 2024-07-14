package com.example.andre.fixparser.api.fields;

/**
 * It was planning to convert the price (number with decimal) to a long value
 * as the conversion is similar to byte[] to int. implementation is skipped.
 * now Price is stored as String (16 digit byte array)
 * */
public class PriceField extends Field{
    public PriceField(byte[] name) {
        super(name, DataType.PRICE, 16);
    }
}


