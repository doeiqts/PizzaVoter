package com.doeiqts.pizzavoter.repositories;

import com.doeiqts.pizzavoter.domain.Pizza;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class PizzaRepository {

    public static void savePizza(Pizza pizza) {
        ofy().save().entity(pizza).now();
    }
}
