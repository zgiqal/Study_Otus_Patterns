package org.spacebattle.domain.actions.impl;

import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.objects.IMovable;
import org.spacebattle.domain.objects.IVelocityChanging;

import java.util.Vector;

public class ChangeVelocityCommand implements ICommand {
    private final Vector<Double> velocity;
    private final IVelocityChanging obj;

    public ChangeVelocityCommand(Vector<Double> velocity, IMovable movable) {
        if (movable == null) {
            throw new IllegalArgumentException("Movable object is null");
        }
        if (movable instanceof IVelocityChanging velocityChanging) {
            this.obj = velocityChanging;
        } else {
            throw new IllegalArgumentException("Try to change velocity of no velocity changing movable: " + movable);
        }

        if (velocity.size() != movable.getVelocity().size()) {
            throw new IllegalArgumentException("Wrong dimension of new and old velocity: "
                    +  velocity
                    + ", " + movable.getVelocity()
            );
        }

        this.velocity = velocity;
    }

    public void execute() {
        obj.setVelocity(velocity);
    }
}
