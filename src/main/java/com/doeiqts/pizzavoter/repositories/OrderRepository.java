package com.doeiqts.pizzavoter.repositories;

import com.doeiqts.pizzavoter.domain.Order;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class OrderRepository {
    public static Order loadOrder(String partyName) {
        Order order;
        order = ofy().load().type(Order.class).id(partyName).now();

        if (order == null) {
            order = new Order(partyName);
        }

        return order;
    }

    public static void saveOrder(Order order) {
        ofy().save().entity(order).now();
    }
}
