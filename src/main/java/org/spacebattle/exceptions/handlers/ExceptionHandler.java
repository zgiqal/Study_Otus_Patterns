package org.spacebattle.exceptions.handlers;

import lombok.Setter;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.exceptions.handlers.impl.DefaultExceptionHandler;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {
    private static final Map<Type, Map<Type, IExceptionHandler>> STORE = new HashMap<>();

    @Setter
    private static IExceptionHandler defaultHandler = new DefaultExceptionHandler();

    public static ICommand handle(ICommand c, Exception e) {
        Type ct = c.getClass();
        Type et = e.getClass();

        Map<Type, IExceptionHandler> cmdStore = STORE.get(ct);
        if (cmdStore == null) return defaultHandler.apply(c, e);

        IExceptionHandler cmdHandler = cmdStore.get(et);
        if (cmdHandler == null) return defaultHandler.apply(c, e);

        return cmdHandler.apply(c, e);
    }

    @SuppressWarnings("unused")
    public static void registerHandler(Type c, Type e, IExceptionHandler f) {
        Map<Type, IExceptionHandler> cmdStore = STORE.computeIfAbsent(c, k -> new HashMap<>());
        cmdStore.put(e, f);
    }
}