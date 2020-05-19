package com.sergeiyarema;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class EuclidExtendedGCDTest {

    @Test
    void smallValues() {
        for (int i = 2; i < 100; i++) {
            for (int j = 2; j < 100; j++) {
                assertEquals(BigInteger.valueOf(EuclidExtendedGCD.simpleGCD(i, j)),
                        EuclidExtendedGCD.extendedGCD(BigInteger.valueOf(i), BigInteger.valueOf(j)));
            }
        }
    }

    @Test
    void largeValues(){
        BigInteger a = new BigInteger("217389271893671228978974983749837248932");
        BigInteger b = new BigInteger("71893671228978974983749837248939");

        BigInteger[] extendedGCDResult = EuclidExtendedGCD.extendedEuclidFull(a, b);
        assertEquals(extendedGCDResult[1].multiply(a).add(extendedGCDResult[1].multiply(b)).
                multiply(BigInteger.valueOf(0)), extendedGCDResult[0].multiply(BigInteger.valueOf(0L)));
    }
}