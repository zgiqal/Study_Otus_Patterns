package org.spacebattle.exceptions.handlers;

import org.junit.jupiter.api.Test;
import org.spacebattle.Main;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.actions.impl.LogException;
import org.spacebattle.exceptions.handlers.impl.LogHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class LogHandlerTest {

    LogHandler testHandler = new LogHandler();

    @Test
    void givenParams_whenApply_thenSuccess() throws InterruptedException {
        ICommand testCmd = mock(ICommand.class);
        Exception testException = new Exception();

        assertTrue(Main.queue.isEmpty());

        testHandler.apply(testCmd, testException).execute();
        assertEquals(new LogException(testCmd.getClass(), testException), Main.queue.take());
    }
}
