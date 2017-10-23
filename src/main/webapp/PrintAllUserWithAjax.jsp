<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.dao.classes.UserDAO" %>
<%@ page session="false" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.10.2017 г.
  Time: 15:41 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<table border = 1>
    <tr>
        <th>User ID</th>
        <th>Password</th>
        <th>Blocked</th>
        <th>Rigths</th>
        <th>First name</th>
        <th>Email</th>
    </tr>
    <%
        ResultSet set = UserDAO.selecttUsers();
        while ( set.next() ) {%>
    <tr>
        <td><%= set.getInt("user_id")%></td>
        <td><%= set.getInt("password")%></td>
        <td><%= set.getInt("blocked")%></td>
        <td><%= set.getInt("rights")%></td>
        <td><%= set.getInt("first_name")%></td>
        <td><%= set.getInt("email")%></td>
    </tr>

    <% } %>
</table>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
