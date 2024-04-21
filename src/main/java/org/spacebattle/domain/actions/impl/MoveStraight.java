package org.spacebattle.domain.actions.impl;

import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.objects.IFuelConsuming;
import org.spacebattle.domain.objects.IMovable;
import org.spacebattle.domain.objects.UObject;

public class MoveStraight implements ICommand {
    private final long unit;
    private final UObject obj;

    public MoveStraight(long fuelMovingUnit, UObject movable) {
        if (movable == null) {
            throw new IllegalArgumentException("Movable object is null");
        }
        if (!(movable instanceof IMovable)) {
            throw new IllegalArgumentException("Try to move not movable object: " + movable);
        }
        if (!(movable instanceof IFuelConsuming)) {
            throw new IllegalArgumentException("Try to move with consuming fuel not fuel consuming object: " + movable);
        }

        if (fuelMovingUnit < 0) throw new IllegalArgumentException("Fuel consuming < 0");

        this.obj = movable;
        this.unit = fuelMovingUnit;
    }

    public void execute() throws Exception {
        new CheckFuelCommand(unit, (IFuelConsuming) obj).execute();
        new BurnFuelCommand(unit, (IFuelConsuming) obj).execute();
        new Move((IMovable) obj).execute();
    }
}
