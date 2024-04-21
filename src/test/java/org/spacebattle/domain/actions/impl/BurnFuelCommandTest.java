package org.spacebattle.domain.actions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spacebattle.domain.objects.IFuelConsuming;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BurnFuelCommandTest {

    private final IFuelConsuming testFuelBurning = mock(IFuelConsuming.class);

    @BeforeEach
    void initTest() {
        reset(testFuelBurning);
    }

    @Test
    void givenWrongFuelUnit_whenConstruct_thenError() {
        assertThrows(IllegalArgumentException.class, () -> new BurnFuelCommand(-1, testFuelBurning));
    }

    @Test
    void givenWrongFuelObj_whenConstruct_thenError() {
        assertThrows(IllegalArgumentException.class, () -> new BurnFuelCommand(1, null));
    }

    @Test
    void givenObjWithFuel_whenExecute_thenSuccess() {
        assertDoesNotThrow(() -> new BurnFuelCommand(0L, testFuelBurning).execute());
        verify(testFuelBurning, times(1)).setFuelAmount(0L);

        long testFuelUnit = 1L;
        long testFuelDelta = 1;
        when(testFuelBurning.getFuelAmount()).thenReturn(testFuelUnit + testFuelDelta);
        assertDoesNotThrow(() -> new BurnFuelCommand(testFuelUnit, testFuelBurning).execute());
        verify(testFuelBurning, times(1)).setFuelAmount(testFuelDelta);
    }
}
