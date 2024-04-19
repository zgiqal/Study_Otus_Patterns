package org.spacebattle.domain.actions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CommandWrapper implements ICommand {
    private Runnable runnable = () -> {};

    @Override
    public void execute() {
        runnable.run();
    }
}
