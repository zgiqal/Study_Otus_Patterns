package org.spacebattle.ioc;

import java.util.function.BiFunction;

public interface IStrategy extends BiFunction<String, Object[], Object> {
}
