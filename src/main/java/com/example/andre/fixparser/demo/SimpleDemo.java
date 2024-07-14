package com.example.andre.fixparser.demo;

import com.example.andre.fixparser.api.records.Record;
import com.example.andre.fixparser.demo.simple.SimpleFixParser;

import java.nio.charset.StandardCharsets;

public class SimpleDemo {
    public static void main(String[] args){
        SimpleFixParser parser = new SimpleFixParser();

        byte[] fixMsg = "8=FIX.4.4|9=137|35=D|34=1|49=TESTSENDER1|52=20240712-22:14:19.508|56=TESTTARGET1|1=A8466653547|11=6490907363867796|15=USD|38=7000|54=1|55=MSFT|60=20240712-22:14:19.508|10=092|".getBytes(StandardCharsets.UTF_8);
        Record record = parser.parse(fixMsg);
        System.out.println(record);
    }
}
