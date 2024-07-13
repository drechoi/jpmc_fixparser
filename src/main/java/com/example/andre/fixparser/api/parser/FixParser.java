package com.example.andre.fixparser.api.parser;

import com.example.andre.fixparser.api.records.ByteBufferRecord;
import com.example.andre.fixparser.api.records.Record;
import com.example.andre.fixparser.api.utils.ByteArrayUtils;
import com.example.andre.fixparser.api.utils.Constants;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FixParser {
    Logger logger = Logger.getLogger("FixParser");

    public static class Configuration{
        public final byte separator;
        public final int temporaryBufferSize;

        public Configuration(byte separator, int temporaryBufferSize) {
            this.separator = separator;
            this.temporaryBufferSize = temporaryBufferSize;
        }
    }

    private static final Configuration DEFAULT_CONFIG = new Configuration((byte)'|', 255);

    // special tags
    private final int MSG_TYPE_TAG = 35;

    private final byte separator;
    private final ByteBuffer parserBuffer;

    private FixMessageHandler currentRecordHandler;


    private final Map<Byte, FixMessageHandler> handlers = new HashMap<>();

    // Dummy handler
    private static final FixMessageHandler IgnoreHandler = new FixMessageHandler(){
        @Override
        public void reset() {}

        @Override
        public void handleFixTag(int tag, ByteBuffer buffer, int index, int length) {}

        @Override
        public Record getRecord() {
            return null;
        }
    };

    public FixParser() {
        this(DEFAULT_CONFIG);
    }

    public FixParser(Configuration config) {
        this.separator = config.separator;
        parserBuffer = ByteBuffer.allocate(config.temporaryBufferSize);
        currentRecordHandler = null;
    }

    public void registerHandler(byte messageType, FixMessageHandler handler){
        handlers.put(messageType, handler);
    }

    private FixMessageHandler getMessageHandler(byte messageType){
        if(handlers.containsKey(messageType)) return handlers.get(messageType);

        if(handlers.containsKey(Constants.WILDCARD_BYTE)) return handlers.get(Constants.WILDCARD_BYTE);

        logger.warning("Wanring: No handler found for message type: " + messageType + ", message will be ignored");

        return IgnoreHandler;
    }

    public final Record parse(byte[] fixMessage) {
        // clear current record
        // handler will be set at tag 35
        currentRecordHandler = null;

        // reset all buffer
        parserBuffer.clear();
        int bufferIndex = 0;
        int currentTag = 0;

        // loop thr the msg
        for(byte b : fixMessage){
            if (b == separator){
                // parserBuffer contain everything between '=' and separator
                handleTag(currentTag, parserBuffer,0, bufferIndex);

                // reset index and buffer
                bufferIndex = 0;
                parserBuffer.clear();
            }else if(b == '='){
                // parserBuffer contain tag value before '='
                currentTag = ByteArrayUtils.parseInt(parserBuffer, 0, bufferIndex,10);

                // reset index and buffer
                bufferIndex = 0;
                parserBuffer.clear();
            } else {
                parserBuffer.put(bufferIndex++,b);
            }
        }

        return currentRecordHandler.getRecord();
    }

    private void handleTag(int tag, ByteBuffer buffer, int index, int length){
        // handle message type tag
        if(isSpecialTag(tag)){
            handlerSpecialTag(tag, buffer, index, length);
            return;
        }

        // handle fix tag
        if(currentRecordHandler != null){
            currentRecordHandler.handleFixTag(tag, buffer, index, length);
        }
    }

    private boolean isSpecialTag(int tag){
        // assume no need to handle tags before 35 (tags like fix version and message length)
        // we could add other handling
        return tag == MSG_TYPE_TAG;
    }

    private void handlerSpecialTag(int tag, ByteBuffer buffer, int index, int length){
        switch(tag){
            case MSG_TYPE_TAG:
                if(length != 1) {
                    throw new RuntimeException("invalid msg type: " + new String(buffer.array(), index, length));
                }

                if(currentRecordHandler != null) {
                    throw new RuntimeException("Invalid state ?!");
                }

                byte msgType = buffer.get(index);
                currentRecordHandler = getMessageHandler(msgType);
                // reset your handler
                currentRecordHandler.reset();
                break;
            default:
                // no other special tags
                break;
        }

    }
}
