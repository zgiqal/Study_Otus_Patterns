package org.spacebattle.domain.actions.impl;

import org.junit.jupiter.api.Test;
import org.spacebattle.domain.actions.ICommand;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RepeatTest {

    @Test
    void givenWrongParams_whenExecute_thenError() {
        Repeat testCmd = new Repeat( null);
        assertThrows(NullPointerException.class, testCmd::execute);
    }

    @Test
    void givenParams_whenExecute_thenSuccessLog() {
        ICommand mockCmd = mock(ICommand.class);
        new Repeat(mockCmd).execute();
        verify(mockCmd, times(1)).execute();
    }

}
