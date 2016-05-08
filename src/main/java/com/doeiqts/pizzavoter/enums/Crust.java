package com.doeiqts.pizzavoter.enums;

public enum Crust {
    HAND_TOSSED("Hand Tossed"),
    HANDMADE_PAN("Handmade Pan"),
    THIN("Thin");

    private String type;

    Crust(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
