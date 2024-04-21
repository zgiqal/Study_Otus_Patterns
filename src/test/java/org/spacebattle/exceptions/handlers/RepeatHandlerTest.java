package org.spacebattle.exceptions.handlers;

import org.junit.jupiter.api.Test;
import org.spacebattle.Main;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.actions.impl.Repeat;
import org.spacebattle.exceptions.handlers.impl.RepeatHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class RepeatHandlerTest {

    RepeatHandler testHandler = new RepeatHandler();

    @Test
    void givenParams_whenApply_thenSuccess() throws Exception {
        ICommand testCmd = mock(ICommand.class);
        assertTrue(Main.queue.isEmpty());

        testHandler.apply(testCmd, new Exception()).execute();
        assertEquals(new Repeat(testCmd), Main.queue.take());
    }
}
