package com.sergeiyarema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class MontgomeryOperationsTest {
    private static BigInteger mod = new BigInteger("9321783782771786617628686181");
    private MontgomeryOperations operations;

    @BeforeEach
    void prepare() {
        operations = new MontgomeryOperations(mod);
    }

    @Test
    void multiply() {
        for (int i = 99000; i < 100000; i++) {
            for (int j = 0; j < 1000; j++) {
                assertEquals(BigInteger.valueOf(i).multiply(BigInteger.valueOf(j)).mod(mod),
                        operations.multiply(
                                operations.multiply(operations.wrapModShifted(BigInteger.valueOf(i)),
                                        operations.wrapModShifted(BigInteger.valueOf(j))),
                                BigInteger.ONE));
            }
        }
    }

    @Test
    void pow() {
        for (int i = 95000; i < 100000; i++) {
            for (int j = 0; j < 50; j++) {
                assertEquals(BinPow.pow(BigInteger.valueOf(i), BigInteger.valueOf(j), mod),
                        operations.pow(BigInteger.valueOf(i), BigInteger.valueOf(j)));
            }
        }
    }
}