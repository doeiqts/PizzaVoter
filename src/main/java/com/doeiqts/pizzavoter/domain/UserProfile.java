package com.doeiqts.pizzavoter.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class UserProfile {
    @Id
    private String username;
    private Map<String, Set<Pizza>> partyPizzas = new HashMap<>();

    public UserProfile() {
        // Needed for Objectify
    }

    public UserProfile(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Set<Pizza> setPartyPizzas(String partyName, Set<Pizza> pizzas) {
        return partyPizzas.put(partyName, pizzas);
    }

    public Set<Pizza> getPartyPizzas(String partyName) {
        return partyPizzas.get(partyName);
    }

    public Set<Pizza> removePartyPizzas(String partyName) {
        return partyPizzas.remove(partyName);
    }
}
