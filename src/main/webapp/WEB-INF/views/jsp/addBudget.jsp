<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 01.11.2017 г.
  Time: 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Budgets</title>
    <link href="css/front-awesome.min.css" rel="stylesheet">
    <script src="js/calendar.js"></script>

    <script src="js/calendar.js"></script>
</head>
<body>

    <form action="addBudget" method="post">
        <label>Name</label>
        <input type="text" placeholder="Enter budget name" name="name">

        <br>
        <label>Wallet</label>
        <select data-placeholder="Select an wallet" name="account">
            <c:forEach items="${ wallets }" var="w">
                <option><c:out value="${ w.name }"></c:out></option>
            </c:forEach>
        </select>
        <br>

        <label>Category</label>
        <select data-placeholder="Select a category" name="category">
            <c:forEach items="${ categories }" var="category">
                <option><c:out value="${ category }"></c:out></option>
            </c:forEach>
        </select>
        <%--<input type="text" placeholder="Category" name="category">--%>
        <br>
        <label>End Date</label>
        <input type="date" name="date" >

        <br>
        <label>Amount</label>
        <input type="text" placeholder="Amount" name="amount">

        <br>
        <button type="submit" >Save</button>
    </form>



</body>
</html>
