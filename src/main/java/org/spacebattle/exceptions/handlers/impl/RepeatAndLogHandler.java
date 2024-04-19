package org.spacebattle.exceptions.handlers.impl;

import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.actions.impl.Repeat;
import org.spacebattle.exceptions.handlers.IExceptionHandler;

public class RepeatAndLogHandler implements IExceptionHandler {

    @Override
    public ICommand apply(ICommand iCommand, Exception e) {
        IExceptionHandler handler;
        ICommand cmd = iCommand;
        if (iCommand instanceof Repeat repeat) {
            cmd = repeat.getCmd();
            handler = new LogHandler();
        }
        else {
            handler = new RepeatHandler();
        }

        return handler.apply(cmd, e);
    }
}
