package com.sergeiyarema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class FermatPrimacyTestTest {

    @Test
    void CheckPrimeValuesBelow10() {
        int maxIterations = 20;
        Assertions.assertTrue(FermatPrimacyTest.isProbablePrime(BigInteger.valueOf(2L),maxIterations));
        Assertions.assertTrue(FermatPrimacyTest.isProbablePrime(BigInteger.valueOf(3L),maxIterations));
        Assertions.assertTrue(FermatPrimacyTest.isProbablePrime(BigInteger.valueOf(5L),maxIterations));
        Assertions.assertTrue(FermatPrimacyTest.isProbablePrime(BigInteger.valueOf(7L),maxIterations));
    }

    @Test
    void CheckNotPrimeValuesBelow10() {
        int maxIterations = 20;
        Assertions.assertFalse(FermatPrimacyTest.isProbablePrime(BigInteger.valueOf(4L),maxIterations));
        Assertions.assertFalse(FermatPrimacyTest.isProbablePrime(BigInteger.valueOf(6L),maxIterations));
        Assertions.assertFalse(FermatPrimacyTest.isProbablePrime(BigInteger.valueOf(8L),maxIterations));
        Assertions.assertFalse(FermatPrimacyTest.isProbablePrime(BigInteger.valueOf(9L),maxIterations));
    }
}