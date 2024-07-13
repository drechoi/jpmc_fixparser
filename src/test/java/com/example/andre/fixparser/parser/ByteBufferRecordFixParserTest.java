package com.example.andre.fixparser.parser;

import com.example.andre.fixparser.api.parser.FixParser;
import com.example.andre.fixparser.api.records.ByteBufferRecord;
import com.example.andre.fixparser.demo.orders.Field;
import com.example.andre.fixparser.demo.orders.OrderFixParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO: rename this like OrderFixParserTest
class ByteBufferRecordFixParserTest extends FixParserTestBase {
    FixParser parser = new OrderFixParser();

    @Test
    void testParseNewOrder() {
        ByteBufferRecord newSingleOrder = (ByteBufferRecord) parser.parse(newOrderMessage);


        Assertions.assertInstanceOf(ByteBufferRecord.class, newSingleOrder);

//        assertEquals(newSingleOrder.getAttribute(1, String.class), "DUMMY_ACC");

        assertEquals(newSingleOrder.getIntAttribute(Field.OrderQty), 7000);


        // TODO: create generic assert in the base class
    }


    @Test
    void testParseCancelOrder() {


    }

    @Test
    void testOtherMessageType() {

    }
}