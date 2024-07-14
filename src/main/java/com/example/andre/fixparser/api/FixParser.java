package com.example.andre.fixparser.api;

import com.example.andre.fixparser.api.handlers.FixMessageHandler;
import com.example.andre.fixparser.api.records.Record;
import com.example.andre.fixparser.api.utils.ByteArrayUtils;
import com.example.andre.fixparser.api.utils.Constants;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Fix parser
 *
 * Usage:
 *  create a parser, register the handler
 *  call parse
 *  get the result from the handler
 *
 */
public class FixParser<T extends Record> {
    // special tags, msg Type
    private static final int SPECIAL_TAG_BEGIN_STRING = 8;
    private static final int SPECIAL_TAG_BODY_LENGTH = 9;
    private static final int SPECIAL_TAG_CHECKSUM = 10;
    private static final int SPECIAL_TAG_MSG_TYPE = 35;

    public static class Configuration{
        public final byte separator;
        public final int temporaryBufferSize;

        public Configuration(byte separator, int temporaryBufferSize) {
            this.separator = separator;
            this.temporaryBufferSize = temporaryBufferSize;
        }
    }

    private static final Configuration DEFAULT_CONFIG = new Configuration((byte)'|', Constants.MAX_FIELD_SIZE);

    private final Logger logger = Logger.getLogger(FixParser.class.getName());

    private final byte separator;
    private final ByteBuffer parserBuffer;

    private FixMessageHandler<T> currentRecordHandler;

    private final Map<Byte, FixMessageHandler<T>> registeredHandlers = new HashMap<>();

    public FixParser() {
        this(DEFAULT_CONFIG);
    }

    public FixParser(Configuration config) {
        this.separator = config.separator;
        parserBuffer = ByteBuffer.allocate(config.temporaryBufferSize);
        currentRecordHandler = null;
    }

    public void registerHandler(byte messageType, FixMessageHandler<T> handler){
        registeredHandlers.put(messageType, handler);
    }

    private FixMessageHandler<T> getMessageHandler(byte messageType){
        if(registeredHandlers.containsKey(messageType)) return registeredHandlers.get(messageType);

        if(registeredHandlers.containsKey(Constants.WILDCARD_BYTE)) return registeredHandlers.get(Constants.WILDCARD_BYTE);

        logger.warning("No handler found for message type: " + messageType + ", message will be ignored");

        return null;
    }

    public final T parse(byte[] fixMessage) {
        // clear current record
        // handler will be set at tag 35
        currentRecordHandler = null;

        if(registeredHandlers.isEmpty()){
            throw new RuntimeException("Handler is required. please call registerHandler(handler).");
        }

        if(fixMessage.length >  Constants.MAX_FIX_MSG_LENGTH){
            throw new RuntimeException("fix too long. Try increase Constants.MAX_FIX_MSG_LENGTH");
        }

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

        if(currentRecordHandler == null){
            logger.warning("failed to parse fix message. It may caused by missing msg type or no valid handler for this message type: " + new String(fixMessage));
            return null;
        }

        return currentRecordHandler.getResult();
    }

    private void handleTag(int tag, ByteBuffer buffer, int index, int length){
        if(isSpecialTag(tag)){
            // handle special tags
            // 8: begin string, 9: body length, 10: checksum, 35: msg typ
            handlerSpecialTag(tag, buffer, index, length);
        } else {
            // handle other tags
            handlerNormalTag(tag, buffer, index, length);
        }
    }

    private boolean isSpecialTag(int tag){
        return tag == SPECIAL_TAG_MSG_TYPE || tag == SPECIAL_TAG_BEGIN_STRING || tag == SPECIAL_TAG_BODY_LENGTH || tag == SPECIAL_TAG_CHECKSUM;
    }

    private void handlerSpecialTag(int tag, ByteBuffer buffer, int index, int length){
        switch(tag){
            case SPECIAL_TAG_MSG_TYPE:
                if(length != 1) {
                    throw new RuntimeException("invalid msg type: " + new String(buffer.array(), index, length));
                }

                if(currentRecordHandler != null) {
                    throw new RuntimeException("Invalid state ?!");
                }

                byte msgType = buffer.get(index);
                currentRecordHandler = getMessageHandler(msgType);

                // reset the handler
                if(currentRecordHandler != null){
                    currentRecordHandler.reset();
                }
                break;
            case SPECIAL_TAG_BEGIN_STRING:
            case SPECIAL_TAG_BODY_LENGTH:
            case SPECIAL_TAG_CHECKSUM:
            default:
                // no special handle
                // may add verification
                break;
        }
    }

    private void handlerNormalTag(int tag, ByteBuffer buffer, int index, int length){
        if(currentRecordHandler != null){
            currentRecordHandler.handleFixTag(tag, buffer, index, length);
        }
    }
}
