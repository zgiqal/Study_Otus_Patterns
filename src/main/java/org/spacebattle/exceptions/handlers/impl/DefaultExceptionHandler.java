package org.spacebattle.exceptions.handlers.impl;

import org.spacebattle.domain.actions.CommandWrapper;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.exceptions.handlers.IExceptionHandler;

public class DefaultExceptionHandler implements IExceptionHandler {
    @Override
    public ICommand apply(ICommand iCommand, Exception e) {
        // TODO например, логирование,  отправка отчёта Sentry, фиксация в метриках, и т.д.
        return new CommandWrapper();
    }
}
