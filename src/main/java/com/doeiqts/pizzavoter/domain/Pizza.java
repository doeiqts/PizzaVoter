package com.doeiqts.pizzavoter.domain;

import com.doeiqts.pizzavoter.enums.Crust;
import com.doeiqts.pizzavoter.enums.Position;
import com.doeiqts.pizzavoter.enums.Sauce;
import com.doeiqts.pizzavoter.enums.Size;
import com.doeiqts.pizzavoter.enums.Topping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Pizza {
    private static final double LIMIT = 3.0; //effectively 2 but cheese is on everything...
    private Size size;
    private Crust crust;
    private Sauce sauce;
    private double toppingCount;

    private Map<Topping, Position> toppings = new TreeMap<>();


    public Pizza(Size size, Crust crust, Sauce sauce) {
        this.crust = crust;
        this.size = size;
        this.sauce = sauce;
        toppings.put(Topping.CHEESE, Position.ALL);
        //it's effectively 0 but cheese is on everything...not sure how I feel about this...
        toppingCount = 1.0;
    }

    public Position addTopping(Topping topping, Position position, HttpServletRequest request) {
        double old = 0.0; //if duplicate topping positions are sent need to subtract existing topping value)
        Position existing = this.toppings.get(topping);
        if(null != existing) {
            old = existing.getValue();
        }
        if((this.getToppingCount() - old + position.getValue()) <= LIMIT) {
            toppingCount = toppingCount - old + position.getValue();
            return this.toppings.put(topping, position);
        } else {
            request.setAttribute("limitExceeded", true);
        }
        return null;
    }

    public void addAllToppings(String pizzaNumber,HttpServletRequest request) {
        String[] toppings = request.getParameterValues("toppings" + pizzaNumber);
        for (int i = 0; i < toppings.length; i++) {
            if (toppings[i] != "") {
                String[] positions = request.getParameterValues("position" + pizzaNumber + "-"+(i+1));
                if(null != positions) {
                    Position newPosition = Position.valueOf(positions[0]);
                    Position old = this.addTopping(Topping.valueOf(toppings[i]), newPosition, request);
                    if (Position.ALL.equals(old) || (Position.RIGHT.equals(old) && newPosition.equals(Position.LEFT)) ||
                            (Position.LEFT.equals(old) && newPosition.equals(Position.RIGHT))) {
                        this.addTopping(Topping.valueOf(toppings[i]), Position.ALL, request);
                    }
                }
            }
        }
    }

    public static double getToppingLimit() {
        return LIMIT;
    }

    public double getToppingCount() { return toppingCount; }

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

    public String getToppingsJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(toppings);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public List<Topping> getToppingList() {
        return new ArrayList<>(toppings.keySet());
    }

    public List<Position> getPositionList() {
        return new ArrayList<>(toppings.values());
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
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
