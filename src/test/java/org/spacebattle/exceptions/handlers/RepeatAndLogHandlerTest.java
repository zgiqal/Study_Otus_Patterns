package org.spacebattle.exceptions.handlers;

import org.junit.jupiter.api.Test;
import org.spacebattle.domain.actions.impl.LogException;
import org.spacebattle.domain.actions.impl.Move;
import org.spacebattle.domain.actions.impl.Repeat;
import org.spacebattle.exceptions.handlers.impl.RepeatAndLogHandler;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class RepeatAndLogHandlerTest extends ComplexHandlerTest {

    public RepeatAndLogHandlerTest() {
        super(new RepeatAndLogHandler());
    }

    @Test
    void givenCommandToRepeatAndLogOnException_whenException_thenRightCommands() throws InterruptedException {
        Move testCmd = mock(Move.class);
        doThrow(NullPointerException.class).when(testCmd).execute();

        assertCommandsChain(testCmd, Repeat.class, LogException.class);
    }

}
