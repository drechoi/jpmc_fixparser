package com.example.andre.fixparser.demo.orders.handlers;

import com.example.andre.fixparser.api.handlers.FieldMapperHandler;
import com.example.andre.fixparser.api.handlers.FieldMapperImpl;
import com.example.andre.fixparser.demo.orders.fields.OrderFields;
import com.example.andre.fixparser.demo.orders.records.CancelOrderRecord;

public class CancelOrderHandler extends FieldMapperHandler {
    private static final RecordPool cancelOrderRecordObjectPool = new RecordPool(1000, CancelOrderRecord::new);

    private static final FieldMapperImpl fieldMapper = new FieldMapperImpl();

    static{
        fieldMapper.addTaggedField(1, OrderFields.Account);
        fieldMapper.addTaggedField(11, OrderFields.ClOrdID);
        fieldMapper.addTaggedField(15, OrderFields.Currency);
        fieldMapper.addTaggedField(37, OrderFields.OrderId);
        fieldMapper.addTaggedField(38, OrderFields.OrderQty);
        fieldMapper.addTaggedField(40, OrderFields.OrdType);
        fieldMapper.addTaggedField(41, OrderFields.OrigClOrdID);
        fieldMapper.addTaggedField(44, OrderFields.Price);
        fieldMapper.addTaggedField(54, OrderFields.Side);
        fieldMapper.addTaggedField(55, OrderFields.Symbol);
        fieldMapper.addTaggedField(60, OrderFields.TransactTime);
    }

    public CancelOrderHandler() {
        super(fieldMapper, cancelOrderRecordObjectPool::obtain);
    }
}
