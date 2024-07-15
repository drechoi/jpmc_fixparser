package com.example.andre.fixparser.api.records;

import com.example.andre.fixparser.api.fields.Field;
import com.example.andre.fixparser.api.fields.Schema;
import com.example.andre.fixparser.api.utils.Constants;

import java.util.Arrays;

public class SchemaRecord  extends ByteBufferRecord {
    private final Schema schema;

    protected SchemaRecord(Schema schema) {
        this.schema = schema;
    }

    @Override
    public final int getFieldOffset(Field field) {
        return schema.getFieldOffset(field);
    }

    /**
     *
     * only used in unit test or Exception handling. not going to make it GC free
     *
     * @return String representation of the object
     */
    @Override
    public String toString() {
        byte[] tmp = new byte[Constants.MAX_FIELD_SIZE];

        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append("{");

        for (Field field : schema.getAllFields()){
            sb.append(new String(field.fieldName)).append(": ");

            switch (field.dataType){
                case INTEGER:
                    sb.append(getIntAttribute(field));
                    break;
                case PRICE:
                    sb.append(getPriceAttribute(field));
                    break;
                case BYTE_ARRAY:
                    Arrays.fill(tmp, (byte)0);
                    sb.append("\"").append(new String(getByteArrayAttribute(field, tmp)).trim()).append("\"");
                    break;
            }
            sb.append(", ");
        }

        sb.append("}");

        return sb.toString();
    }
}
