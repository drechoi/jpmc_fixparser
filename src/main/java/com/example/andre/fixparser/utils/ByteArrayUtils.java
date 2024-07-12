package com.example.andre.fixparser.utils;

import java.nio.ByteBuffer;
import java.util.Objects;

public class ByteArrayUtils {



    /**
     * Byte buffer (GC free) version parseInt (modified from Integer.parseInt)
     * otherwise, need to use Integer.parseInt(new String(s)) which will allocate new memory
     *
     * */
    public static int parseInt(ByteBuffer buffer, int beginIndex, int endIndex){
        return parseInt(buffer, beginIndex, endIndex, 10);
    }

    public static int parseInt(ByteBuffer buffer, int beginIndex, int endIndex, int radix)
            throws NumberFormatException {
        buffer = Objects.requireNonNull(buffer);

        if (beginIndex < 0 || beginIndex > endIndex || endIndex > buffer.limit()) {
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
            char firstChar = (char)buffer.get(i);
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
                int digit = Character.digit((char)buffer.get(i), radix);
                if (digit < 0 || result < multmin) {
                    throw new NumberFormatException("Unknown digit " + digit);
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new NumberFormatException("Unknown digit " + digit);
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
