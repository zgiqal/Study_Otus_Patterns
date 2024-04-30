package org.spacebattle.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Vector;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VectorUtil {

    public static Vector<Double> add(Vector<Double> v1, Vector<Double> v2) {
        if (v1.size() != v2.size()) {
            throw new IllegalArgumentException(String.format("Vector sizes: %s, %s", v1.size(), v2.size()));
        }

        for (int i = 0; i < v1.size(); i++) {
            Double vi1 = v1.get(i) == null ? 0 : v1.get(i);
            Double vi2 = v2.get(i) == null ? 0 : v2.get(i);
            v1.set(i, vi1 + vi2);
        }

        return v1;
    }

    public static Vector<Double> getVector(Double... values) {
        return new Vector<>(Arrays.asList(values));
    }
}
