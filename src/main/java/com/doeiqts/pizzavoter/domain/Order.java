package com.doeiqts.pizzavoter.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private Map<Pizza, Integer> pizzas = new HashMap<>();

    public Map<Pizza, Integer> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza) {
        Integer voteCount = pizzas.get(pizza);

        if (voteCount == null) {
            pizzas.put(pizza, 1);
        } else {
            pizzas.put(pizza, voteCount + 1);
        }
    }

    public int size() {
        return pizzas.size();
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
