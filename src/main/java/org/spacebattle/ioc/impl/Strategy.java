package org.spacebattle.ioc.impl;

import lombok.AllArgsConstructor;
import org.spacebattle.ioc.IStrategy;

@AllArgsConstructor
public class Strategy implements IStrategy {

    IStrategy strategy;

    @Override
    public Object apply(String s, Object[] objects) {
        return strategy.apply(s, objects);
    }
}
