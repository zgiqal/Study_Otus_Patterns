package org.spacebattle.domain.actions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spacebattle.domain.objects.IMovable;
import org.spacebattle.domain.objects.IVelocityChanging;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.spacebattle.util.VectorUtil.getVector;

public class ChangeVelocityCommandTest {
    private final IMovable testObj = mock(IMovable.class, withSettings().extraInterfaces(
            IVelocityChanging.class
    ));

    @BeforeEach
    void initTest() {
        reset(testObj);
    }

    @Test
    void givenWrongObj_whenConstruct_thenError() {
        Vector<Double> testVector = getVector(0.,0.);
        when(testObj.getVelocity()).thenReturn(testVector);
        assertThrows(IllegalArgumentException.class, () -> new ChangeVelocityCommand(testVector, null));
        assertThrows(IllegalArgumentException.class, () -> new ChangeVelocityCommand(testVector, mock(IMovable.class)));
    }

    @Test
    void givenWrongVelocity_whenConstruct_thenError() {
        assertThrows(NullPointerException.class, () -> new ChangeVelocityCommand(null, testObj));

        when(testObj.getVelocity()).thenReturn(getVector(0.,0.));
        assertThrows(IllegalArgumentException.class, () -> new ChangeVelocityCommand(getVector(0.), testObj));
        assertThrows(IllegalArgumentException.class, () -> new ChangeVelocityCommand(getVector(0., 0., 0.), testObj));
    }

    @Test
    void givenObj_whenExecute_thenSuccess() {
        Vector<Double> testVelocity = getVector(1., 1.);

        when(testObj.getVelocity()).thenReturn(getVector(0., 0.));
        assertDoesNotThrow(() -> new ChangeVelocityCommand(testVelocity, testObj).execute());

        verify((IVelocityChanging) testObj, times(1)).setVelocity(testVelocity);
    }

}
