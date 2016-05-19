package com.doeiqts.pizzavoter.domain;

import com.doeiqts.pizzavoter.enums.Crust;
import com.doeiqts.pizzavoter.enums.Position;
import com.doeiqts.pizzavoter.enums.Sauce;
import com.doeiqts.pizzavoter.enums.Size;
import com.doeiqts.pizzavoter.enums.Topping;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    @Test
    public void testAddTopping_ReturnsFalseAfterLimitReached() {
        Pizza pizza = new Pizza(Size.MEDIUM, Crust.HANDMADE_PAN, Sauce.TOMATO);

        pizza.addTopping(Topping.BACON, Position.ALL);
        pizza.addTopping(Topping.BANANA_PEPPERS, Position.ALL);
        boolean result = pizza.addTopping(Topping.BEEF, Position.ALL);

        assertFalse(result);
    }

    @Test
    public void testAddTopping_DoesNotAddMoreThanLimit() {
        Pizza pizza = new Pizza(Size.MEDIUM, Crust.HANDMADE_PAN, Sauce.TOMATO);

        pizza.addTopping(Topping.BACON, Position.ALL);
        pizza.addTopping(Topping.BANANA_PEPPERS, Position.LEFT);
        boolean result = pizza.addTopping(Topping.BEEF, Position.ALL);

        assertFalse(result);
    }

    @Test
    public void testAddTopping_DoesNotChangePizzaIfToppingsAboveLimit() {
        Pizza expectedPizza = new Pizza(Size.MEDIUM, Crust.HAND_TOSSED, Sauce.TOMATO);
        expectedPizza.addTopping(Topping.PEPPERONI, Position.ALL);
        expectedPizza.addTopping(Topping.BLACK_OLIVES,Position.LEFT);

        Pizza actualPizza = new Pizza(Size.MEDIUM, Crust.HAND_TOSSED, Sauce.TOMATO);
        actualPizza.addTopping(Topping.PEPPERONI,Position.ALL);
        actualPizza.addTopping(Topping.BLACK_OLIVES,Position.LEFT);
        actualPizza.addTopping(Topping.BACON, Position.ALL);

        assertEquals(expectedPizza, actualPizza);
    }

    @Test
    public void testGetToppingCount_Returns_Zero() {
        Pizza pizza = new Pizza(Size.MEDIUM, Crust.HANDMADE_PAN, Sauce.TOMATO);

        assertEquals(0, pizza.getToppingCount(), 0);
    }

    @Test
    public void testGetToppingCount_Returns_One() {
        Pizza pizza = new Pizza(Size.MEDIUM, Crust.HANDMADE_PAN, Sauce.TOMATO);

        pizza.addTopping(Topping.BACON, Position.ALL);

        assertEquals(1, pizza.getToppingCount(), 0);
    }

    @Test
    public void testGetToppingCount_Returns_One_Five() {
        Pizza pizza = new Pizza(Size.MEDIUM, Crust.HANDMADE_PAN, Sauce.TOMATO);

        pizza.addTopping(Topping.BACON, Position.ALL);
        pizza.addTopping(Topping.BANANA_PEPPERS, Position.LEFT);

        assertEquals(1.5, pizza.getToppingCount(), 0);
    }

    @Test
    public void testGetToppingCount_Returns_Two() {
        Pizza pizza = new Pizza(Size.MEDIUM, Crust.HANDMADE_PAN, Sauce.TOMATO);

        pizza.addTopping(Topping.BACON, Position.ALL);
        pizza.addTopping(Topping.BANANA_PEPPERS, Position.ALL);
        pizza.addTopping(Topping.BEEF, Position.ALL);

        assertEquals(2, pizza.getToppingCount(), 0);
    }
}
