<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 05.11.2017 г.
  Time: 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Budget Info</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
        }
        th {
            text-align: left;
        }
    </style>
</head>
<body>
        <table style="width:100%">
        <tr>
            <th>Amount</th>
            <th>Type</th>
            <th>Category</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${transactions}" var="t">
                <tr>
                    <td>${t.amount}</td>
                    <td>${t.type}</td>
                    <td>${t.categoryId}</td>
                    <td>${t.description}</td>
                </tr>
        </c:forEach>
        </table>
</body>
</html>
