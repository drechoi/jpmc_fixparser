package com.example.andre.fixparser.demo.orders.records;

import com.example.andre.fixparser.api.records.SchemaRecord;
import com.example.andre.fixparser.demo.orders.schema.CancelOrderSchema;

public class CancelOrderRecord extends SchemaRecord {
    public CancelOrderRecord() {
        super(CancelOrderSchema.getInstance());
    }
}
