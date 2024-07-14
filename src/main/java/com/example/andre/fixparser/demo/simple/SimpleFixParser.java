package com.example.andre.fixparser.demo.simple;

import com.example.andre.fixparser.api.FixParser;
import com.example.andre.fixparser.api.utils.Constants;

/**
 * Simple implementation, for structure / interface check
 *
 * no performance tuning
 * no repeating group handling
 *
 */
public class SimpleFixParser extends FixParser<SimpleKeyValueRecord> {
    public SimpleFixParser() {
        super();

        this.registerHandler(Constants.WILDCARD_BYTE, new SimpleKeyValueHandler());
    }
}
