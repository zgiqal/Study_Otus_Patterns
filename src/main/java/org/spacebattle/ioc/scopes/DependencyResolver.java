package org.spacebattle.ioc.scopes;

import lombok.AllArgsConstructor;

import java.util.function.Function;

import static org.spacebattle.ioc.InitialDependency.IOC_SCOPE_PARENT;

@AllArgsConstructor
public class DependencyResolver implements IDependencyResolver{

    private IScope dependencies;

    @Override
    public Object resolve(String dependency, Object[] args) {
        var currentScopeDependencies = dependencies;

        while (true)
        {
            Function<Object[], Object> dependencyResolverStrategy = currentScopeDependencies.get(dependency);
            if (dependencyResolverStrategy != null) {
                return dependencyResolverStrategy.apply(args);
            } else {
                currentScopeDependencies = (IScope) currentScopeDependencies.getBy(IOC_SCOPE_PARENT).apply(args);
            }
        }
    }
}
