package org.spacebattle.domain.actions.impl;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.spacebattle.domain.actions.ICommand;

import static org.mockito.Mockito.*;

public class LogExceptionTest {

    @Test
    void givenParams_whenExecute_thenSuccessLog() {
        String testMsg = "testMsg";
        Exception testException = new Exception(testMsg);
        ICommand cmd = mock(ICommand.class);

        try(MockedStatic<LoggerFactory> logManager = Mockito.mockStatic(LoggerFactory.class)) {
            Logger logger = mock(Logger.class);
            logManager.when(() -> LoggerFactory.getLogger(any(Class.class))).thenReturn(logger);

            LogException testCmd = new LogException(cmd.getClass(), testException);

            testCmd.execute();
            verify(logger, times(1)).warn(LogException.PATTERN, cmd.getClass(), testMsg);
        }
    }

}
