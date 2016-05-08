package com.doeiqts.pizzavoter.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private Map<Pizza, Vote> pizzas = new LinkedHashMap<>();

    public Map<Pizza, Vote> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza, String username) {
        Vote vote = pizzas.get(pizza);
        if (vote == null) {
            pizzas.put(pizza, new Vote(username));
        } else {
            pizzas.put(pizza, vote.addToVote(username));
        }
    }

    public void removePizza(Pizza pizza, String username) {
        Vote vote = pizzas.get(pizza);

        if(null != vote) {
            if (vote.getCount() == 1) {
                pizzas.remove(pizza);
            } else if (vote.getCount() > 1) {
                pizzas.put(pizza, vote.removeVote(username));
            }
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
