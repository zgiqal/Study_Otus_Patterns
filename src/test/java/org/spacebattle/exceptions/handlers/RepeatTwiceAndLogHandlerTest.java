package org.spacebattle.exceptions.handlers;

import org.junit.jupiter.api.Test;
import org.spacebattle.domain.actions.impl.LogException;
import org.spacebattle.domain.actions.impl.Move;
import org.spacebattle.domain.actions.impl.Repeat;
import org.spacebattle.domain.actions.impl.SecondRepeat;
import org.spacebattle.exceptions.handlers.impl.RepeatTwiceAndLogHandler;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class RepeatTwiceAndLogHandlerTest extends ComplexHandlerTest {

    public RepeatTwiceAndLogHandlerTest() {
        super(new RepeatTwiceAndLogHandler());
    }

    @Test
    void givenCommandToRepeatAndLogOnException_whenException_thenRightCommands() throws Exception {
        Move testCmd = mock(Move.class);
        doThrow(NullPointerException.class).when(testCmd).execute();

        assertCommandsChain(testCmd, SecondRepeat.class, Repeat.class, LogException.class);
    }

}
