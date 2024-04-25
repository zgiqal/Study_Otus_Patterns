package org.spacebattle.ioc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.ioc.impl.FunctionOperator;
import org.spacebattle.ioc.impl.Strategy;
import org.spacebattle.ioc.scopes.InitCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.spacebattle.ioc.InitialDependency.*;

public class InitCommandTest {

    private static Strategy strategy;

    @BeforeAll
    static void init() {
        strategy = IoC.getStrategy();
        new InitCommand().execute();
        IoC.<ICommand>resolve(IOC_SCOPE_CURRENT_SET, IoC.resolve(IOC_SCOPE_CREATE)).execute();
    }

    @AfterEach
    void clear() {
        IoC.<ICommand>resolve(IOC_SCOPE_CURRENT_CLEAR).execute();
    }

    @AfterAll
    static void finish() {
        IoC.setStrategy(strategy);
    }

    @Test
    void givenRegister_whenResolve_thenSuccess() {
        IoC.<ICommand>resolve(IOC_REGISTER, "dependency", new FunctionOperator(args->1)).execute();
        assertEquals(1, IoC.<Integer>resolve("dependency"));
    }

    @Test
    void givenUnregistered_whenResolve_thenError() {
        assertThrows(Exception.class, () -> IoC.<Integer>resolve("tmpDependency"));
    }

    @Test
    void givenParentWithDependency_whenResolve_thenSuccess() {
        IoC.<ICommand>resolve(IOC_REGISTER, "someDependency", new FunctionOperator(args -> 1)).execute();

        var iocScope = IoC.resolve(IOC_SCOPE_CREATE);
        IoC.<ICommand>resolve(IOC_SCOPE_CURRENT_SET, iocScope).execute();

        assertEquals(iocScope, IoC.resolve(IOC_SCOPE_CURRENT));
        assertEquals(1, IoC.<Integer>resolve("someDependency"));
    }

    @Test
    void givenNewParentScope_whenResolveFromChild_thenSuccess() {
        var scope1 = IoC.resolve(IOC_SCOPE_CREATE);
        var scope2 = IoC.resolve(IOC_SCOPE_CREATE, scope1);

        IoC.<ICommand>resolve(IOC_SCOPE_CURRENT_SET, scope1);
        IoC.<ICommand>resolve(IOC_REGISTER, "someDependency", new FunctionOperator(args ->  2)).execute();
        IoC.<ICommand>resolve(IOC_SCOPE_CURRENT_SET, scope2);

        assertEquals(2, IoC.<Integer>resolve("someDependency"));
    }
}
