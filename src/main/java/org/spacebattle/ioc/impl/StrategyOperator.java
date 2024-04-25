package org.spacebattle.ioc.impl;

import lombok.AllArgsConstructor;

import java.util.function.UnaryOperator;

@AllArgsConstructor
public class StrategyOperator implements UnaryOperator<Strategy> {

    UnaryOperator<Strategy> strategyChanger;

    @Override
    public Strategy apply(Strategy strategy) {
        return strategyChanger.apply(strategy);
    }
}
