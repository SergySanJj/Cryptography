package com.sergeiyarema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;


class MillerRabinPrimacyTest {
    private static int limit = 40;


    @Test
    void isProbablePrime() {
        String[] primes = {"1", "3", "3613", "7297", "226673591177742970257407", "2932031007403"};

        for (String p : primes)
            Assertions.assertTrue(MillerRabinPrimacy.isProbablePrime(new BigInteger(p), limit));

    }

    @Test
    void isNotProbablePrime() {
        String[] nonPrimes = {"3341", "2932021007403", "226673591177742970257405"};

        for (String n : nonPrimes)
            Assertions.assertFalse(MillerRabinPrimacy.isProbablePrime(new BigInteger(n), limit));
    }

    @Test
    void CheckMersennePrimes() {
        int maxIterations = 20;
        Assertions.assertTrue(MillerRabinPrimacy.isProbablePrime(BigInteger.valueOf(5L), maxIterations));
        Assertions.assertTrue(MillerRabinPrimacy.isProbablePrime(BigInteger.valueOf(7L), maxIterations));
        Assertions.assertTrue(MillerRabinPrimacy.isProbablePrime(BigInteger.valueOf(13L), maxIterations));
        Assertions.assertTrue(MillerRabinPrimacy.isProbablePrime(BigInteger.valueOf(521L), maxIterations));
        Assertions.assertTrue(MillerRabinPrimacy.isProbablePrime(BigInteger.valueOf(607L), maxIterations));
        Assertions.assertTrue(MillerRabinPrimacy.isProbablePrime(BigInteger.valueOf(1279L), maxIterations));
    }

    @Test
    void CheckMersennePrimeComposites() {
        int maxIterations = 20;
        int testCasesCount = 2000;
        int powerUpsCount = 20;
        List<Integer> mersennePrimes = List.of(5, 7, 13, 521, 607, 1279);
        List<BigInteger> composits = new LinkedList<>();
        int n = mersennePrimes.size();
        for (int i = 0; i < testCasesCount; i++) {
            BigInteger val = BigInteger.valueOf(1L);
            for (int j = 0; j < powerUpsCount; j++) {
                val = val.multiply(BigInteger.valueOf(mersennePrimes.get((i + j) % n)));
            }
            composits.add(val);
        }

        for (BigInteger number : composits){
            Assertions.assertFalse(MillerRabinPrimacy.isProbablePrime(number,maxIterations));
        }
    }

}