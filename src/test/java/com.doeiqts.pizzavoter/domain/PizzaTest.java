package com.doeiqts.pizzavoter.domain;

import com.doeiqts.pizzavoter.enums.Crust;
import com.doeiqts.pizzavoter.enums.Sauce;
import com.doeiqts.pizzavoter.enums.Size;
import com.doeiqts.pizzavoter.enums.Topping;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PizzaTest {

    @Test
    public void testEquals_TwoPizzaObjectsEqual() {
        Pizza pizza1 = new Pizza(Size.MEDIUM, Crust.HAND_TOSSED, Sauce.TOMATO);
        pizza1.addTopping(Topping.PEPPERONI);
        pizza1.addTopping(Topping.BLACK_OLIVES);

        Pizza pizza2 = new Pizza(Size.MEDIUM, Crust.HAND_TOSSED, Sauce.TOMATO);
        pizza2.addTopping(Topping.PEPPERONI);
        pizza2.addTopping(Topping.BLACK_OLIVES);

        assertTrue(pizza1.equals(pizza2));
    }
}
