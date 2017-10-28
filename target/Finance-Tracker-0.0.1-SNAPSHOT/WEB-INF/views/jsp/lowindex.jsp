<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.10.2017 г.
  Time: 15:31 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p> Last added product: ${user.username}</p>


<p> Last added product: ${myuser.username}</p>
<p> Last added product: ${myuser.password}</p>
<p> Last added product: ${user}</p>
<%--System.out.println(request.getSession(false).getAttribute("user_id"));--%>
<%--System.out.println(request.getSession(false).getAttribute("email"));--%>
<%--System.out.println(request.getSession(false).getAttribute("username"));--%>


<c:if test="${user_id != null }">
    <p> user_id ${user_id}</p>
    <p> username ${username}</p>
    <p> username ${user_email}</p>
</c:if>

<c:if test="${user.email != null }">
<p> Last added product: ${user.email}</p>
</c:if>
</body>
</html>
