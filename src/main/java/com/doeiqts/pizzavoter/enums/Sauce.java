package com.doeiqts.pizzavoter.enums;

public enum Sauce {
    TOMATO("Tomato"),
    MARINARA("Marinara"),
    BBQ("BBQ"),
    GARLIC_PARMESAN_WHITE("White Garlic Parmesan"),
    ALFREDO("Alfredo");

    private String type;

    Sauce(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
