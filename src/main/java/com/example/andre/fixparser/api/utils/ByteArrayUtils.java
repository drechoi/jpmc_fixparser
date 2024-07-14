package com.example.andre.fixparser.api.utils;

import java.nio.ByteBuffer;

public class ByteArrayUtils {
    /**
     * the implementation is similar to byte[] to int
     * skipped implementation.
     * price is stored as byte[16]
     * */
    public static long parsePriceAsLong(byte[] bytes, int index, int length){
        throw new UnsupportedOperationException("Decimal to long is not implemented");
    }

    public static int parseInt(ByteBuffer buffer, int beginIndex, int endIndex){
        return parseInt(buffer, beginIndex, endIndex, 10);
    }

    public static int parseInt(ByteBuffer buffer, int beginIndex, int endIndex, int radix)
            throws NumberFormatException {
        return parseInt(buffer.array(), beginIndex, endIndex, radix);

    }

    public static int parseInt(byte[] bytes, int beginIndex, int endIndex)
            throws NumberFormatException {
        return parseInt(bytes, beginIndex, endIndex, 10);
    }


    /**
     * Byte buffer (GC free) version parseInt (modified from Integer.parseInt)
     * otherwise, need to use Integer.parseInt(new String(s)) which will allocate new memory
     * */
    public static int parseInt(byte[] bytes, int beginIndex, int endIndex, int radix)
            throws NumberFormatException {
//        buffer = Objects.requireNonNull(buffer);

        if (beginIndex < 0 || beginIndex > endIndex || endIndex > bytes.length) {
            throw new IndexOutOfBoundsException();
        }
        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " less than Character.MIN_RADIX");
        }
        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " greater than Character.MAX_RADIX");
        }

        boolean negative = false;
        int i = beginIndex;
        int limit = -Integer.MAX_VALUE;

        if (i < endIndex) {
            char firstChar = (char) bytes[i];
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+') {
                    throw new NumberFormatException("firstChar < '0' but not +/-");
                }
                i++;
                if (i == endIndex) { // Cannot have lone "+" or "-"
                    throw new NumberFormatException("Cannot have lone + or -");
                }
            }
            int multmin = limit / radix;
            int result = 0;
            while (i < endIndex) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                int digit = Character.digit((char)bytes[i], radix);
                if (digit < 0 || result < multmin) {
                    throw new NumberFormatException("failed to parse int ");
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new NumberFormatException("Unknown digit ");
                }
                i++;
                result -= digit;
            }
            return negative ? result : -result;
        } else {
            throw new NumberFormatException("");
        }
    }
}
