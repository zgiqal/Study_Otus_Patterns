package org.spacebattle.domain.actions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spacebattle.domain.objects.IFuelConsuming;
import org.spacebattle.exceptions.NotEnoughFuel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CheckFuelCommandTest {

    private final IFuelConsuming testFuelConsuming = mock(IFuelConsuming.class);

    @BeforeEach
    void initTest() {
        reset(testFuelConsuming);
    }

    @Test
    void givenWrongFuelUnit_whenConstruct_thenError() {
        assertThrows(IllegalArgumentException.class, () -> new CheckFuelCommand(-1, testFuelConsuming));
    }

    @Test
    void givenWrongFuelObj_whenConstruct_thenError() {
        assertThrows(IllegalArgumentException.class, () -> new CheckFuelCommand(1, null));
    }

    @Test
    void givenObjWithNoFuel_whenExecute_thenError() {
        assertThrows(NotEnoughFuel.class, () -> new CheckFuelCommand(1L, testFuelConsuming).execute());
    }

    @Test
    void givenObjWithFuel_whenExecute_thenSuccess() {
        assertDoesNotThrow(() -> new CheckFuelCommand(0L, testFuelConsuming).execute());

        long testFuelUnit = 1L;
        when(testFuelConsuming.getFuelAmount()).thenReturn(testFuelUnit + 1);
        assertDoesNotThrow(() -> new CheckFuelCommand(testFuelUnit, testFuelConsuming).execute());
    }
}
