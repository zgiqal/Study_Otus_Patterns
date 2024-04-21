package org.spacebattle.domain.actions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spacebattle.domain.objects.IFuelConsuming;
import org.spacebattle.domain.objects.IMovable;
import org.spacebattle.domain.objects.UObject;
import org.spacebattle.exceptions.NotEnoughFuel;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.spacebattle.util.VectorUtil.add;
import static org.spacebattle.util.VectorUtil.getVector;

public class MoveStraightTest {
    private final UObject testObj = mock(UObject.class, withSettings().extraInterfaces(
            IMovable.class,
            IFuelConsuming.class
    ));

    @BeforeEach
    void initTest() {
        reset(testObj);
    }

    @Test
    void givenWrongFuelUnit_whenConstruct_thenError() {
        assertThrows(IllegalArgumentException.class, () -> new MoveStraight(-1, testObj));
    }

    @Test
    void givenWrongObj_whenConstruct_thenError() {
        long testFuelUnit = 1;
        assertThrows(IllegalArgumentException.class, () -> new MoveStraight(testFuelUnit, null));
        assertThrows(IllegalArgumentException.class, () -> new MoveStraight(testFuelUnit, mock(UObject.class,
                withSettings().extraInterfaces(IMovable.class)
        )));
        assertThrows(IllegalArgumentException.class, () -> new MoveStraight(testFuelUnit, mock(UObject.class,
                withSettings().extraInterfaces(IFuelConsuming.class)
        )));
    }

    @Test
    void givenNotEnoughFuel_whenExecute_thenNoMoving() {
        assertThrows(NotEnoughFuel.class, () -> new MoveStraight(1, testObj).execute());

        verify((IMovable) testObj, times(0)).setLocation(any());
        verify((IFuelConsuming) testObj, times(0)).setFuelAmount(anyLong());
    }

    @Test
    void givenObj_whenExecute_thenSuccess() {
        Vector<Double> testLocation = getVector(5., 5.);
        when(((IMovable) testObj).getLocation()).thenReturn(testLocation);

        Vector<Double> testVelocity = getVector(3., 3.);
        when(((IMovable) testObj).getVelocity()).thenReturn(testVelocity);

        long testFuelAmount = 10L;
        long testFuelUnit = 1L;

        when(((IFuelConsuming) testObj).getFuelAmount()).thenReturn(testFuelAmount);

        assertDoesNotThrow(() -> new MoveStraight(testFuelUnit, testObj).execute());

        verify((IMovable) testObj, times(1)).setLocation(add(testLocation, testVelocity));
        verify((IFuelConsuming) testObj, times(1)).setFuelAmount(testFuelAmount - testFuelUnit);
    }

}
