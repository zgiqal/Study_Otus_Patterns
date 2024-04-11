package org.spacebattle.adapters;

import lombok.AllArgsConstructor;
import org.spacebattle.domain.objects.IMovable;
import org.spacebattle.domain.objects.UObject;

import java.util.List;
import java.util.Vector;

@AllArgsConstructor
public class MovableAdapter implements IMovable {
    private UObject o;

    @Override
    // TODO
    @SuppressWarnings("unchecked")
    public Vector<Double> getLocation() {
        return (Vector<Double>) o.getProperty("Position");
    }

    @Override
    public Vector<Double> getVelocity() {
        int d = (int) o.getProperty("Direction");
        int n = (int) o.getProperty("DirectionsNumber");
        int v = (int) o.getProperty("Velocity");

        return new Vector<>(List.of(v * Math.cos((double) d / 360 * n), v * Math.sin((double) d / 360 * n)));
    }

    @Override
    public void setLocation(Vector<Double> location) {
        o.setProperty("Position", location);
    }
}
