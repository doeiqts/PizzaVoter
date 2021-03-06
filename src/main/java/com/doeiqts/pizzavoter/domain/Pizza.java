package com.doeiqts.pizzavoter.domain;

import com.doeiqts.pizzavoter.enums.Crust;
import com.doeiqts.pizzavoter.enums.Position;
import com.doeiqts.pizzavoter.enums.Sauce;
import com.doeiqts.pizzavoter.enums.Size;
import com.doeiqts.pizzavoter.enums.Topping;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.TreeMap;

public class Pizza {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final double LIMIT = 2.0;

    private Size size;
    private Crust crust;
    private Sauce sauce;
    private Map<Topping, Position> toppings = new TreeMap<>();

    public Pizza() {
        // Needed for Objectify
    }

    public Pizza(Size size, Crust crust, Sauce sauce) {
        this.crust = crust;
        this.size = size;
        this.sauce = sauce;
        toppings.put(Topping.CHEESE, Position.ALL);
    }

    public boolean addTopping(Topping topping, Position position) {
        if (toppingCount() < LIMIT) {
            toppings.put(topping, position.combinePositions(toppings.get(topping)));

            if (toppingCount() > LIMIT) {
                toppings.remove(topping);
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public static double getToppingLimit() {
        return LIMIT;
    }

    public double toppingCount() {
        double toppingCount = 0.0;

        for (Map.Entry<Topping, Position> entry : toppings.entrySet()) {
            if (entry.getKey() != Topping.CHEESE) {
                toppingCount += entry.getValue().getValue();
            }
        }

        return toppingCount;
    }

    public Size getSize() {
        return size;
    }

    public Crust getCrust() {
        return crust;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public Map<Topping, Position> getToppings() {
        return toppings;
    }

    @JsonIgnore
    public String getToppingsJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(toppings);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (size != pizza.size) return false;
        if (crust != pizza.crust) return false;
        if (sauce != pizza.sauce) return false;
        return toppings != null ? toppings.equals(pizza.toppings) : pizza.toppings == null;

    }

    @Override
    public int hashCode() {
        int result = size != null ? size.hashCode() : 0;
        result = 31 * result + (crust != null ? crust.hashCode() : 0);
        result = 31 * result + (sauce != null ? sauce.hashCode() : 0);
        result = 31 * result + (toppings != null ? toppings.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
