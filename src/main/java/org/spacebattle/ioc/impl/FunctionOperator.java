package org.spacebattle.ioc.impl;

import lombok.AllArgsConstructor;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@AllArgsConstructor
public class FunctionOperator implements Function<Object[], Object> {
    Function<Object[], Object> method;

    @Override
    public Object apply(Object[] objects) {
        return method.apply(objects);
    }
}
