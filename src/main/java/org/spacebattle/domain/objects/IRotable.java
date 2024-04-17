package org.spacebattle.domain.objects;

public interface IRotable {
    int getDirection();
    int getAngularVelocity();
    int getDirectionsNumber();

    void setDirection(int newV);
}
