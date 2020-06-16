package com.sergeiyarema;

import java.math.BigInteger;

public class MontgomeryOperations {
    private BigInteger mod;
    private BigInteger modRev;
    private BigInteger r;
    private int shiftLength;

    public MontgomeryOperations(BigInteger mod) {
        this.mod = mod;
        shiftLength = mod.bitLength();
        r = BigInteger.ONE.shiftLeft(shiftLength);
        modRev = gcd(mod, r).negate();
    }

    public BigInteger multiply(BigInteger aRepr, BigInteger bRepr) {
        BigInteger t = aRepr.multiply(bRepr);

        BigInteger u = t.add(t.multiply(modRev).and(r.subtract(BigInteger.ONE)).multiply(mod)).
                shiftRight(shiftLength);

        if (u.compareTo(mod) > 0)
            u = u.subtract(mod);

        return u;
    }

    public BigInteger pow(BigInteger n, BigInteger p) {
        BigInteger nRepresentation = wrapModShifted(n);
        BigInteger resultRepresentation = wrapModShifted(BigInteger.ONE);

        while (!p.equals(BigInteger.ZERO)) {
            if (p.and(BigInteger.ONE).equals(BigInteger.ONE))
                resultRepresentation = multiply(resultRepresentation, nRepresentation);
            nRepresentation = multiply(nRepresentation, nRepresentation);
            p = p.shiftRight(1);
        }

        return multiply(resultRepresentation, BigInteger.ONE);
    }

    public BigInteger wrapModShifted(BigInteger n) {
        return n.shiftLeft(shiftLength).mod(mod);
    }

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        return gcdImpl(a, b).x;
    }

    private static final class GcdMisc {
        public BigInteger gcd;
        public BigInteger x;
        public BigInteger y;
    }

    private static GcdMisc gcdImpl(BigInteger a, BigInteger b) {
        GcdMisc result = new GcdMisc();
        if (a.equals(BigInteger.ZERO)) {
            result.gcd = b;
            result.x = BigInteger.ZERO;
            result.y = BigInteger.ONE;
        } else {
            GcdMisc previousResult = gcdImpl(b.mod(a), a);
            result.gcd = previousResult.gcd;
            result.x = previousResult.y.subtract(b.divide(a).multiply(previousResult.x));
            result.y = previousResult.x;
        }

        return result;
    }
}