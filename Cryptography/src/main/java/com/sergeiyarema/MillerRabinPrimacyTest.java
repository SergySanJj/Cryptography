package com.sergeiyarema;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabinPrimacyTest {
    private static Random rnd = new Random();

    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private static final BigInteger THREE = BigInteger.valueOf(3L);

    public static boolean isProbablePrime(BigInteger n, int k) {
        if (n.compareTo(THREE) < 0)
            return true;
        int s = 0;
        BigInteger d = n.subtract(ONE);
        while (d.mod(TWO).equals(ZERO)) {
            s++;
            d = d.divide(TWO);
        }
        for (int i = 0; i < k; i++) {
            BigInteger a = uniformRandom(TWO, n.subtract(ONE));
            BigInteger x = a.modPow(d, n);
            if (x.equals(ONE) || x.equals(n.subtract(ONE)))
                continue;
            int r = 1;
            for (; r < s; r++) {
                x = x.modPow(TWO, n);
                if (x.equals(ONE))
                    return false;
                if (x.equals(n.subtract(ONE)))
                    break;
            }
            if (r == s)
                return false;
        }
        return true;
    }

    private static BigInteger uniformRandom(BigInteger bottom, BigInteger top) {

        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), rnd);
        } while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);
        return res;
    }
}
