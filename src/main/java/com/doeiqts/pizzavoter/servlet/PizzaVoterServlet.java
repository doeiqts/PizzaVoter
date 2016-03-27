package com.doeiqts.pizzavoter.servlet;

import com.doeiqts.pizzavoter.domain.Order;
import com.doeiqts.pizzavoter.enums.Crust;
import com.doeiqts.pizzavoter.domain.Pizza;
import com.doeiqts.pizzavoter.enums.Sauce;
import com.doeiqts.pizzavoter.enums.Size;
import com.doeiqts.pizzavoter.enums.Topping;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PizzaVoterServlet extends HttpServlet {
    private Order currentOrder = new Order();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        User currentUser = userService.getCurrentUser();

        if (currentUser != null) {
            request.setAttribute("name", currentUser.getNickname());
            request.setAttribute("sizes", Size.values());
            request.setAttribute("pizzas", currentOrder.getPizzas());

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

        if (currentUser != null) {
            request.setAttribute("name", currentUser.getNickname());
            request.setAttribute("sizes", Size.values());

            Pizza pizza1 = new Pizza(Crust.HANDMADE_PAN, Size.valueOf(request.getParameter("size")), Sauce.TOMATO);
            pizza1.addTopping(Topping.PEPPERONI);
            pizza1.addTopping(Topping.BLACK_OLIVES);

            Pizza pizza2 = new Pizza(Crust.HANDMADE_PAN, Size.valueOf(request.getParameter("size")), Sauce.TOMATO);
            pizza2.addTopping(Topping.SAUSAGE);
            pizza2.addLeftTopping(Topping.BACON);
            pizza2.addRightTopping(Topping.CHICKEN);

            currentOrder.addPizza(pizza1);
            currentOrder.addPizza(pizza2);

            request.setAttribute("pizzas", currentOrder.getPizzas());

            try{
                request.getRequestDispatcher("/pizzavoter.jsp").forward(request, response);
            } catch (ServletException e) {
                response.getWriter().println(e);
            }
        } else {
            response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
        }
    }
}
