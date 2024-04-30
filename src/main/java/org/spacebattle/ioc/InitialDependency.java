package org.spacebattle.ioc;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum InitialDependency {
    IOC_SCOPE_CURRENT_SET,
    IOC_SCOPE_CURRENT_CLEAR,
    IOC_SCOPE_CURRENT,
    IOC_SCOPE_PARENT,
    IOC_SCOPE_CREATE_EMPTY,
    IOC_SCOPE_CREATE,
    IOC_REGISTER,
    IOC_STRATEGY_UPDATE;

    private final String title;

    InitialDependency() {
        title = Stream.of(name().split("_"))
                .map(str -> StringUtils.capitalize(str.toLowerCase()))
                .collect(Collectors.joining("."));
    }

    @Override
    public String toString() {
        return title;
    }
}
