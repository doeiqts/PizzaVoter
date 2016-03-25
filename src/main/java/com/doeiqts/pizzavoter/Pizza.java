package com.doeiqts.pizzavoter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.EnumSet;

public class Pizza {
    private Crust crust;
    private Size size;
    private Sauce sauce;

    private EnumSet<Topping> rightToppings = EnumSet.of(Topping.CHEESE);
    private EnumSet<Topping> leftToppings = EnumSet.of(Topping.CHEESE);

    public Pizza(Crust crust, Size size, Sauce sauce) {
        this.crust = crust;
        this.size = size;
        this.sauce = sauce;
    }

    public void addRightTopping(Topping topping) {
        this.rightToppings.add(topping);
    }

    public void addLeftTopping(Topping topping) {
        this.leftToppings.add(topping);
    }

    public void addTopping(Topping topping) {
        addRightTopping(topping);
        addLeftTopping(topping);
    }

    public double countToppings() {
        // Total toppings on the right side minus the default cheese.
        double rightToppingsCount = rightToppings.size() - 1;

        // Total topping on the left side minus the default cheese.
        double leftToppingsCount = leftToppings.size() - 1;

        // Each side only counts as half a topping, so cut the total count in half.
        return (rightToppingsCount + leftToppingsCount) / 2;
    }

    public Crust getCrust() {
        return crust;
    }

    public Size getSize() {
        return size;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public EnumSet<Topping> getRightToppings() {
        return rightToppings;
    }

    public EnumSet<Topping> getLeftToppings() {
        return leftToppings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (crust != pizza.crust) return false;
        if (size != pizza.size) return false;
        if (sauce != pizza.sauce) return false;
        if (!rightToppings.equals(pizza.rightToppings)) return false;
        return leftToppings.equals(pizza.leftToppings);

    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
