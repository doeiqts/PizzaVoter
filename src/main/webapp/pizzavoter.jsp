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

    <p><b>Hello ${name}, submit the pizzas you want to vote for:</b></p>
    <br>

    <form action="/" method="post">
        Size: <select name="size">
              <c:forEach var="size" items="${sizes}">
                <option value="${size}">${size}</option>
              </c:forEach>
              </select>
              <br>
        Crust:
        <p>
            <input type="submit" value="Vote for these pizzas">
        </p>
    </form>
    <br>

    <b>Current pizzas being voted on:</b>
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

</body>
</html>