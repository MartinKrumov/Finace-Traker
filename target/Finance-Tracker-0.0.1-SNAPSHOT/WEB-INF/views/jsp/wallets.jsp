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
${ param.parameter }
<%--<c:if test="${param.parameter.equals(add)}">--%>

    <c:out value="${param.parameter}"/>
    <c:out value="inside the if"/>
    <%--<c:import var="data" url="${param.parameter}"/>--%>
    ${param.parameter}
<%--${request.contextPath}--%>
    <jsp:include page="${param.parameter}.jsp"/>

<%--</c:if>--%>


<table>
    <tr>
        <th>Name</th>
        <th>Amount</th>
    </tr>
    <c:forEach items="${wallets}" var="w">
        <tr>
            <td>${w.name}</td>
            <td>${w.amount}</td>
            <table border=1>
                <tr>
                    <th>Cat Name</th>
                    <th>Cat Id</th>
                    <th>Cat userId</th>
                    <th>Options</th>
                </tr>
                <c:forEach items="${w.categories}" var="cat">
                    <tr>
                        <td>${cat.name}</td>
                        <td>${cat.categoryId}</td>
                        <td>${cat.userId}</td>
                        <td><a href="wallets?parameter=add">Add</a></td>
                    </tr>
                </c:forEach>
            </table>

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
