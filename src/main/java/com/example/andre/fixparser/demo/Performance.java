package com.example.andre.fixparser.demo;


import com.example.andre.fixparser.api.FixParser;
import com.example.andre.fixparser.demo.orders.OrderFixParser;
import com.example.andre.fixparser.demo.simple.SimpleFixParser;

import java.nio.charset.StandardCharsets;

/**
 * Can run with:
 *  -verbose:gc -XX:+PrintGCDetails
 *
 *  Local result for OrderParser
 *  ----------------- Start running [Order Parser] -----------------
 *  Message parsed: 1000000
 *  Running time: 1087 ms
 *  memory: 0 KB
 *  --------------------------------------
 *
 * */
public class Performance {
    enum Mode {
        COUNT,
        TIMER
    };

    private final Mode mode;
    private final int durationOrLoopCount;

    public Performance(Mode mode, int durationOrLoopCount) {
        this.mode = mode;
        this.durationOrLoopCount = durationOrLoopCount;
    }

    void run(){
        System.out.println("Running with mode: "  + mode + ", arg: " + durationOrLoopCount);
        byte[][] msg = prepareDummyMessage();

        FixParser orderParser = new OrderFixParser();

        FixParser simpleParser = new SimpleFixParser();

        parseMsgs("Order Parser", orderParser, msg, durationOrLoopCount);

        parseMsgs("Simple Parser" , simpleParser, msg, durationOrLoopCount);
    }


    private byte[][] prepareDummyMessage(){
        byte[][] msg = new byte[durationOrLoopCount][];
        for (int i=0; i<durationOrLoopCount; i++) {
            msg[i] = ("8=FIX.4.4|9=137|35=D|34=1|49=TESTSENDER1|52=20240712-22:14:19.508|56=TESTTARGET1|" +
                    "1=A8466653" + i % 1000+ " |11=" + i + "|15=USD|" +
                    "38=" + i % 1000 + "|40=1|44=12.501|54=1|" +
                    "55=S" + i % 1000 + "|60=20240712-22:14:19.508|10=092|").getBytes(StandardCharsets.UTF_8);
        }
        return msg;
    }

    void parseMsgs(String name, FixParser parser, byte[][] msg, int loop){
        int i=0;

        System.out.println("----------------- Start running [" + name + "] ----------------- ");

        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();

        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        while(!meetStopCondition(i, startTime)){
            parser.parse(msg[i % msg.length]);
            i++;
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long endTime = System.currentTimeMillis();

        System.out.println("Message parsed: " + i);
        System.out.println("Running time: " + (endTime - startTime) + " ms");
        System.out.println("memory: " + (memoryAfter - memoryBefore) / 1024 + " KB");
        System.out.println(" -------------------------------------- ");
    }

    boolean meetStopCondition(int iteration, long startTime){
        switch(mode){
            case COUNT:
                return iteration >= durationOrLoopCount;
            case TIMER:
                long now = System.currentTimeMillis();
                return now - startTime >= durationOrLoopCount;
            default:
                return true;
        }
    }

    public static void main(String[] arg){
        // default mode is looping mode, with count = 1_000_000
        Mode mode = Mode.TIMER;

        if(arg.length > 1){
            mode = arg[0] == "0" ? Mode.TIMER : Mode.COUNT;
        }

        // different default value
        int durationOrLoopCount = mode == Mode.COUNT ? 1_000_000 : 10_000;

        if(arg.length > 2){
            durationOrLoopCount = Integer.parseInt(arg[1]);
        }

        Performance app = new Performance(mode, durationOrLoopCount);
        app.run();
    }
}
