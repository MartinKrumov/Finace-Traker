<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2.11.2017 г.
  Time: 09:06 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <c:if test="${empty sessionScope.user.username}">
        <c:redirect url="index"/>
    </c:if>
</head>
<body>

</body>
</html>
