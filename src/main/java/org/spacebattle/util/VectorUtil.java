package org.spacebattle.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Vector;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VectorUtil {

    public static Vector<Double> add(Vector<Double> v, Vector<Double> v1) {
        if (v.size() != v1.size()) {
            throw new IllegalArgumentException(String.format("Vector sizes: %s, %s", v.size(), v1.size()));
        }

        for (int i = 0; i < v.size(); i++) {
            v.set(i, v.get(i) + v1.get(i));
        }

        return v;
    }

    public static Vector<Double> getVector(Double... values) {
        return new Vector<>(Arrays.asList(values));
    }


}
