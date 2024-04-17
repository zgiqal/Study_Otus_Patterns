package org.spacebattle.domain.actions.impl;

import lombok.AllArgsConstructor;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.objects.IRotable;

@AllArgsConstructor
public class Rotate implements ICommand {
    private IRotable rotatable;

    public void execute() {
        int angularDelta = rotatable.getAngularVelocity() % rotatable.getDirectionsNumber();
        rotatable.setDirection(rotatable.getDirection() + angularDelta);
    }
}
