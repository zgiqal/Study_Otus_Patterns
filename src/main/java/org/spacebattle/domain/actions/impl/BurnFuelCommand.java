package org.spacebattle.domain.actions.impl;

import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.objects.IFuelConsuming;

public class BurnFuelCommand implements ICommand {
    private final long unit;
    private final IFuelConsuming obj;

    public BurnFuelCommand(long fuelBurningUnit, IFuelConsuming fuelBurningObject) {
        if (fuelBurningUnit < 0) throw new IllegalArgumentException("Fuel burning < 0");
        if (fuelBurningObject == null) throw new IllegalArgumentException("Fuel burn object is null");

        this.unit = fuelBurningUnit;
        this.obj = fuelBurningObject;
    }

    public void execute() {
        obj.setFuelAmount(obj.getFuelAmount() - unit);
    }
}
