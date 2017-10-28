<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 28.10.2017 г.
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wallets</title>
</head>
<body>
    <h1>User: ${sessionScope.user.username}</h1>

    <table>
        <tr>
            <th>Name</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${wallets}" var="w">
            <tr>
                <td>${w.name}</td>
                <td>${w.amount}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
