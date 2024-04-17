package org.spacebattle.domain.actions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spacebattle.domain.objects.IRotable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RotateTest {

    private final IRotable testRotable = mock(IRotable.class);

    @BeforeEach
    void initTest() {
        reset(testRotable);
    }

    @Test
    void givenLocationAndVelocity_whenMove_thenExpectedLocation() {
        when(testRotable.getAngularVelocity()).thenReturn(25);
        when(testRotable.getDirectionsNumber()).thenReturn(8);
        when(testRotable.getDirection()).thenReturn(5);


        new Rotate(testRotable).Execute();

        verify(testRotable, times(1)).setDirection(6);

    }


    @Test
    void givenNonDirectionNumbers_whenMove_thenError() {
        assertThrows(ArithmeticException.class, () -> new Rotate(testRotable).Execute());
    }

    @Test
    void givenUnableSetLocation_whenMove_thenError() {
        when(testRotable.getDirectionsNumber()).thenReturn(1);
        doThrow(IllegalArgumentException.class).when(testRotable).setDirection(0);

        assertThrows(IllegalArgumentException.class, () -> new Rotate(testRotable).Execute());
    }

}
