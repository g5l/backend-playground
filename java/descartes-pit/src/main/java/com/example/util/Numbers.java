package com.example.util;

import java.util.Objects;

public class Numbers {

    /** Simple arithmetic; Descartes may replace with a constant. */
    public int sum(int a, int b) {
        return a + b;
    }

    /** Builds [0, 1, 2, ..., n-1]. Descartes' 'empty' operator returns an empty array. */
    public int[] firstN(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            out[i] = i;
        }
        return out;
    }

    /** Side-effect method; Descartes 'void' operator can remove the body. */
    public void addOne(Counter c) {
        Objects.requireNonNull(c, "counter");
        c.increment();
    }

    /** String transformation; Descartes 'null' operator can return null always. */
    public String normalize(String s) {
        if (s == null) return null;
        return s.trim().toUpperCase();
    }
}
