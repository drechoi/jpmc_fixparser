package com.example.andre.fixparser.parser;

import com.example.andre.fixparser.Record;
import com.example.andre.fixparser.records.ByteBufferedRecord;
import com.example.andre.fixparser.records.NewSingleOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteBufferRecordFixParserTest extends FixParserTestBase{

    FixParser<ByteBufferedRecord> parser = new ByteBufferRecordFixParser();

    @Test
    void testParseNewOrder() {
        ByteBufferedRecord newSingleOrder = parser.parse(newOrderMessage);


        Assertions.assertInstanceOf(ByteBufferedRecord.class, newSingleOrder);

//        assertEquals(newSingleOrder.getAttribute(1, String.class), "DUMMY_ACC");

        assertEquals(newSingleOrder.getIntAttribute(NewSingleOrder.Field.OrderQty), 7000);


        // TODO: create generic assert in the base class

    }
}
