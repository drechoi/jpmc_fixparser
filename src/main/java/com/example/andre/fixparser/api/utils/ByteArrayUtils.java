package com.example.andre.fixparser.api.utils;

import java.nio.ByteBuffer;

public class ByteArrayUtils {
    /**
     * the implementation is similar to byte[] to int
     * skipped implementation.
     * price is stored as byte[16]
     * */
    public static long parsePriceAsLong(byte[] bytes, int index, int length){
        return parsePriceAsLong(bytes, index, length, Constants.DEFAULT_DECIMAL_PLACES);
    }

    /**
     * default decimal places = 6
     * parsing i.e. (long)price * 1_000_000
     * */
    public static long parsePriceAsLong(byte[] bytes, int beginIndex, int length, int dp)
            throws NumberFormatException {
        // assume always in decimal
        final int radix = 10;

        long result = 0;
        boolean isDecimal = false;
        int decimalPlacesLeft = dp;
        boolean isNegative = false;

        for(int i=beginIndex; i< beginIndex + length; i++){
            if(i == 0){
                if (bytes[i] == '-'){
                    isNegative = true;
                    continue;
                } else if (bytes[i] == '+') {
                    continue;
                }
            }

            if(bytes[i] == '.') {
                if(isDecimal) {
                    throw new NumberFormatException("More than 1 '.'");
                }
                // handle decimal place
                isDecimal = true;
                continue;
            } else if (isDecimal){
                if (decimalPlacesLeft <= 0) {
                    // already handle all deciaml places.
                    // round the number (round down)
                    break;
                }
                decimalPlacesLeft--;
            }

            int digit = Character.digit((char)bytes[i], radix);

            if(digit < 0) {
                throw new NumberFormatException("Invalid number " + new String(bytes, beginIndex, length));
            }

            result *= radix;
            result += digit;
        }

        for (int i=0; i<decimalPlacesLeft; i++){
            // pad 0;
            result *= radix;
        }

        return isNegative ? -result : result;
    }

    public static int parseInt(ByteBuffer buffer, int beginIndex, int length)
            throws NumberFormatException {
        return parseInt(buffer.array(), beginIndex, length);
    }

    /**
     * byte array version of Integer.parseInt
     * otherwise, need to use Integer.parseInt(new String(s)) which will allocate new memory
     * */
    public static int parseInt(byte[] bytes, int beginIndex, int length)
            throws NumberFormatException {
        // assume always in decimal
        final int radix = 10;

        int result = 0;
        boolean isNegative = false;

        for(int i=beginIndex; i< beginIndex + length; i++){
            if(i == 0){
                if (bytes[i] == '-'){
                    isNegative = true;
                    continue;
                } else if (bytes[i] == '+') {
                    continue;
                }
            }

            int digit = Character.digit((char)bytes[i], radix);

            if(digit < 0) {
                throw new NumberFormatException("Invalid number " + new String(bytes, beginIndex, length));
            }

            result *= radix;
            result += digit;
        }

        return isNegative ? -result : result;
    }
}
