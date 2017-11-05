<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 03.11.2017 г.
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Budgets</title>
</head>
<body>
    <c:forEach items="${budgets}" var="budget">
        <h1> Budget Name: ${budget.name}</h1>
        <h2> Initial amount: ${budget.initialAmount}</h2>
        <h2> Current amount: ${budget.amount}</h2>

    <a href="<c:url value='/budget/${budget.budgetId}'></c:url>">Show Transactions</a>
    </c:forEach>

</body>
</html>
