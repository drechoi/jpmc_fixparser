package com.example.andre.fixparser.demo.simple;

import java.nio.ByteBuffer;

/**
 * This is just a POC on a repeating group handling. not an actual implementation
 *
 * here is the sample test case
 *    "10001=3|" +
 *      "10002=A|10003=1|" +
 *      "10002=B|10003=999|" +
 *      "10002=C|10003=54321|" +
 * */
public class PocRepeatingGroupHandler extends SimpleKeyValueHandler {
    private String currentFieldName = "";
    private String currentGroupItem = "";

    @Override
    public void handleFixTag(int tag, ByteBuffer buffer, int index, int length) {
        if(isRepeatingGroup(tag)){
            handleRepeatingGroup(tag, new String(buffer.array(), index, length));
        } else {
            super.handleFixTag(tag,buffer,index,length);
        }
    }

    private boolean isRepeatingGroup(int tag){
        return tag == 10001 || tag == 10002 || tag == 10003;
    }

    private void handleRepeatingGroup(int tag, String value){
        switch(tag){
            case 10001:
                // group begin, just ignore
                break;
            case 10002:
                currentFieldName = "GROUP_10001_" + value;
                currentGroupItem = "{" + tag + ": " + value + ", ";
                break;
            case 10003:
                currentGroupItem += tag + ": " + value + "}";
                currentRecord.map.put(currentFieldName, currentGroupItem);
                break;
            default:
                break;
        }
    }
}








