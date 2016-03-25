package com.doeiqts.pizzavoter;

import org.junit.Test;

public class PizzaTest {

    @Test
    public void testToString_JsonOutput() {
        Pizza pizza1 = new Pizza(Crust.HAND_TOSSED, Size.MEDIUM, Sauce.TOMATO);
        pizza1.addTopping(Topping.PEPPERONI);
        pizza1.addTopping(Topping.BLACK_OLIVES);

        pizza1.toString();
    }
}
