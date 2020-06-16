package com.sergeiyarema;

import java.math.BigInteger;

public class EuclidExtendedGCD {


    public static BigInteger extendedGCD(BigInteger a, BigInteger b) {
        return extendedEuclidFull(a, b)[0];
    }

    public static int simpleGCD(int a, int b) {
        for (int i = Math.min(a, b); i >= 1; i--)
            if (a % i == 0 && b % i == 0)
                return i;
        return 0;
    }


    public static BigInteger[] extendedEuclidFull(BigInteger a, BigInteger b) {
        BigInteger[] ans = new BigInteger[3];

        if (b.intValue() == 0) {
            ans[0] = a;
            ans[1] = BigInteger.valueOf(1);
            ans[2] = BigInteger.valueOf(0);
        } else {
            BigInteger q = a.divide(b);
            ans = extendedEuclidFull(b, a.remainder(b));
            BigInteger temp = ans[1].subtract(ans[2].multiply(q));
            ans[1] = ans[2];
            ans[2] = temp;
        }

        return ans;
    }
}
