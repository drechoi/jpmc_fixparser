package com.example.andre.fixparser.demo.simple;

import com.example.andre.fixparser.api.FixParser;
import com.example.andre.fixparser.api.utils.Constants;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RepeatingGroupHandlingTest {

    @Test
    public void canHandleRepeatingGroup(){
        byte[] fixMessageWithRepeatingGroup = ("35=0|1001=A|1002=99999|" +
                // the repeating group with 3 items
                "10001=3|" +
                "10002=A|10003=1|" +
                "10002=B|10003=999|" +
                "10002=C|10003=54321|" +
                "9990=A|9991=B|").getBytes(StandardCharsets.UTF_8);

        FixParser<SimpleKeyValueRecord> parser = new FixParser<>();
        parser.registerHandler(Constants.WILDCARD_BYTE, new PocRepeatingGroupHandler());

        SimpleKeyValueRecord record = parser.parse(fixMessageWithRepeatingGroup);

        assertNotNull(record);
        String fieldA = "GROUP_10001_A";
        String fieldB = "GROUP_10001_B";
        String fieldC = "GROUP_10001_C";

        // getting repeating group values
        assertEquals("{10002: A, 10003: 1}", record.getAttribute(fieldA));
        assertEquals("{10002: B, 10003: 999}", record.getAttribute(fieldB));
        assertEquals("{10002: C, 10003: 54321}", record.getAttribute(fieldC));

        // make sure all field also parsed correctly
        assertEquals("A", record.getAttribute("1001"));
        assertEquals("99999", record.getAttribute("1002"));
        assertEquals("A", record.getAttribute("9990"));
        assertEquals("B", record.getAttribute("9991"));

        System.out.println(record);
    }
}
