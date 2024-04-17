package org.spacebattle.exceptions.handlers;


import lombok.AllArgsConstructor;
import org.spacebattle.Main;
import org.spacebattle.domain.actions.ICommand;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AllArgsConstructor
public class ComplexHandlerTest {

    IExceptionHandler testHandler;


    protected void assertCommandsChain(ICommand testCmd, Class<?>... clss) throws InterruptedException {
        ICommand cmd = testCmd;
        for(Class<?> cls : clss) {
            cmd = processAndAssertResult(cmd, cls);
        }
    }

    private <T> ICommand processAndAssertResult(ICommand cmd, Class<T> cls) throws InterruptedException {
        try {
            cmd.execute();
        } catch (Exception e) {
            testHandler.apply(cmd, e).execute();
        }

        ICommand result = Main.queue.poll(1, MICROSECONDS);
        assertNotNull(result);
        assertEquals(cls, result.getClass());

        return result;
    }
}
