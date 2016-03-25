/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.appengine.helloworld;

import com.doeiqts.pizzavoter.Crust;
import com.doeiqts.pizzavoter.Pizza;
import com.doeiqts.pizzavoter.Sauce;
import com.doeiqts.pizzavoter.Size;
import com.doeiqts.pizzavoter.Topping;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// [START example]
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      UserService userService = UserServiceFactory.getUserService();
      User currentUser = userService.getCurrentUser();

      if (currentUser != null) {
          request.setAttribute("name", currentUser.getNickname());

          Pizza pizza1 = new Pizza(Crust.HANDMADE_PAN, Size.MEDIUM, Sauce.TOMATO);
          pizza1.addTopping(Topping.PEPPERONI);
          pizza1.addTopping(Topping.BLACK_OLIVES);

          Pizza pizza2 = new Pizza(Crust.HANDMADE_PAN, Size.MEDIUM, Sauce.TOMATO);
          pizza2.addTopping(Topping.SAUSAGE);
          pizza2.addLeftTopping(Topping.BACON);
          pizza2.addRightTopping(Topping.CHICKEN);

          List<Pizza> pizzas = new ArrayList<>(2);
          pizzas.add(pizza1);
          pizzas.add(pizza2);

          request.setAttribute("pizzas", pizzas);

          try{
              request.getRequestDispatcher("/helloworld.jsp").forward(request, response);
          } catch (ServletException e) {
              response.getWriter().println(e);
          }
      } else {
          response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
      }
    }
}
// [END example]
