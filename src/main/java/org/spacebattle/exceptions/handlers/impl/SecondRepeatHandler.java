package org.spacebattle.exceptions.handlers.impl;

import org.spacebattle.Main;
import org.spacebattle.domain.actions.CommandWrapper;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.actions.impl.SecondRepeat;
import org.spacebattle.exceptions.handlers.IExceptionHandler;

public class SecondRepeatHandler implements IExceptionHandler {

    @Override
    public ICommand apply(ICommand iCommand, Exception e) {
        return new CommandWrapper(() -> Main.queue.add(new SecondRepeat(iCommand)));
    }
}
