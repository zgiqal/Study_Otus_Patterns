package org.spacebattle.domain.actions.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spacebattle.domain.objects.IMovable;

import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.spacebattle.util.VectorUtil.getVector;

public class MoveTest {

    private final IMovable testMovable = mock(IMovable.class);

    @BeforeEach
    void initTest() {
        reset(testMovable);
    }

    @Test
    void givenLocationAndVelocity_whenMove_thenExpectedLocation() {
        when(testMovable.getLocation()).thenReturn(getVector(12., 5.));
        when(testMovable.getVelocity()).thenReturn(getVector(-7., 3.));

        new Move(testMovable).Execute();

        verify(testMovable, times(1)).setLocation(getVector(5., 8.));

    }

    @Test
    void givenNonLocation_whenMove_thenError() {
        assertThrows(NullPointerException.class, () -> new Move(testMovable).Execute());
    }

    @Test
    void givenNonVelocity_whenMove_thenError() {
        when(testMovable.getLocation()).thenReturn(new Vector<>(List.of(0., 0.)));
        assertThrows(NullPointerException.class, () -> new Move(testMovable).Execute());
    }

    @Test
    void givenUnableSetLocation_whenMove_thenError() {
        when(testMovable.getLocation()).thenReturn(getVector(0., 0.));
        when(testMovable.getVelocity()).thenReturn(getVector(1., 0.));
        doThrow(IllegalArgumentException.class).when(testMovable).setLocation(any());

        assertThrows(IllegalArgumentException.class, () -> new Move(testMovable).Execute());
    }

}
