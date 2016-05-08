package com.doeiqts.pizzavoter.enums;

public enum Topping {
    CHEESE("Cheese"),
    PEPPERONI("Pepperoni"),
    SAUSAGE("Sausage"),
    BEEF("Beef"),
    PHILLY_STEAK("Philly Steak"),
    HAM("Ham"),
    BACON("Bacon"),
    CHICKEN("Chicken"),
    CHEDDAR_CHEESE("Cheddar Cheese"),
    FETA_CHEESE("Feta Cheese"),
    PARMESAN_ASIAGO("Parmesan Asiago"),
    PROVOLONE_CHEESE("Provolone Cheese"),
    BANANA_PEPPERS("Banana Peppers"),
    BLACK_OLIVES("Black Olives"),
    GREEN_OLIVES("Green Olives"),
    GREEN_PEPPERS("Green Peppers"),
    JALAPENO_PEPPERS("Jalapeno Peppers"),
    MUSHROOMS("Mushrooms"),
    PINEAPPLE("Pineapple"),
    ONIONS("Onions"),
    RED_PEPPERS("Red Peppers"),
    SPINACH("Spinach"),
    DICED_TOMATOES("Diced Tomatoes");

    private String type;

    Topping(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
