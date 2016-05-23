package com.doeiqts.pizzavoter.servlet;

import com.doeiqts.pizzavoter.domain.Order;
import com.doeiqts.pizzavoter.domain.Pizza;
import com.doeiqts.pizzavoter.enums.Crust;
import com.doeiqts.pizzavoter.enums.Position;
import com.doeiqts.pizzavoter.enums.Sauce;
import com.doeiqts.pizzavoter.enums.Size;
import com.doeiqts.pizzavoter.enums.Topping;
import com.doeiqts.pizzavoter.repositories.OrderRepository;
import com.doeiqts.pizzavoter.util.MapUtil;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PizzaVoterServlet extends HttpServlet {
    private Map<String, Set<Pizza>> individualOrders = new HashMap<>();

    static {
        ObjectifyService.register(Order.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User currentUser = userService.getCurrentUser();
        Order currentOrder = OrderRepository.loadOrder("The Fangs of Phandalin");

        if (currentUser != null) {
            // Check for clear command
            if ("doeiqts".equals(currentUser.getNickname())) {
                clearVotesForUser(request.getParameter("username"), currentOrder);
            }

            setCommonRequestVariables(request, currentUser, currentOrder);

            try{
                request.getRequestDispatcher("/pizzavoter.jsp").forward(request, response);
            } catch (ServletException e) {
                response.getWriter().println(e);
            }
        } else {
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User currentUser = userService.getCurrentUser();
        Order currentOrder = OrderRepository.loadOrder("The Fangs of Phandalin");

        if (currentUser != null) {
            Set<Pizza> pizzaSet = new LinkedHashSet<>();

            // Get the user inputted pizzas
            Pizza pizza1 = serializePizza(request, "1");
            if (pizza1 != null) {
                pizzaSet.add(pizza1);
            }

            Pizza pizza2 = serializePizza(request, "2");
            if (pizza2 != null) {
                pizzaSet.add(pizza2);
            }

            Pizza pizza3 = serializePizza(request, "3");
            if (pizza3 != null) {
                pizzaSet.add(pizza3);
            }

            Pizza pizza4 = serializePizza(request, "4");
            if (pizza4 != null) {
                pizzaSet.add(pizza4);
            }

            // Remove any previously voted on pizzas from this user.
            clearVotesForUser(currentUser.getNickname(), currentOrder);

            // Save the users new votes.
            if (pizzaSet.isEmpty()) {
                individualOrders.remove(currentUser.getNickname());
            } else {
                individualOrders.put(currentUser.getNickname(), pizzaSet);
            }

            // Add the votes to the current order.
            for (Pizza pizza : pizzaSet) {
                currentOrder.addPizza(pizza, currentUser.getNickname());
            }
            OrderRepository.saveOrder(currentOrder);

            setCommonRequestVariables(request, currentUser, currentOrder);

            try{
                request.getRequestDispatcher("/pizzavoter.jsp").forward(request, response);
            } catch (ServletException e) {
                response.getWriter().println(e);
            }
        } else {
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        }
    }

    private void setCommonRequestVariables(HttpServletRequest request, User currentUser, Order currentOrder) {
        request.setAttribute("name", currentUser.getNickname());
        request.setAttribute("crusts", Crust.values());
        request.setAttribute("sauces", Sauce.values());
        request.setAttribute("limit", Pizza.getToppingLimit());

        // Since cheese is defaulted, don't let it be a choice for the toppings.
        EnumSet<Topping> toppings = EnumSet.allOf(Topping.class);
        toppings.remove(Topping.CHEESE);
        request.setAttribute("toppings", toppings);
        request.setAttribute("pizzas", MapUtil.sortByValue(currentOrder.getPizzas(), true));
        request.setAttribute("voters", individualOrders.keySet());

        List<Pizza> userPizzas = null;
        if (individualOrders.get(currentUser.getNickname()) != null) {
            userPizzas = new ArrayList<>(individualOrders.get(currentUser.getNickname()));
        }

        request.setAttribute("userPizzas", userPizzas);
    }

    private Pizza serializePizza(HttpServletRequest request, String pizzaNumber) {
        try {
            // Hardcoding the size for now. Focusing on the pizza itself.
            Pizza pizza = new Pizza(Size.MEDIUM,
                    Crust.valueOf(request.getParameter("crust" + pizzaNumber)),
                    Sauce.valueOf(request.getParameter("sauce" + pizzaNumber)));

            String[] toppings = request.getParameterValues("toppings" + pizzaNumber);
            for (int i = 0; i < toppings.length; i++) {
                String position = request.getParameter("position" + pizzaNumber + "-"+(i+1));
                if (position != null && toppings[i] != "") {
                    if(!pizza.addTopping(Topping.valueOf(toppings[i]), Position.valueOf(position))) {
                        request.setAttribute("limitExceeded", true);
                    }
                }
            }

            return pizza;
        } catch (IllegalArgumentException e) {
            // Missing pizza parameters
            return null;
        }
    }

    private void clearVotesForUser(String username, Order currentOrder) {
        Set<Pizza> pizzasToRemove = individualOrders.get(username);

        if (pizzasToRemove != null) {
            for (Pizza pizza : pizzasToRemove) {
                currentOrder.removePizza(pizza, username);
            }

            individualOrders.remove(username);
        }
    }
}
