package org.spacebattle.exceptions.handlers;

import org.spacebattle.domain.actions.ICommand;

import java.util.function.BiFunction;

@FunctionalInterface
public interface IExceptionHandler extends BiFunction<ICommand, Exception, ICommand> {
}
