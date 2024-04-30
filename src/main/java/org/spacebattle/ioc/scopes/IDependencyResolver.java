package org.spacebattle.ioc.scopes;

public interface IDependencyResolver {
    Object resolve(String dependency, Object[] args);
}
