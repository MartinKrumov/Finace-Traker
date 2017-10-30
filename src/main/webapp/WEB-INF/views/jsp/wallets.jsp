<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Wallets</title>
</head>
<body>
<c:if test="${empty sessionScope.user.username}">
    <c:redirect url="index?error=loginerror"/>
</c:if>
<h1>User: ${sessionScope.user.username}</h1>

<table>
    <tr>
        <th>Name</th>
        <th>Amount</th>
    </tr>
    <c:forEach items="${wallets}" var="w">
        <tr>
            <td>${w.name}</td>
            <c:forEach items="${w.categories}" var="cat">
                <td>${cat.name}</td>
                <td>${cat.categoryId}</td>
                <td>${cat.userId}</td>
            </c:forEach>
            <td>${w.amount}</td>
        </tr>
    </c:forEach>

    <form:form commandName="wallet" method="post" action="wallet_insert">

        <div class="field-wrap">
            <label>
                Wallet Name<span class="req">*</span>
            </label>
            <form:input type="text" path="name" autocomplete="on"/>
        </div>
        <div class="field-wrap">
            <label>
                Amount<span class="req">*</span>
            </label>
            <form:input type="text" path="amount"/>
        </div>
        <button class="button button-block"/>
        Insert</button>
    </form:form>

</table>
</body>
</html>
