package com.example.andre.fixparser.demo.orders.records;

import com.example.andre.fixparser.api.records.SchemaRecord;
import com.example.andre.fixparser.demo.orders.schema.NewSingleOrderSchema;


public class NewSingleOrderRecord extends SchemaRecord {
    public NewSingleOrderRecord(){
        super(NewSingleOrderSchema.getInstance());
    }
}
