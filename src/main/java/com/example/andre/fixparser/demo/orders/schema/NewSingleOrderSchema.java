package com.example.andre.fixparser.demo.orders.schema;

import com.example.andre.fixparser.api.fields.Field;
import com.example.andre.fixparser.api.fields.SchemaImpl;
import com.example.andre.fixparser.demo.orders.fields.OrderFields;

public class NewSingleOrderSchema extends SchemaImpl {
    private static NewSingleOrderSchema instance = null;

    public static NewSingleOrderSchema getInstance() {
        if (instance == null)
            instance = new NewSingleOrderSchema();

        return instance;
    }

    static final Field[] FIELDS = {
        OrderFields.Account,
        OrderFields.ClOrdID,
        OrderFields.Currency,
        OrderFields.OrderQty,
        OrderFields.Symbol,
        OrderFields.OrdType,
        OrderFields.Price,
        OrderFields.Side,
        OrderFields.Symbol,
        OrderFields.TransactTime
    };

    private NewSingleOrderSchema() {
        super(FIELDS);
    }
}
