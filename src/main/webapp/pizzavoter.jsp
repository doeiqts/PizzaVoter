<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

    <p><b>Hello ${name}, submit the pizzas you want to vote for</b></p>

    <p><b>Current pizzas being voted on:</b>
        <c:forEach var="pizza" items="${pizzas}">
            <p>
                Votes: ${pizza.value}<br>
                Size: ${pizza.key.size}<br>
                Crust: ${pizza.key.crust}<br>
                Sauce: ${pizza.key.sauce}<br>
                Toppings (right side): ${pizza.key.rightToppings}<br>
                Toppings (left side): ${pizza.key.leftToppings}<br>
            </p>
        </c:forEach>

        <c:if test="${empty pizzas}">
            <p>
                No pizzas have been voted on yet.
            </p>
        </c:if>
    </p>

    <form action="/" method="post">
    <c:forEach begin="0" end="3" varStatus="pizza">
        <p>
            Size:   <select name="size${pizza.count}">
                        <option value=""></option>
                    <c:forEach var="size" items="${sizes}">
                        <option value="${size}">${size}</option>
                    </c:forEach>
                    </select>
                    <br>

            Crust:  <select name="crust${pizza.count}">
                        <option value=""></option>
                    <c:forEach var="crust" items="${crusts}">
                        <option value="${crust}">${crust}</option>
                    </c:forEach>
                    </select>
                    <br>

            Sauce:  <select name="sauce${pizza.count}">
                        <option value=""></option>
                    <c:forEach var="sauce" items="${sauces}">
                        <option value="${sauce}">${sauce}</option>
                    </c:forEach>
                    </select>
                    <br>

            <c:forEach begin="0" end="3" varStatus="topping">
            Right Topping:  <select name="rightToppings${pizza.count}">
                               <option value=""></option>
                            <c:forEach var="topping" items="${toppings}">
                               <option value="${topping}">${topping}</option>
                            </c:forEach>
                            </select>
                            <br>
            </c:forEach>

            <c:forEach begin="0" end="3" varStatus="topping">
            Left Topping:   <select name="leftToppings${pizza.count}">
                                <option value=""></option>
                            <c:forEach var="topping" items="${toppings}">
                                <option value="${topping}">${topping}</option>
                            </c:forEach>
                            </select>
                            <br>
            </c:forEach>
        </p>
    </c:forEach>
        <p>
            <input type="submit" value="Vote for these pizzas!">
        </p>
    </form>
    <br>
</body>
</html>