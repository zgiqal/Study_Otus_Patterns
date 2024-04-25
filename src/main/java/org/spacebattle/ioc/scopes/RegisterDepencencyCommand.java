package org.spacebattle.ioc.scopes;

import lombok.AllArgsConstructor;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.ioc.IoC;

import java.util.function.Function;

import static org.spacebattle.ioc.InitialDependency.IOC_SCOPE_CURRENT;

@AllArgsConstructor
public class RegisterDepencencyCommand implements ICommand {
    private String dependency;
    private Function<Object[], Object> dependencyResolverStrategy;

    @Override
    public void execute() {
        var currentScope = IoC.<IScope>resolve(IOC_SCOPE_CURRENT);
        currentScope.put(dependency, dependencyResolverStrategy);
    }
}
