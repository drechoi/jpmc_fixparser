package com.example.andre.fixparser.api.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class ByteArrayUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "1024", "-3", "000125", "-000321", "2147483647", "-2147483648"})
    void testParseByteArrayToInt(String input){
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        int actualResult = ByteArrayUtils.parseInt(bytes, 0, bytes.length);

        System.out.println("result: " + actualResult);
        assertEquals(Integer.parseInt(input), actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "1024", "-3", "000125.012", "-000321", "002147483647.00001", "-2147483648.0123456"})
    void testParsePriceToLong(String input){
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        long actualResult = ByteArrayUtils.parsePriceAsLong(bytes, 0, bytes.length, 8);

        long expectedResult = new BigDecimal(input).multiply(new BigDecimal("100000000")).longValue();
        System.out.println("result: " + actualResult);
        assertEquals(expectedResult, actualResult);
    }
}