package org.spacebattle.ioc.scopes;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Scope extends ConcurrentHashMap<String, Function<Object[], Object>> implements IScope{

    public Scope put(Object key, Function<Object[], Object> value) {
        this.put(key.toString(), value);
        return this;
    }

    public Function<Object[], Object> getBy(Object key) {
        return this.get(key.toString());
    }
}
