package org.spacebattle.domain.actions.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.actions.IRepeatable;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class Repeat implements ICommand, IRepeatable {
    private ICommand cmd;

    @Override
    public void execute() {
        cmd.execute();
    }
}
