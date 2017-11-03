<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 01.11.2017 г.
  Time: 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Budgets</title>
</head>
<body>

    <form:form commandName="budget">
        Name: <form:input path="name"/> </br>
        Amount: <form:input path="initialAmount"/> </br>
        From date: <form:input type = "date" path="fromDate"/> </br>
        To date: <form:input type = "date" path="toDate"/> </br>

        <input type="submit" value="Save Budget">
    </form:form>

</body>
</html>
