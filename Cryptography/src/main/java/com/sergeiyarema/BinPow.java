package com.sergeiyarema;

import java.math.BigInteger;

public class BinPow {
    public static BigInteger pow(BigInteger base, BigInteger power, BigInteger mod) {
        BigInteger result = BigInteger.ONE;
        while (!power.equals(BigInteger.ZERO)) {
            if (power.and(BigInteger.ONE).equals(BigInteger.ONE))
            {
                result = result.multiply(base);
                if (mod != null)
                    result = result.mod(mod);
            }
            base = base.multiply(base);
            if (mod != null)
                base = base.mod(mod);
            power = power.shiftRight(1);
        }
        return result;
    }
}
