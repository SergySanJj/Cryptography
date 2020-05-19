package com.sergeiyarema;

import java.math.BigInteger;

public class KaratsubaMult {
    public static final int maxBruteBitLen = 2000;

    public static BigInteger multiply(BigInteger x, BigInteger y) {
        // optimization cut
        int bitLen = Math.max(x.bitLength(), y.bitLength());
        if (bitLen <= maxBruteBitLen)
            return x.multiply(y);

        bitLen = (bitLen / 2) + (bitLen % 2);

        // x = a + 2^bitLen b,   y = c + 2^bitLen d
        BigInteger b = x.shiftRight(bitLen);
        BigInteger a = x.subtract(b.shiftLeft(bitLen));
        BigInteger d = y.shiftRight(bitLen);
        BigInteger c = y.subtract(d.shiftLeft(bitLen));

        BigInteger ac = multiply(a, c);
        BigInteger bd = multiply(b, d);
        BigInteger abcd = multiply(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(bitLen)).add(bd.shiftLeft(2 * bitLen));
    }
}
