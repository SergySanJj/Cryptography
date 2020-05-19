package com.sergeiyarema;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class KaratsubaMultTest {

    @Test
    void multiplyZero() {
        assertEquals(BigInteger.ZERO, KaratsubaMult.multiply(BigInteger.ZERO, BigInteger.ZERO));
        assertEquals(BigInteger.ZERO, KaratsubaMult.multiply(BigInteger.ZERO, BigInteger.ONE));
        assertEquals(BigInteger.ZERO, KaratsubaMult.multiply(BigInteger.ZERO, BigInteger.valueOf(10)));
        assertEquals(BigInteger.ZERO, KaratsubaMult.multiply(BigInteger.ZERO, BigInteger.valueOf(1000)));
        assertEquals(BigInteger.ZERO, KaratsubaMult.multiply(BigInteger.ZERO, BigInteger.valueOf(10000)));

        assertEquals(BigInteger.ZERO, KaratsubaMult.multiply(BigInteger.ONE, BigInteger.ZERO));
        assertEquals(BigInteger.ZERO, KaratsubaMult.multiply(BigInteger.valueOf(10), BigInteger.ZERO));
        assertEquals(BigInteger.ZERO, KaratsubaMult.multiply(BigInteger.valueOf(1000), BigInteger.ZERO));
        assertEquals(BigInteger.ZERO, KaratsubaMult.multiply(BigInteger.valueOf(10000), BigInteger.ZERO));
    }

    @Test
    void multiplyOne() {
        int[] nums = {1, 2, 5, 100, 10000, 1200000};
        for (int num : nums) {
            assertEquals(BigInteger.valueOf(num), KaratsubaMult.multiply(BigInteger.valueOf(num), BigInteger.ONE));
            assertEquals(BigInteger.valueOf(num), KaratsubaMult.multiply(BigInteger.ONE, BigInteger.valueOf(num)));
        }
    }

    @Test
    void multiplyLargeNumbers() {
        for (int i = 95000; i < 100000; i++) {
            for (int j = 95000; j < 100000; j++) {
                assertEquals(BigInteger.valueOf(i).multiply(BigInteger.valueOf(j)),
                        KaratsubaMult.multiply(BigInteger.valueOf(i), BigInteger.valueOf(j)));
            }
        }
    }
}