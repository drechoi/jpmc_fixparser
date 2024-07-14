package com.example.andre.fixparser.demo.orders.fields;

import com.example.andre.fixparser.api.fields.ByteArrayField;
import com.example.andre.fixparser.api.fields.Field;
import com.example.andre.fixparser.api.fields.IntField;
import com.example.andre.fixparser.api.fields.PriceField;

public class OrderFields {
    public static final Field Account = new ByteArrayField("Account".getBytes(),20);
    public static final Field ClOrdID = new ByteArrayField("ClOrdID".getBytes(), 20);
    public static final Field OrderId = new ByteArrayField("OrderId".getBytes(), 20);
    public static final Field OrigClOrdID = new ByteArrayField("OrigClOrdID".getBytes(), 20);
    public static final Field Currency = new ByteArrayField("Currency".getBytes(), 5);
    public static final Field OrderQty = new IntField("OrderQty".getBytes());
    public static final Field OrdType = new ByteArrayField("OrdType".getBytes(), 1);
    public static final Field Price = new PriceField("Price".getBytes());
    public static final Field Side = new IntField("Side".getBytes());
    public static final Field Symbol = new ByteArrayField("Symbol".getBytes(), 8);
    public static final Field TransactTime = new ByteArrayField("TransactTime".getBytes(), 30);
}

