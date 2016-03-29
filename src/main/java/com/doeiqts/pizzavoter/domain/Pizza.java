package com.doeiqts.pizzavoter.domain;

import com.doeiqts.pizzavoter.enums.Crust;
import com.doeiqts.pizzavoter.enums.Sauce;
import com.doeiqts.pizzavoter.enums.Size;
import com.doeiqts.pizzavoter.enums.Topping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.EnumSet;

public class Pizza {
    private Size size;
    private Crust crust;
    private Sauce sauce;

    private EnumSet<Topping> rightToppings = EnumSet.of(Topping.CHEESE);
    private EnumSet<Topping> leftToppings = EnumSet.of(Topping.CHEESE);

    public Pizza(Size size, Crust crust, Sauce sauce) {
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

    public Size getSize() {
        return size;
    }

    public Crust getCrust() {
        return crust;
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
    public int hashCode() {
        int result = crust != null ? crust.hashCode() : 0;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (sauce != null ? sauce.hashCode() : 0);
        result = 31 * result + (rightToppings != null ? rightToppings.hashCode() : 0);
        result = 31 * result + (leftToppings != null ? leftToppings.hashCode() : 0);
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
