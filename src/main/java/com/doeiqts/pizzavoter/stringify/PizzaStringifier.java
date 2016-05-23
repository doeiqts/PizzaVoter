package com.doeiqts.pizzavoter.stringify;

import com.doeiqts.pizzavoter.domain.Pizza;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.objectify.stringifier.Stringifier;

import java.io.IOException;

public class PizzaStringifier implements Stringifier<Pizza> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String toString(Pizza obj) {
        return obj.toString();
    }

    @Override
    public Pizza fromString(String str) {
        try {
            return OBJECT_MAPPER.readValue(str, Pizza.class);
        } catch (IOException e) {
            // Should probably log something here.
            String test = "";

            return null;
        }
    }
}
