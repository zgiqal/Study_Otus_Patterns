package org.spacebattle.exceptions.handlers.impl;

import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.actions.impl.Repeat;
import org.spacebattle.domain.actions.impl.SecondRepeat;
import org.spacebattle.exceptions.handlers.IExceptionHandler;

public class RepeatTwiceAndLogHandler implements IExceptionHandler {

    @Override
    public ICommand apply(ICommand iCommand, Exception e) {
        IExceptionHandler handler;
        ICommand cmd = iCommand;
        if (iCommand instanceof SecondRepeat repeat) {
            cmd = repeat.getCmd();
            handler = new RepeatAndLogHandler();
        }
        else if (iCommand instanceof Repeat repeat) {
            cmd = repeat.getCmd();
            handler = new LogHandler();
        }
        else {
            handler = new SecondRepeatHandler();
        }

        return handler.apply(cmd, e);
    }
}
