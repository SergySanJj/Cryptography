package com.sergeiyarema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;


class MillerRabinPrimacyTestTest {
    private static int limit = 40;


    @Test
    void isProbablePrime() {
        String[] primes = {"1", "3", "3613", "7297", "226673591177742970257407", "2932031007403"};

        for (String p : primes)
            Assertions.assertTrue(MillerRabinPrimacyTest.isProbablePrime(new BigInteger(p), limit));

    }

    @Test
    void isNotProbablePrime() {
        String[] nonPrimes = {"3341", "2932021007403", "226673591177742970257405"};

        for (String n : nonPrimes)
            Assertions.assertFalse(MillerRabinPrimacyTest.isProbablePrime(new BigInteger(n), limit));
    }
}