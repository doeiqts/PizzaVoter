package com.doeiqts.pizzavoter.enums;

/**
 * Created by mitchdtop on 5/4/2016.
 */
public enum Position {
    LEFT(0),
    ALL(1),
    RIGHT(2);

    private int value;

    Position(int value) {
        this.value = value;
    }
}
