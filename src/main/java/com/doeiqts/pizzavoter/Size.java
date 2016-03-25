package com.doeiqts.pizzavoter;

public enum Size {
    SMALL(10),
    MEDIUM(12),
    LARGE(14),
    X_LARGE(16);

    private int value;

    private Size(int value) {
        this.value = value;
    }
}
