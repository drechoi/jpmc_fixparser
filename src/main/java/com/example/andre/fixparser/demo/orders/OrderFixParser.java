package com.example.andre.fixparser.demo.orders;

import com.example.andre.fixparser.api.FixParser;
import com.example.andre.fixparser.api.records.ByteBufferRecord;
import com.example.andre.fixparser.demo.orders.handlers.CancelOrderHandler;
import com.example.andre.fixparser.demo.orders.handlers.NewSingleOrderHandler;

public class OrderFixParser extends FixParser<ByteBufferRecord> {
    public OrderFixParser() {
        super();

        registerHandler((byte) 'D', new NewSingleOrderHandler());
        registerHandler((byte) 'F', new CancelOrderHandler());
    }
}
