package org.spacebattle.ioc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.ioc.impl.Strategy;
import org.spacebattle.ioc.impl.StrategyOperator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.spacebattle.ioc.InitialDependency.IOC_STRATEGY_UPDATE;

public class IoCTest {

    private static Strategy strategy;

    @BeforeAll
    static void init() {
        strategy = IoC.getStrategy();
    }

    @AfterEach
    void reset() {
        IoC.setStrategy(strategy);
    }

    @Test
    void givenUnknownDependency_whenResolve_thenError() {
      assertThrows(IllegalArgumentException.class, () -> IoC.resolve("Unknown"));
    }

    @Test
    @SuppressWarnings("unused")
    void givenWrongType_whenResolve_thenError() {
        assertThrows(ClassCastException.class, () -> {
            String testResult = IoC.resolve(IOC_STRATEGY_UPDATE, new StrategyOperator(args -> args));
        });
    }

    @Test
    void givenWrongParam_whenResolve_thenError() {
        assertThrows(ClassCastException.class, () -> {
            IoC.<ICommand>resolve(IOC_STRATEGY_UPDATE, new Strategy((key, args) -> args)).execute();
        });
    }

    @Test
    void IoCUpdateResolveStrategy() {
        Strategy defaultStrategy = IoC.getStrategy();
        Strategy testStrategy = mock(Strategy.class);
        StrategyOperator testOperator = spy(new StrategyOperator(args -> testStrategy));

        IoC.<ICommand>resolve(IOC_STRATEGY_UPDATE, testOperator).execute();
        verify(testOperator, times(1)).apply(defaultStrategy);
        assertEquals(testStrategy, IoC.getStrategy());
    }


}
