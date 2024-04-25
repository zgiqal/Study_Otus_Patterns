package org.spacebattle.ioc;

import lombok.Getter;
import lombok.Setter;
import org.spacebattle.ioc.impl.Strategy;
import org.spacebattle.ioc.impl.StrategyOperator;
import org.spacebattle.ioc.impl.UpdateIocResolveDependencyStrategyCommand;

import static org.apache.commons.lang3.ArrayUtils.subarray;
import static org.spacebattle.ioc.InitialDependency.IOC_STRATEGY_UPDATE;

public class IoC {

    @Setter
    @Getter
    public static Strategy strategy = new Strategy((String dependency, Object[] args) -> {
            if (IOC_STRATEGY_UPDATE.toString().equals(dependency)) {
                return new UpdateIocResolveDependencyStrategyCommand((StrategyOperator) args[0]);
            }
            else {
                throw new IllegalArgumentException(String.format("Dependency %s is not found.", dependency));
            }
        });

    @SuppressWarnings("unchecked")
    public static <T> T resolve(Object... args) {
        return (T) strategy.apply(args[0].toString(), subarray(args, 1, args.length));
    }
}
