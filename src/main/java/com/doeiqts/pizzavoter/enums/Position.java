package com.doeiqts.pizzavoter.enums;

public enum Position {
    LEFT(0.5),
    ALL(1.0),
    RIGHT(0.5);

    private double value;

    Position(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }
}
