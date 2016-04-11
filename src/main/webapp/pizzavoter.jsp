<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">

	<!-- PizzaVoter style -->
    <link rel="stylesheet" type="text/css" href="/css/style.css" />

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script type="text/javascript">
        function clearMyPizzaVote(pizzaNumber) {
            $("#crust" + pizzaNumber)[0].selectedIndex = 0;
            $("#sauce" + pizzaNumber)[0].selectedIndex = 0;
            $("#rightToppings" + pizzaNumber + "-1")[0].selectedIndex = 0;
            $("#rightToppings" + pizzaNumber + "-2")[0].selectedIndex = 0;
            $("#leftToppings" + pizzaNumber + "-1")[0].selectedIndex = 0;
            $("#leftToppings" + pizzaNumber + "-2")[0].selectedIndex = 0;
        }

        function setMyPizzaVote(pizzaNumber, crust, sauce, rightTopping1, rightTopping2, leftTopping1, leftTopping2) {
            $("#crust" + pizzaNumber).val(crust);
            $("#sauce" + pizzaNumber).val(sauce);
            $("#rightToppings" + pizzaNumber + "-1").val(rightTopping1);
            $("#rightToppings" + pizzaNumber + "-2").val(rightTopping2);
            $("#leftToppings" + pizzaNumber + "-1").val(leftTopping1);
            $("#leftToppings" + pizzaNumber + "-2").val(leftTopping2);
        }
    </script>

    <script type="text/javascript">
        window.onload = function() {
            <c:forEach var="pizza" items="${pizzas}" varStatus="pizzaLoop">
                document.getElementById("votedPizza${pizzaLoop.count}").onclick = function() {
                    if ($("#crust1")[0].selectedIndex == 0) {
                        setMyPizzaVote(1, "${pizza.key.crust}", "${pizza.key.sauce}", "${fn:length(pizza.key.rightToppingsList) > 1 ? pizza.key.rightToppingsList[1] : ''}", "${fn:length(pizza.key.rightToppingsList) > 2 ? pizza.key.rightToppingsList[2] : ''}", "${fn:length(pizza.key.leftToppingsList) > 1 ? pizza.key.leftToppingsList[1] : ''}", "${fn:length(pizza.key.leftToppingsList) > 2 ? pizza.key.leftToppingsList[2] : ''}");
                    } else if ($("#crust2")[0].selectedIndex == 0) {
                        setMyPizzaVote(2, "${pizza.key.crust}", "${pizza.key.sauce}", "${fn:length(pizza.key.rightToppingsList) > 1 ? pizza.key.rightToppingsList[1] : ''}", "${fn:length(pizza.key.rightToppingsList) > 2 ? pizza.key.rightToppingsList[2] : ''}", "${fn:length(pizza.key.leftToppingsList) > 1 ? pizza.key.leftToppingsList[1] : ''}", "${fn:length(pizza.key.leftToppingsList) > 2 ? pizza.key.leftToppingsList[2] : ''}");
                    } else if ($("#crust3")[0].selectedIndex == 0) {
                        setMyPizzaVote(3, "${pizza.key.crust}", "${pizza.key.sauce}", "${fn:length(pizza.key.rightToppingsList) > 1 ? pizza.key.rightToppingsList[1] : ''}", "${fn:length(pizza.key.rightToppingsList) > 2 ? pizza.key.rightToppingsList[2] : ''}", "${fn:length(pizza.key.leftToppingsList) > 1 ? pizza.key.leftToppingsList[1] : ''}", "${fn:length(pizza.key.leftToppingsList) > 2 ? pizza.key.leftToppingsList[2] : ''}");
                    } else if ($("#crust4")[0].selectedIndex == 0) {
                        setMyPizzaVote(4, "${pizza.key.crust}", "${pizza.key.sauce}", "${fn:length(pizza.key.rightToppingsList) > 1 ? pizza.key.rightToppingsList[1] : ''}", "${fn:length(pizza.key.rightToppingsList) > 2 ? pizza.key.rightToppingsList[2] : ''}", "${fn:length(pizza.key.leftToppingsList) > 1 ? pizza.key.leftToppingsList[1] : ''}", "${fn:length(pizza.key.leftToppingsList) > 2 ? pizza.key.leftToppingsList[2] : ''}");
                    } else {
                        alert("All your pizza votes are being used. Clear a vote to be able to copy another.");
                    }

                    return false;
                }
            </c:forEach>
        }
    </script>


</head>
<body>

    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Pizza Voter</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><p class="navbar-text">Hello ${name}</p></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container">
        <form role="form" name="vote" method="post" action="/">
            <div class="row">
                <c:forEach begin="0" end="3" varStatus="pizza">
                    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
                        <legend>Pizza ${pizza.count}</legend>
                        <p>
                            Crust:  <select class="form-control" id="crust${pizza.count}" name="crust${pizza.count}">
                                        <option value=""></option>
                                    <c:forEach var="crust" items="${crusts}">
                                        <option value="${crust}" ${fn:length(userPizzas) >= pizza.count && userPizzas[pizza.index].crust == crust ? "selected" : ""}>${crust}</option>
                                    </c:forEach>
                                    </select>
                                    <br>

                            Sauce:  <select class="form-control" id="sauce${pizza.count}" name="sauce${pizza.count}">
                                        <option value=""></option>
                                    <c:forEach var="sauce" items="${sauces}">
                                        <option value="${sauce}" ${fn:length(userPizzas) >= pizza.count && userPizzas[pizza.index].sauce == sauce ? "selected" : ""}>${sauce}</option>
                                    </c:forEach>
                                    </select>
                                    <br>

                            Right Toppings:
                            <c:forEach begin="0" end="1" varStatus="toppingLoop">
                                <select class="form-control" id="rightToppings${pizza.count}-${toppingLoop.count}" name="rightToppings${pizza.count}">
                                    <option value=""></option>
                                <c:forEach var="topping" items="${toppings}">
                                    <option value="${topping}" ${fn:length(userPizzas) >= pizza.count
                                                                && fn:length(userPizzas[pizza.index].rightToppingsList) > toppingLoop.count
                                                                && userPizzas[pizza.index].rightToppingsList[toppingLoop.count] == topping
                                                                ? "selected" : ""}>
                                        ${topping}
                                    </option>
                                </c:forEach>
                                </select>
                            </c:forEach>
                            <br>

                            Left Toppings:
                            <c:forEach begin="0" end="1" varStatus="toppingLoop">
                                <select class="form-control" id="leftToppings${pizza.count}-${toppingLoop.count}" name="leftToppings${pizza.count}">
                                    <option value=""></option>
                                <c:forEach var="topping" items="${toppings}">
                                    <option value="${topping}" ${fn:length(userPizzas) >= pizza.count
                                                                && fn:length(userPizzas[pizza.index].leftToppingsList) > toppingLoop.count
                                                                && userPizzas[pizza.index].leftToppingsList[toppingLoop.count] == topping
                                                                ? "selected" : ""}>
                                        ${topping}
                                    </option>
                                </c:forEach>
                                </select>
                            </c:forEach>
                            <br>
                            <button type="button" class="btn btn-default" alt="clear pizza" onclick="clearMyPizzaVote(${pizza.count})">Clear Pizza</button>
                            <br>
                        </p>
                    </div>
                </c:forEach>
            </div>
            <div class="row top-buffer">
                <div class="col-xs-12">
                    <p>
                        <button type="submit" class="btn btn-default" alt="vote">Vote</button>
                    </p>
                </div>
            </div>
        </form>

        <div class="row top-buffer">
            <legend>Current voters</legend>
            <div class="col-xs-12">
                <c:forEach var="voter" items="${voters}">
                    ${voter}
                    <c:if test="${name == 'doeiqts'}">
                         <a href="/clear?username=${voter}">[X]</a>
                    </c:if>
                    <br>
                </c:forEach>
            </div>
            <c:if test="${empty voters}">
                <div class="col-xs-12">
                    <p>
                        No one has voted yet.
                    </p>
                </div>
            </c:if>
        </div>

        <div class="row top-buffer">
            <legend>Current pizzas being voted on</legend>
            <c:forEach var="pizza" items="${pizzas}" varStatus="pizzaLoop">
                <div class="col-md-3 col-sm-4 col-xs-6">
                    <p>
                        <b>Votes:</b> ${pizza.value} <a id="votedPizza${pizzaLoop.count}" href="#">Copy</a><br>
                        <b>Crust:</b> ${pizza.key.crust}<br>
                        <b>Sauce:</b> ${pizza.key.sauce}<br>
                        <b>Toppings (right side):</b><br>
                            ${pizza.key.rightToppings}<br>
                        <b>Toppings (left side):</b><br>
                            ${pizza.key.leftToppings}<br>
                    </p>
                </div>

                <c:if test="${pizzaLoop.count % 4 == 0}">
                    <div class="clearfix visible-lg-block visible-md-block"></div>
                </c:if>
                <c:if test="${pizzaLoop.count % 3 == 0}">
                    <div class="clearfix visible-sm-block"></div>
                </c:if>
                <c:if test="${pizzaLoop.count % 2 == 0}">
                    <div class="clearfix visible-xs-block"></div>
                </c:if>
            </c:forEach>
            <c:if test="${empty pizzas}">
                <div class="col-xs-12">
                    <p>
                        No pizzas have been voted on yet.
                    </p>
                </div>
            </c:if>
        </div>
    </div>

</body>
</html>