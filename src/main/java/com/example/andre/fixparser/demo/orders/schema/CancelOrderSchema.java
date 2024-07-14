package com.example.andre.fixparser.demo.orders.schema;

import com.example.andre.fixparser.api.fields.Field;
import com.example.andre.fixparser.api.fields.SchemaImpl;
import com.example.andre.fixparser.demo.orders.fields.OrderFields;

public class CancelOrderSchema extends SchemaImpl {
    private static CancelOrderSchema instance = null;

    public static CancelOrderSchema getInstance() {
        if (instance == null)
            instance = new CancelOrderSchema();

        return instance;
    }

    static final Field[] FIELDS = {
        OrderFields.Account,
        OrderFields.ClOrdID,
        OrderFields.OrderId,
        OrderFields.OrigClOrdID,
        OrderFields.Side,
        OrderFields.Symbol,
        OrderFields.TransactTime,
        OrderFields.Currency,
        OrderFields.OrderQty,
        OrderFields.OrdType,
        OrderFields.Price,
    };

    private CancelOrderSchema() {
        super(FIELDS);
    }
}