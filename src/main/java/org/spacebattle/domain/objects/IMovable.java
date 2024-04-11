package org.spacebattle.domain.objects;

import java.util.Vector;

public interface IMovable {
    Vector<Double> getLocation();
    Vector<Double> getVelocity();
    void setLocation(Vector<Double> location);
}
