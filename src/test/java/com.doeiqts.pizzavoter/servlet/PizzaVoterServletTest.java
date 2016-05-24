package com.doeiqts.pizzavoter.servlet;

import com.doeiqts.pizzavoter.repositories.OrderRepository;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ User.class, UserServiceFactory.class })
public class PizzaVoterServletTest {

    @Test
    @Ignore
    public void testDoGet_AddsPizzasToOrder() throws Exception {
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);

        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        Mockito.when(httpServletRequest.getRequestDispatcher(Mockito.anyString())).thenReturn(requestDispatcher);

        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

        User user = PowerMockito.mock(User.class);

        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        PowerMockito.mockStatic(UserServiceFactory.class);
        PowerMockito.when(UserServiceFactory.getUserService()).thenReturn(userService);


        // From here on down it doesn't seem this test was finished.
        // TODO Finish or remove this test.
        PowerMockito.mockStatic(OrderRepository.class);

        PizzaVoterServlet pizzaVoterServlet = new PizzaVoterServlet();
        pizzaVoterServlet.doGet(httpServletRequest, httpServletResponse);
        pizzaVoterServlet.doGet(httpServletRequest, httpServletResponse);
    }
}
