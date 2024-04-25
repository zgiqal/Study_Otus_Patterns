package org.spacebattle.ioc.scopes;

import lombok.Getter;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.ioc.impl.StrategyOperator;
import org.spacebattle.ioc.impl.Strategy;
import org.spacebattle.ioc.IoC;

import java.util.function.Function;

import static org.spacebattle.ioc.InitialDependency.*;


public class InitCommand implements ICommand {

    @Getter
    private static final ThreadLocal<Object> CURRENT_SCOPES = new ThreadLocal<>();

    private static final IScope ROOT_SCOPE = new Scope();

    private static boolean executed = false;

    @Override
    @SuppressWarnings("unchecked")
    public void execute() {
        if (executed) return;

        synchronized (ROOT_SCOPE) {
            ROOT_SCOPE
                    .put(IOC_SCOPE_CURRENT_SET, args -> new SetCurrentScopeCommand(args[0]))
                    .put(IOC_SCOPE_CURRENT_CLEAR, args -> new SetCurrentScopeCommand())
                    .put(IOC_SCOPE_CURRENT, args -> getCurrentScope())
                    .put(IOC_SCOPE_PARENT, args -> {throw new IllegalArgumentException("There is no parent scope");})
                    .put(IOC_SCOPE_CREATE_EMPTY, args -> new Scope())
                    .put(IOC_SCOPE_CREATE, args -> {
                        var parentScope = args.length > 0 ? args[0] : IoC.resolve(IOC_SCOPE_CURRENT);
                        return IoC.<IScope>resolve(IOC_SCOPE_CREATE_EMPTY).put(IOC_SCOPE_PARENT, innerArgs-> parentScope);
                    })
                    .put(IOC_REGISTER, args ->
                    new RegisterDepencencyCommand((String)args[0], (Function<Object[], Object>)args[1])
                );

            IoC.<ICommand>resolve(IOC_STRATEGY_UPDATE, new StrategyOperator(
                    (Strategy oldStrategy) -> new Strategy((String dependency, Object[] args) ->
                            new DependencyResolver(getCurrentScope()).resolve(dependency, args)))
                ).execute();

            executed = true;
        }
    }

    @SuppressWarnings("unchecked")
    private IScope getCurrentScope() {
        return CURRENT_SCOPES.get() != null ? (IScope) CURRENT_SCOPES.get() : ROOT_SCOPE;
    }
}
