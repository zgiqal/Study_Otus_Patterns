package org.spacebattle.domain.actions.impl;

import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.domain.objects.IFuelConsuming;
import org.spacebattle.exceptions.NotEnoughFuel;

public class CheckFuelCommand implements ICommand {
    private final long unit;
    private final IFuelConsuming obj;

    public CheckFuelCommand(long fuelConsumptionUnit, IFuelConsuming fuelConsumingObject) {
        if (fuelConsumptionUnit < 0) throw new IllegalArgumentException("Fuel consumption < 0");
        if (fuelConsumingObject == null) throw new IllegalArgumentException("Fuel consumption object is null");

        this.unit = fuelConsumptionUnit;
        this.obj = fuelConsumingObject;
    }

    public void execute() throws NotEnoughFuel {
        if (obj.getFuelAmount() < unit) {
            throw new NotEnoughFuel();
        }
    }
}
