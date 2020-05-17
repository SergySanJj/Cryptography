package com.sergeiyarema;

import java.math.BigInteger;
import java.util.Random;

public class FermatPrimacyTest {
    private static final Random rand = new Random();

    public static boolean isProbablePrime(BigInteger n, int maxIterations) {
        if (n.equals(BigInteger.ONE))
            return false;

        for (int i = 0; i < maxIterations; i++) {
            BigInteger a = getRandomFermatBase(n);
            a = a.modPow(n.subtract(BigInteger.ONE), n);

            if (!a.equals(BigInteger.ONE))
                return false;
        }

        return true;
    }

    private static BigInteger getRandomFermatBase(BigInteger n) {
        while (true) {
            final BigInteger a = new BigInteger(n.bitLength(), rand);
            if (BigInteger.ONE.compareTo(a) <= 0 && a.compareTo(n) < 0) {
                return a;
            }
        }
    }
}
