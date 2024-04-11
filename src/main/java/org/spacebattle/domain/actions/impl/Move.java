package org.spacebattle.domain.actions.impl;

import lombok.AllArgsConstructor;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.objects.IMovable;

import static org.spacebattle.util.VectorUtil.add;

@AllArgsConstructor
public class Move implements ICommand {
    private IMovable movable;

    public void Execute() {
        movable.setLocation(add(movable.getLocation(), movable.getVelocity()));
    }
}
