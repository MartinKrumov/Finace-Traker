<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>transaction</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container" style="margin-top: 0 !important;">
    <c:forEach items="${sessionScope.user.wallets}" var="w">

        <%--<tr>--%>
        <%--<th>Trans id</th>--%>
        <%--<th>Type</th>--%>
        <%--<th>Amount</th>--%>
        <%--<th>Date</th>--%>
        <%--<th>Description</th>--%>
        <%--<th>Category id</th>--%>
        <%--<th>Wallet id</th>--%>
        <%--</tr>--%>
        <c:forEach items="${w.categories}" var="cat">

            <c:if test="${!empty cat.transactions}">
                Wallet name: ${w.name}<br/>
                Category name: ${cat.name}<br/>
            </c:if>
            <div class="table-trans">
            <table class="table">
            <c:set var="date" value="0" scope="page"/>
            <c:forEach items="${cat.transactions}" var="trans">

                <c:if test="${trans.date != date}">
                    </table>
                    <c:set var="date" value="${trans.date}" scope="page"/>
                    <br/><br/>
                    <div style="color: #428b7e;"><c:out value="${date}"/></div>
                    <br/>
                    <table class="table">
                </c:if>
                <c:if test="${trans.type == 'INCOME'}">
                    <tr class="bg-success">
                </c:if>
                <c:if test="${trans.type == 'EXPENSE'}">
                    <tr class="bg-danger">
                </c:if>
                <td>${trans.transactionId}</td>
                <td>${trans.type}</td>
                <td>${trans.amount}</td>
                <td> ${trans.date}</td>
                <td> ${trans.description}</td>
                <td> ${trans.categoryId}</td>
                <td> ${trans.walletId}</td>
                </tr>
            </c:forEach>
        </c:forEach>
        </table>
        </div>

    </c:forEach>
</div>
</body>
</html>
