package com.doeiqts.pizzavoter.domain;

import com.doeiqts.pizzavoter.enums.Crust;
import com.doeiqts.pizzavoter.enums.Position;
import com.doeiqts.pizzavoter.enums.Sauce;
import com.doeiqts.pizzavoter.enums.Size;
import com.doeiqts.pizzavoter.enums.Topping;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PizzaTest {

    @Test
    public void testEquals_TwoPizzaObjectsEqual() {
        Pizza pizza1 = new Pizza(Size.MEDIUM, Crust.HAND_TOSSED, Sauce.TOMATO);
        pizza1.addTopping(Topping.PEPPERONI, Position.ALL);
        pizza1.addTopping(Topping.BLACK_OLIVES,Position.ALL);

        Pizza pizza2 = new Pizza(Size.MEDIUM, Crust.HAND_TOSSED, Sauce.TOMATO);
        pizza2.addTopping(Topping.PEPPERONI,Position.ALL);
        pizza2.addTopping(Topping.BLACK_OLIVES,Position.ALL);

        assertTrue(pizza1.equals(pizza2));
    }
}
