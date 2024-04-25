package org.spacebattle.ioc.impl;

import lombok.AllArgsConstructor;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.ioc.IoC;

import java.util.function.UnaryOperator;

@AllArgsConstructor
public class UpdateIocResolveDependencyStrategyCommand implements ICommand {

    UnaryOperator<Strategy> updateIoCStrategy;

    @Override
    public void execute() {
        IoC.setStrategy(updateIoCStrategy.apply(IoC.getStrategy()));
    }
}
