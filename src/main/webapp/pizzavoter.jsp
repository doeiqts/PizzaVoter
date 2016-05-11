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
        <c:if test="${limitExceeded}">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            Topping Limit of <strong>${limit}</strong> was exceeded. Excess toppings have been removed from your pizzas.
        </div>
        </c:if>
        <form role="form" name="vote" method="post" action="/">
            <div class="row">
                <c:forEach begin="0" end="3" varStatus="pizza">
                    <div class=" col-lg-3 col-md-3 col-sm-6 col-xs-8">
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
                                    <c:set var="lineCount" value="${0}" />
                                    <c:if test="${fn:length(userPizzas) > pizza.index}">
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
                                                <label class="pizza-radio">
                                                    <input id="leftTop${pizza.count}-${lineCount}" name="position${pizza.count}-${lineCount}" ${toppingMap.value eq 'LEFT' ? 'checked' : ''} type="radio" value="LEFT" />
                                                    <i class="fa fa-adjust fa-flip-horizontal fa-lg" aria-hidden="true"></i>
                                                </label>
                                                <label class="pizza-radio">
                                                    <input id="allTop${pizza.count}-${lineCount}" name="position${pizza.count}-${lineCount}" type="radio" ${toppingMap.value eq 'ALL' ? 'checked' : ''} value="ALL" />
                                                    <i class="fa fa-circle fa-lg" aria-hidden="true"></i>
                                                </label>
                                                <label class="pizza-radio">
                                                    <input id="rightTop${pizza.count}-${lineCount}"  name="position${pizza.count}-${lineCount}" type="radio" ${toppingMap.value eq 'RIGHT' ? 'checked' : ''} value="RIGHT" />
                                                    <i class="fa fa-adjust fa-lg" aria-hidden="true"></i>
                                                </label>
                                            </div>
                                        </c:if>
                                        <c:if test="${toppingLoop.last}">
                                            <c:set var="lineCount" value="${lineCount + 1}" />
                                            <div id="row${pizza.count}-${lineCount}" class="row">
                                                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                                                    <select class="form-control" id="toppings${pizza.count}-${lineCount}" name="toppings${pizza.count}">
                                                        <option value=""></option>
                                                    <c:forEach var="topping" items="${toppings}">
                                                        <option value="${topping}">
                                                            <c:out value="${topping}" />
                                                        </option>
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                                <label class="pizza-radio">
                                                    <input id="leftTop${pizza.count}-${lineCount}" name="position${pizza.count}-${lineCount}" type="radio" value="LEFT" />
                                                    <i class="fa fa-adjust fa-flip-horizontal fa-lg" aria-hidden="true"></i>
                                                </label>
                                                <label class="pizza-radio">
                                                    <input id="allTop${pizza.count}-${lineCount}" name="position${pizza.count}-${lineCount}" type="radio" checked value="ALL" />
                                                    <i class="fa fa-circle fa-lg" aria-hidden="true"></i>
                                                </label>
                                                <label class="pizza-radio">
                                                    <input id="rightTop${pizza.count}-${lineCount}"  name="position${pizza.count}-${lineCount}" type="radio" value="RIGHT" />
                                                    <i class="fa fa-adjust fa-lg" aria-hidden="true"></i>
                                                </label>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                    </c:if>
                                    <c:if test="${lineCount < 10}">
                                         <c:forEach begin="${lineCount}" end="9" varStatus="toppingLoop">
                                            <c:set var="lineCount" value="${lineCount + 1}" />
                                            <div id="row${pizza.count}-${lineCount}" class="row ${lineCount > 1 ? 'hidden' : '' }">
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
                                                <label class="pizza-radio">
                                                    <input id="leftTop${pizza.count}-${lineCount}" name="position${pizza.count}-${lineCount}" type="radio" value="LEFT" />
                                                    <i  class="fa fa-adjust fa-flip-horizontal fa-lg" aria-hidden="true"></i>
                                                </label>
                                                <label class="pizza-radio">
                                                    <input id="allTop${pizza.count}-${lineCount}" name="position${pizza.count}-${lineCount}"  checked="checked" type="radio" value="ALL" />
                                                    <i class="fa fa-circle fa-lg" aria-hidden="true"></i>
                                                </label>
                                                <label class="pizza-radio">
                                                    <input id="rightTop${pizza.count}-${lineCount}" name="position${pizza.count}-${lineCount}" type="radio" value="RIGHT" />
                                                    <i class="fa fa-adjust fa-lg" aria-hidden="true"></i>
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </c:if>
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
                            <span class="badge pull-right">${pizza.value.count}</span>
                            <span>
                                <strong><c:out value="${pizza.key.crust}" /></strong><br>
                                <strong><c:out value="${pizza.key.sauce}" /></strong>
                            </span>
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
            for(var i = 1; i <= 10; i++) {
                $("#toppings" + pizzaNumber + "-" + i)[0].selectedIndex = 0;
                $("#allTop" + pizzaNumber + "-" + i).prop("checked", true);
                if(i > 1) {
                    $("#row" + pizzaNumber + "-" + i).addClass("hidden");
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
                        $("#row" + pizzaNumber + "-" + i).removeClass("hidden");
                        if(topMap[key] === 'LEFT') {
                            $("#leftTop" + pizzaNumber + "-" + i).prop("checked", true);
                        }else if(topMap[key] === 'RIGHT') {
                            $("#rightTop" + pizzaNumber + "-" + i).prop("checked", true);
                        } else if(topMap[key] === 'ALL') {
                            $("#allTop" + pizzaNumber + "-" + i).prop("checked", true);
                        }
                    }
                    i++;
                }
            }
            $("#row" + pizzaNumber + "-" + i).removeClass("hidden");
        }

        $(function() {
            <c:forEach begin="0" end="3" varStatus="pizza">
                <c:forEach begin="0" end="9" varStatus="line">
                    $("#toppings${pizza.count}-${line.count}").change(function() {
                        if($(this).val() !== "") {
                            $("#row${pizza.count}-${line.count + 1}").removeClass("hidden");
                        }
                    });
                </c:forEach>
            </c:forEach>
            <c:forEach var="pizza" items="${pizzas}" varStatus="pizzaLoop">
                 $("#votedPizza${pizzaLoop.count}").click(function() {
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
                });
            </c:forEach>
        });
    </script>
</body>
</html>