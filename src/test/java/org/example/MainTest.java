package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    void givenCoefficients101_whenSolve_thenEmptyRoots() {
        assertEquals(new double[0], Main.solve(1, 0, 1));
    }
}
