package com.sergeiyarema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

class FermatPrimacyTest {

    @Test
    void CheckPrimeValuesBelow10() {
        int maxIterations = 20;
        Assertions.assertTrue(FermatPrimacy.isProbablePrime(BigInteger.valueOf(2L), maxIterations));
        Assertions.assertTrue(FermatPrimacy.isProbablePrime(BigInteger.valueOf(3L), maxIterations));
        Assertions.assertTrue(FermatPrimacy.isProbablePrime(BigInteger.valueOf(5L), maxIterations));
        Assertions.assertTrue(FermatPrimacy.isProbablePrime(BigInteger.valueOf(7L), maxIterations));
    }

    @Test
    void CheckNotPrimeValuesBelow10() {
        int maxIterations = 20;
        Assertions.assertFalse(FermatPrimacy.isProbablePrime(BigInteger.valueOf(4L), maxIterations));
        Assertions.assertFalse(FermatPrimacy.isProbablePrime(BigInteger.valueOf(6L), maxIterations));
        Assertions.assertFalse(FermatPrimacy.isProbablePrime(BigInteger.valueOf(8L), maxIterations));
        Assertions.assertFalse(FermatPrimacy.isProbablePrime(BigInteger.valueOf(9L), maxIterations));
    }

    @Test
    void CheckMersennePrimes() {
        int maxIterations = 20;
        Assertions.assertTrue(FermatPrimacy.isProbablePrime(BigInteger.valueOf(5L), maxIterations));
        Assertions.assertTrue(FermatPrimacy.isProbablePrime(BigInteger.valueOf(7L), maxIterations));
        Assertions.assertTrue(FermatPrimacy.isProbablePrime(BigInteger.valueOf(13L), maxIterations));
        Assertions.assertTrue(FermatPrimacy.isProbablePrime(BigInteger.valueOf(521L), maxIterations));
        Assertions.assertTrue(FermatPrimacy.isProbablePrime(BigInteger.valueOf(607L), maxIterations));
        Assertions.assertTrue(FermatPrimacy.isProbablePrime(BigInteger.valueOf(1279L), maxIterations));
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
            Assertions.assertFalse(FermatPrimacy.isProbablePrime(number,maxIterations));
        }
    }
}