package com.doeiqts.pizzavoter.domain;

import com.doeiqts.pizzavoter.enums.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    @Test
    public void testAddPizza_IncrementsVote() {
        Order order = new Order();

        Pizza pizza1 = new Pizza(Size.MEDIUM, Crust.HAND_TOSSED, Sauce.TOMATO);
        pizza1.addTopping(Topping.PEPPERONI, Position.ALL);
        pizza1.addTopping(Topping.BLACK_OLIVES,Position.ALL);

        Pizza pizza2 = new Pizza(Size.MEDIUM, Crust.HAND_TOSSED, Sauce.TOMATO);
        pizza2.addTopping(Topping.PEPPERONI, Position.ALL);
        pizza2.addTopping(Topping.BLACK_OLIVES,Position.ALL);

        order.addPizza(pizza1, "testUserName");
        order.addPizza(pizza2, "testUsername");

        assertEquals(1, order.size());
        assertEquals(2, order.getPizzas().get(pizza1).getCount().intValue());
    }
}
