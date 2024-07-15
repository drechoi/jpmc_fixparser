package com.example.andre.fixparser.demo.orders;

import com.example.andre.fixparser.FixParserTestBase;
import com.example.andre.fixparser.api.records.ByteBufferRecord;
import com.example.andre.fixparser.demo.orders.fields.OrderFields;
import com.example.andre.fixparser.demo.orders.records.CancelOrderRecord;
import com.example.andre.fixparser.demo.orders.records.NewSingleOrderRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class OrderFixParserTest extends FixParserTestBase {
    private final OrderFixParser parser = new OrderFixParser();

    @Test
    void testParseNewOrder() {
//         * "8=FIX.4.4|
//         * 9=137|
//         * 35=D|
//         * 34=1|
//         * 49=TESTSENDER1|
//         * 52=20240712-22:14:19.508|
//         * 56=TESTTARGET1|
//         * 1=A8466653547|
//         * 11=6490907363867796|
//         * 15=USD|
//         * 38=7000|
//         * 40=1|
//         * 44=12.501|
//         * 54=1|
//         * 55=S207027|
//         * 60=20240712-22:14:19.508|
//         * 10=092|"

        ByteBufferRecord order = parser.parse(newOrderMessage);

        assertNotNull(order);
        Assertions.assertInstanceOf(NewSingleOrderRecord.class, order);

        assertEquals("A8466653547", new String(order.getByteArrayAttribute(OrderFields.Account, new byte[OrderFields.Account.length])).trim());
        assertEquals("6490907363867796", new String(order.getByteArrayAttribute(OrderFields.ClOrdID, new byte[OrderFields.ClOrdID.length])).trim());
        assertEquals( "USD", new String(order.getByteArrayAttribute(OrderFields.Currency, new byte[OrderFields.Currency.length])).trim());
        assertEquals( 7000, order.getIntAttribute(OrderFields.OrderQty));
        assertEquals( "1", new String(order.getByteArrayAttribute(OrderFields.OrdType, new byte[OrderFields.OrdType.length])).trim());
        assertEquals( 12_501_000L, order.getPriceAttribute(OrderFields.Price));
        assertEquals(1, order.getIntAttribute(OrderFields.Side));
        assertEquals( "S207027", new String(order.getByteArrayAttribute(OrderFields.Symbol, new byte[OrderFields.Symbol.length])).trim());
        assertEquals( "20240712-22:14:19.508", new String(order.getByteArrayAttribute(OrderFields.TransactTime, new byte[OrderFields.TransactTime.length])).trim());

        // System.out.println(order);
    }

    @Test
    void testTrimmingLongField() {
        byte[] fixMsg = "8=FIX.4.4|9=137|35=D|55=S98765432198765|".getBytes(StandardCharsets.UTF_8);
        ByteBufferRecord order = parser.parse(fixMsg);

        assertNotNull(order);
        // expect no exception but returning null
        assertEquals( "S9876543", new String(order.getByteArrayAttribute(OrderFields.Symbol, new byte[OrderFields.Symbol.length])).trim());

    }

    @Test
    void testParseCancelOrder() {
//         * 8=FIX.4.4|
//         * 9=137|
//         * 35=F|
//         * 1=A8466653547|
//         * 11=6490907363867797|
//         * 37=C-00000156489-1|
//         * 41=6490907363867796|
//         * 54=1|
//         * 55=S207027|
//         * 60=20240712-22:14:19.508|
//         * 10=123|

        ByteBufferRecord order = parser.parse(cancelOrderMessage);

        assertNotNull(order);
        Assertions.assertInstanceOf(CancelOrderRecord.class, order);

        assertEquals("A8466653547", new String(order.getByteArrayAttribute(OrderFields.Account, new byte[OrderFields.Account.length])).trim());

        assertEquals("6490907363867797", new String(order.getByteArrayAttribute(OrderFields.ClOrdID, new byte[OrderFields.ClOrdID.length])).trim());
        assertEquals("C-00000156489-1", new String(order.getByteArrayAttribute(OrderFields.OrderId, new byte[OrderFields.OrderId.length])).trim());
        assertEquals("6490907363867796", new String(order.getByteArrayAttribute(OrderFields.OrigClOrdID, new byte[OrderFields.OrigClOrdID.length])).trim());

        assertEquals(1, order.getIntAttribute(OrderFields.Side));

        assertEquals( "S207027", new String(order.getByteArrayAttribute(OrderFields.Symbol, new byte[OrderFields.Symbol.length])).trim());
        assertEquals( "20240712-22:14:19.508", new String(order.getByteArrayAttribute(OrderFields.TransactTime, new byte[OrderFields.TransactTime.length])).trim());

        System.out.println(order);
    }

    @Test
    void testOtherMessageType() {
        ByteBufferRecord login = parser.parse(loginMessage);

        // expect no exception but returning null
        assertNull(login);

    }


}