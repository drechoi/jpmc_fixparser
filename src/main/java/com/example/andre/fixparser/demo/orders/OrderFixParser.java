package com.example.andre.fixparser.demo.orders;

import com.example.andre.fixparser.api.parser.FixParser;

public class OrderFixParser extends FixParser {
    public OrderFixParser() {
        super();

        registerHandler((byte) 'D', new NewSingleOrderHandler());
        registerHandler((byte) 'F', new NewSingleOrderHandler());
    }
}
