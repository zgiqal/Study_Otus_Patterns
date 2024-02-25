package org.example;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.example.common.ArraySource;
import org.example.common.ArraySources;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.example.Main.epsilon;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {


    @ParameterizedTest
    @ArraySources(arrays = {
            @ArraySource(inputs = {1, 0, 1}),
//            @ArraySource(inputs = {1, 0, -1}, outputs = {1, -1}),
//            @ArraySource(inputs = {1 + epsilon, 2 + epsilon/4, 1}, outputs = {-1, -1}),
    })
    void givenSquareCoefficients_whenSolve_thenExpectedRoots(double[] inputs, double[] roots) {
        double[] results = Main.solve(inputs[0], inputs[1], inputs[2]);
        Set<Double> expected = new HashSet<>(Arrays.asList(ArrayUtils.toObject(roots)));
        Set<Double> actual = new HashSet<>(Arrays.asList(ArrayUtils.toObject(results)));
        assertEquals(roots.length, results.length);
        expected.forEach(r -> assertTrue(actual.stream().anyMatch(a -> Math.abs(a-r) < epsilon)));
    }

    @Test
    void givenNullFirst_whenSolve_thenThrowException() {
        assertThrows(IllegalArgumentException.class, ()-> Main.solve(epsilon, 1,1));
        assertDoesNotThrow(() -> Main.solve(epsilon * 10, 1, 1));
    }

    @ParameterizedTest
    @ArraySources(arrays = {
            @ArraySource(inputs = {Double.NaN, 1, 1}),
            @ArraySource(inputs = {1, Double.NaN, 1}),
            @ArraySource(inputs = {1, 1, Double.NaN}),
            @ArraySource(inputs = {Double.POSITIVE_INFINITY, 1, 1}),
            @ArraySource(inputs = {1, Double.POSITIVE_INFINITY, 1}),
            @ArraySource(inputs = {1, 1, Double.POSITIVE_INFINITY}),
            @ArraySource(inputs = {Double.NEGATIVE_INFINITY, 1, 1}),
            @ArraySource(inputs = {1, Double.NEGATIVE_INFINITY, 1}),
            @ArraySource(inputs = {1, 1, Double.NEGATIVE_INFINITY}),
    })
    void givenSpecial_whenSolve_thenThrowException() {
        assertThrows(IllegalArgumentException.class, ()-> Main.solve(epsilon, 1,1));
    }
}
