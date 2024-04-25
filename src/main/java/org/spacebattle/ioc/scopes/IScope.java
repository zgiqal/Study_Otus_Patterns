package org.spacebattle.ioc.scopes;

import java.util.Map;
import java.util.function.Function;

public interface IScope extends Map<String, Function<Object[], Object>> {
    Scope put(Object key, Function<Object[], Object> value);
    Function<Object[], Object> getBy(Object key);
}
