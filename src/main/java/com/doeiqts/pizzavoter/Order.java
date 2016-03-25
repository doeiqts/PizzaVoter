package com.doeiqts.pizzavoter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private Map<Pizza, AtomicInteger> pizzas = new HashMap<>();

    public void addPizza(Pizza pizza) {
        pizzas.putIfAbsent(pizza, new AtomicInteger(0));
        pizzas.get(pizza).incrementAndGet();
    }

    public int size() {
        return pizzas.size();
    }

    @Override
    public String toString() {
        String result = "";

        for(Pizza pizza : pizzas.keySet()) {
            result += pizza.toString() + "\n";
        }

        return result;
    }
}
