package com.example.andre.fixparser.demo;

import com.example.andre.fixparser.api.records.Record;
import com.example.andre.fixparser.demo.orders.OrderFixParser;

import java.nio.charset.StandardCharsets;

public class OrderDemo {
    public static void main(String[] args){
        OrderFixParser parser = new OrderFixParser();

        final byte[] newOrderMessage = "8=FIX.4.4|9=137|35=D|34=1|49=TESTSENDER1|52=20240712-22:14:19.508|56=TESTTARGET1|1=A8466653547|11=6490907363867796|15=USD|38=7000|40=1|44=12.501|54=1|55=S2070270281|60=20240712-22:14:19.508|10=092|".getBytes(StandardCharsets.UTF_8);

        Record newOrderRecord = parser.parse(newOrderMessage);
        System.out.println(newOrderRecord);

        final byte[] cancelOrderMessage = "8=FIX.4.4|9=137|35=F|1=A8466653547|11=6490907363867797|37=C-00000156489-1|41=6490907363867796|54=1|55=S2070270281|60=20240712-22:14:19.508|10=123|".getBytes(StandardCharsets.UTF_8);

        Record cancelOrderRecord = parser.parse(cancelOrderMessage);
        System.out.println(cancelOrderRecord);
    }
}
