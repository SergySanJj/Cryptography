package com.sergeiyarema;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.function.DoubleToIntFunction;

import static org.junit.jupiter.api.Assertions.*;

class BinPowTest {
    @Test
    void poweringToZero() {
        BigInteger binPowResult = BinPow.pow(BigInteger.valueOf(0L), BigInteger.valueOf(0), BigInteger.valueOf(10));
        assertEquals(BigInteger.ONE, binPowResult);
        binPowResult = BinPow.pow(BigInteger.valueOf(1L), BigInteger.valueOf(0), BigInteger.valueOf(10));
        assertEquals(BigInteger.ONE, binPowResult);
        binPowResult = BinPow.pow(BigInteger.valueOf(1337L), BigInteger.valueOf(0), BigInteger.valueOf(10));
        assertEquals(BigInteger.ONE, binPowResult);
        binPowResult = BinPow.pow(BigInteger.valueOf(1000000L), BigInteger.valueOf(0), BigInteger.valueOf(10));
        assertEquals(BigInteger.ONE, binPowResult);
    }

    @Test
    void poweringZero() {
        BigInteger binPowResult = BinPow.pow(BigInteger.valueOf(0L), BigInteger.valueOf(0), BigInteger.valueOf(10));
        assertEquals(BigInteger.ONE, binPowResult);
        binPowResult = BinPow.pow(BigInteger.valueOf(0L), BigInteger.valueOf(5L), BigInteger.valueOf(10));
        assertEquals(BigInteger.ZERO, binPowResult);
        binPowResult = BinPow.pow(BigInteger.valueOf(0L), BigInteger.valueOf(256), BigInteger.valueOf(10));
        assertEquals(BigInteger.ZERO, binPowResult);
        binPowResult = BinPow.pow(BigInteger.valueOf(0L), BigInteger.valueOf(1337), BigInteger.valueOf(10));
        assertEquals(BigInteger.ZERO, binPowResult);

    }

    @Test
    void poweringOne() {
        BigInteger binPowResult = BinPow.pow(BigInteger.valueOf(1L), BigInteger.valueOf(0), BigInteger.valueOf(10));
        assertEquals(BigInteger.ONE, binPowResult);
        binPowResult = BinPow.pow(BigInteger.valueOf(1L), BigInteger.valueOf(5L), BigInteger.valueOf(10));
        assertEquals(BigInteger.ONE, binPowResult);
        binPowResult = BinPow.pow(BigInteger.valueOf(1L), BigInteger.valueOf(256), BigInteger.valueOf(10));
        assertEquals(BigInteger.ONE, binPowResult);
        binPowResult = BinPow.pow(BigInteger.valueOf(1L), BigInteger.valueOf(1337), BigInteger.valueOf(10));
        assertEquals(BigInteger.ONE, binPowResult);

    }


    @Test
    void smallNumbersPowering() {
        int limit = 1000;
        int mod = 100000;
        int base = 1337;
        for (int i = 0; i < limit; i++) {
            assertEquals(BigInteger.valueOf(simplePow(base, i, mod)),
                    BinPow.pow(BigInteger.valueOf(base), BigInteger.valueOf(i), BigInteger.valueOf(mod)));
        }
    }

    int simplePow(int base, int power, int mod) {
        int res = 1;
        for (int i = 0; i < power; ++i)
            res = (res * base) % mod;
        return res;
    }
}