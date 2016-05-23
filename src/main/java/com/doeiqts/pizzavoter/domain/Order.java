package com.doeiqts.pizzavoter.domain;

import com.doeiqts.pizzavoter.stringify.PizzaStringifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Stringify;

import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Order {
    @Id
    private String partyName;
    @Stringify(PizzaStringifier.class)
    private Map<Pizza, Vote> pizzas = new LinkedHashMap<>();

    public Order() {
        // Needed for Objectify
    }

    public Order(String partyName) {
        this.partyName = partyName;
    }

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
