package com.doeiqts.pizzavoter.domain;

import com.doeiqts.pizzavoter.enums.Crust;
import com.doeiqts.pizzavoter.enums.Sauce;
import com.doeiqts.pizzavoter.enums.Size;
import com.doeiqts.pizzavoter.enums.Topping;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    @Test
    public void testAddPizza_IncrementsVote() {
        Order order = new Order();

        Pizza pizza1 = new Pizza(Size.MEDIUM, Crust.HAND_TOSSED, Sauce.TOMATO);
        pizza1.addTopping(Topping.PEPPERONI);
        pizza1.addTopping(Topping.BLACK_OLIVES);

        Pizza pizza2 = new Pizza(Size.MEDIUM, Crust.HAND_TOSSED, Sauce.TOMATO);
        pizza2.addTopping(Topping.PEPPERONI);
        pizza2.addTopping(Topping.BLACK_OLIVES);

        order.addPizza(pizza1);
        order.addPizza(pizza2);

        assertEquals(1, order.size());
        assertEquals(2, order.getPizzas().get(pizza1).intValue());
    }
}
