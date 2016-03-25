<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

</head>
<body>

<p>Hello ${name} and stuff!</p>
<br>

<c:forEach var="pizza" items="${pizzas}">
    <p>
        Size: ${pizza.size}<br>
        Crust: ${pizza.crust}<br>
        Sauce: ${pizza.sauce}<br>
        Toppings (right side): ${pizza.rightToppings}<br>
        Toppings (left side): ${pizza.leftToppings}<br>
    </p>
</c:forEach>

</body>
</html>