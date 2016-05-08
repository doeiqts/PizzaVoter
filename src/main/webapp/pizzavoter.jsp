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

    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.2/css/font-awesome.min.css">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">

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
                    <div class=" col-lg-3 col-md-3 col-sm-6 col-xs-6">
                        <div class="panel panel-default">
                            <div class="panel-heading pizza-header">Pizza ${pizza.count} <span onclick="clearMyPizzaVote(${pizza.count})" class="glyphicon glyphicon-remove pull-right text-danger" aria-hidden="true"></span></div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label for="crust${pizza.count}">
                                        Crust
                                    </label>
                                    <select class="form-control" id="crust${pizza.count}" name="crust${pizza.count}">
                                        <option value=""></option>
                                        <c:forEach var="crust" items="${crusts}">
                                            <option value="${crust}" ${fn:length(userPizzas) >= pizza.count && userPizzas[pizza.index].crust == crust ? "selected" : ""}><c:out value="${crust}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="sauce${pizza.count}">
                                        Sauce
                                    </label>
                                    <select class="form-control" id="sauce${pizza.count}" name="sauce${pizza.count}">
                                        <option value=""></option>
                                        <c:forEach var="sauce" items="${sauces}">
                                            <option value="${sauce}" ${fn:length(userPizzas) >= pizza.count && userPizzas[pizza.index].sauce == sauce ? "selected" : ""}><c:out value="${sauce}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="toppings${pizza.count}">
                                        Toppings
                                    </label>
                                    <c:choose>
                                    <c:when test="${fn:length(userPizzas) > pizza.index && !empty userPizzas[pizza.index]}">
                                        <c:set var="lineCount" value="${0}" />
                                        <c:forEach var="toppingMap" items="${userPizzas[pizza.index].toppings}" varStatus="toppingLoop">
                                            <c:if test="${toppingMap.key ne 'CHEESE'}">
                                                <c:set var="lineCount" value="${lineCount + 1}" />
                                                <div id="row${pizza.count}-${lineCount}" class="row">
                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                                                        <select class="form-control" id="toppings${pizza.count}-${lineCount}" name="toppings${pizza.count}">
                                                            <option value=""></option>
                                                        <c:forEach var="topping" items="${toppings}">
                                                            <option value="${topping}" ${toppingMap.key == topping ? "selected" : ""}>
                                                                <c:out value="${topping}" />
                                                            </option>
                                                        </c:forEach>
                                                        </select>
                                                    </div>
                                                    <input id="position${pizza.count}-${lineCount}" name="position${pizza.count}" type="hidden" value="${toppingMap.value}">
                                                    <i id="leftTop${pizza.count}-${lineCount}" onclick="setPosition(${pizza.count},${lineCount},'LEFT')" class="fa fa-adjust fa-flip-horizontal fa-lg ${toppingMap.value == 'LEFT' ? 'text-primary' : ''}" aria-hidden="true"></i>
                                                    <c:if test="${lineCount <= 2}"><i id="allTop${pizza.count}-${lineCount}" onclick="setPosition(${pizza.count},${lineCount},'ALL')" class="fa fa-circle fa-lg ${toppingMap.value == 'ALL' ? 'text-primary' : ''}" aria-hidden="true"></i></c:if>
                                                    <i id="rightTop${pizza.count}-${lineCount}" onclick="setPosition(${pizza.count},${lineCount},'RIGHT')" class="fa fa-adjust fa-lg ${toppingMap.value == 'RIGHT' ? 'text-primary' : ''}" aria-hidden="true"></i>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${lineCount < 4}">
                                             <c:forEach begin="${lineCount}" end="3" varStatus="toppingLoop">
                                                <c:set var="lineCount" value="${lineCount + 1}" />
                                                <div id="row${pizza.count}-${lineCount}" class="row ${lineCount > 2 ? 'hidden' : '' }">
                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                                                        <select class="form-control" id="toppings${pizza.count}-${lineCount}" name="toppings${pizza.count}">
                                                            <option value=""></option>
                                                        <c:forEach var="topping" items="${toppings}">
                                                            <option value="${topping}" >
                                                                <c:out value="${topping}" />
                                                            </option>
                                                        </c:forEach>
                                                        </select>
                                                    </div>
                                                    <input id="position${pizza.count}-${lineCount}" name="position${pizza.count}" type="hidden" value="ALL">
                                                    <i id="leftTop${pizza.count}-${lineCount}" onclick="setPosition(${pizza.count},${lineCount},'LEFT')" class="fa fa-adjust fa-flip-horizontal fa-lg" aria-hidden="true"></i>
                                                    <c:if test="${lineCount <= 2}"><i id="allTop${pizza.count}-${lineCount}" onclick="setPosition(${pizza.count},${lineCount},'ALL')" class="fa fa-circle fa-lg text-primary" aria-hidden="true"></i></c:if>
                                                    <i id="rightTop${pizza.count}-${lineCount}" onclick="setPosition(${pizza.count},${lineCount},'RIGHT')" class="fa fa-adjust fa-lg" aria-hidden="true"></i>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach begin="0" end="3" varStatus="toppingLoop">
                                            <div id="row${pizza.count}-${toppingLoop.count}" class="row ${toppingLoop.count > 2 ? 'hidden' : '' }">
                                            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                                                <select class="form-control" id="toppings${pizza.count}-${toppingLoop.count}" name="toppings${pizza.count}">
                                                    <option value=""></option>
                                                <c:forEach var="topping" items="${toppings}">
                                                    <option value="${topping}" >
                                                        <c:out value="${topping}" />
                                                    </option>
                                                </c:forEach>
                                                </select>
                                            </div>
                                            <input id="position${pizza.count}-${toppingLoop.count}" name="position${pizza.count}" type="hidden" value="ALL">
                                            <i id="leftTop${pizza.count}-${toppingLoop.count}" onclick="setPosition(${pizza.count},${toppingLoop.count},'LEFT')" class="fa fa-adjust fa-flip-horizontal fa-lg" aria-hidden="true"></i>
                                            <c:if test="${toppingLoop.count <= 2}"><i id="allTop${pizza.count}-${toppingLoop.count}" onclick="setPosition(${pizza.count},${toppingLoop.count},'ALL')" class="fa fa-circle fa-lg text-primary" aria-hidden="true"></i></c:if>
                                            <i id="rightTop${pizza.count}-${toppingLoop.count}" onclick="setPosition(${pizza.count},${toppingLoop.count},'RIGHT')" class="fa fa-adjust fa-lg" aria-hidden="true"></i>
                                            </div>
                                        </c:forEach>
                                    </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="row top-buffer">
                <div class="col-xs-12">
                    <p>
                        <button type="submit" class="btn btn-primary btn-lg" alt="vote">Vote</button>
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
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <span class="truncate">
                                <strong><c:out value="${pizza.key.crust}" /> <c:out value="${pizza.key.sauce}" /></strong>
                            </span>
                            <span class="badge pull-right">${pizza.value.count}</span>
                            <br>
                        </div>
                        <div class="panel-body">
                            <c:if test="${fn:contains(pizza.value.usersWhoVoted, name)}"><span class="label label-success pull-right">Voted For</span></c:if>
                            <ul>
                                <c:forEach var="topping" items="${pizza.key.toppings}" varStatus="toppingLoop">
                                    <li>
                                        <c:out value="${topping.key}" /><c:if test="${topping.value == 'LEFT'}"> (Left Half) </c:if><c:if test="${topping.value == 'RIGHT'}"> (Right Half) </c:if>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="panel-footer text-center"><a id="votedPizza${pizzaLoop.count}" href="#">Copy</a></div>
                    </div>
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
<script type="text/javascript">
        function clearMyPizzaVote(pizzaNumber) {
            $("#crust" + pizzaNumber)[0].selectedIndex = 0;
            $("#sauce" + pizzaNumber)[0].selectedIndex = 0;
            $("#toppings" + pizzaNumber + "-1")[0].selectedIndex = 0;
            $("#toppings" + pizzaNumber + "-2")[0].selectedIndex = 0;
            $("#toppings" + pizzaNumber + "-3")[0].selectedIndex = 0;
            $("#toppings" + pizzaNumber + "-4")[0].selectedIndex = 0;
            setPosition(pizzaNumber,1,"ALL");
            setPosition(pizzaNumber,2,"ALL");
        }

        function setPosition(pizzaNumber, toppingNumber, position) {
            $("#position" + pizzaNumber + "-" + toppingNumber).val(position);
            $("#leftTop" + pizzaNumber + "-" + toppingNumber).removeClass('text-primary');
            $("#allTop" + pizzaNumber + "-" + toppingNumber).removeClass('text-primary');
            $("#rightTop" + pizzaNumber + "-" + toppingNumber).removeClass('text-primary');

            if(position === "LEFT") {
                $("#leftTop" + pizzaNumber + "-" + toppingNumber).addClass('text-primary');
                if(toppingNumber <= 2) {
                    $("#row" + pizzaNumber + "-" + (toppingNumber+ 2)).removeClass('hidden');
                    $("#position" + pizzaNumber + "-" + (toppingNumber+2)).val("RIGHT");
                    $("#leftTop" + pizzaNumber + "-" + (toppingNumber+2)).removeClass('text-primary');
                    $("#rightTop" + pizzaNumber + "-" + (toppingNumber+2)).addClass('text-primary');
                }
            } else if(position === "ALL") {
                $("#allTop" + pizzaNumber + "-" + toppingNumber).addClass('text-primary');
                if(toppingNumber <= 2) {
                    $("#row" + pizzaNumber + "-" + (toppingNumber+ 2)).addClass('hidden');
                    $("#position" + pizzaNumber + "-" + (toppingNumber+2)).val();
                    $("#toppings" + pizzaNumber + "-" + (toppingNumber+2))[0].selectedIndex = 0;
                }
            } else if(position === "RIGHT") {
                $("#rightTop" + pizzaNumber + "-" + toppingNumber).addClass('text-primary');
                if(toppingNumber <= 2) {
                    $("#row" + pizzaNumber + "-" + (toppingNumber+2)).removeClass('hidden');
                    $("#position" + pizzaNumber + "-" + (toppingNumber+2)).val("LEFT");
                    $("#leftTop" + pizzaNumber + "-" + (toppingNumber+2)).addClass('text-primary');
                    $("#rightTop" + pizzaNumber + "-" + (toppingNumber+2)).removeClass('text-primary');
                }
            }
        }


        function setMyPizzaVote(pizzaNumber, crust, sauce, topMap) {
            $("#crust" + pizzaNumber).val(crust);
            $("#sauce" + pizzaNumber).val(sauce);
            var i = 0;
            for(var key in topMap) {
                if (topMap.hasOwnProperty(key)) {
                    if(key !== "Cheese") {
                        $("#toppings" + pizzaNumber + '-' + i).val(key.replace(" ", "_").toUpperCase());
                        setPosition(pizzaNumber, i, topMap[key]);
                    }
                    i++;
                }
            }
        }


        window.onload = function() {
            <c:forEach begin="0" end="3" varStatus="pizza">
            <c:if test="${fn:length(userPizzas) > pizza.index && !empty userPizzas[pizza.index]}">
             <c:set var="lineCount" value="${0}" />
            <c:forEach var="toppingMap" items="${userPizzas[pizza.index].toppings}" varStatus="toppingLoop">
                <c:if test="${toppingMap.key ne 'CHEESE'}">
                    <c:set var="lineCount" value="${lineCount + 1}" />
                    setPosition(${pizza.count}, ${lineCount}, '${toppingMap.value}');
                </c:if>
            </c:forEach>
            </c:if>
            </c:forEach>

            <c:forEach var="pizza" items="${pizzas}" varStatus="pizzaLoop">
                document.getElementById("votedPizza${pizzaLoop.count}").onclick = function() {
                    var topMap${pizzaLoop.count} = ${pizza.key.toppingsJson};


                    if ($("#crust1")[0].selectedIndex == 0) {
                        setMyPizzaVote(1, "${pizza.key.crust}", "${pizza.key.sauce}", topMap${pizzaLoop.count});
                    } else if ($("#crust2")[0].selectedIndex == 0) {
                        setMyPizzaVote(2, "${pizza.key.crust}", "${pizza.key.sauce}", topMap${pizzaLoop.count});
                    } else if ($("#crust3")[0].selectedIndex == 0) {
                        setMyPizzaVote(3, "${pizza.key.crust}", "${pizza.key.sauce}", topMap${pizzaLoop.count});
                    } else if ($("#crust4")[0].selectedIndex == 0) {
                        setMyPizzaVote(4, "${pizza.key.crust}", "${pizza.key.sauce}", topMap${pizzaLoop.count});
                    } else {
                        alert("All your pizza votes are being used. Clear a vote to be able to copy another.");
                    }

                    return false;
                }
            </c:forEach>
        }
    </script>
</body>
</html>