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
<%--<h1>User: ${sessionScope.user.username}</h1>--%>
<%--${ param.parameter }--%>
<%--<c:if test="${param.parameter.equals(add)}">--%>

<%--<c:out value="${param.parameter}"/>--%>
<%--<c:out value="inside the if"/>--%>
<%--<c:import var="data" url="${param.parameter}"/>--%>
<%--${param.parameter}--%>
<%--${request.contextPath}--%>
<%--<jsp:include page="${param.parameter}.jsp"/>--%>

<%--</c:if>--%>
<%--<select name="select">--%>

<%--<option value="1">1</option>--%>
<%--<option value="2">2</option>--%>
<%--<option value="3">3</option>--%>
<%--</select>--%>
<%--<c:out value="${param.select}"/>--%>
<form commandName="wallet" method="post" action="walletInsert">
    <div class="field-wrap">
        <label>
            Wallet Name<span class="req">*</span>
        </label>
        <input type="text" name="name"/>
    </div>
    <div class="field-wrap">
        <label>
            Amount<span class="req">*</span>
        </label>
        <input type="text" name="amount"/>
    </div>
    <button class="button button-block"/>
    Insert</button>
</form>
<%--<c:forEach items="${sessionScope.user.wallets}" var="w">--%>
    <%--Wallet name: ${w.name}<br/>--%>
    <%--Wallet Categories:--%>
    <%--<table border=1>--%>
        <%--<tr>--%>
            <%--<th>Cat Name</th>--%>
            <%--<th>Cat Id</th>--%>
            <%--<th>Cat userId</th>--%>
            <%--&lt;%&ndash;<th>Options</th>&ndash;%&gt;--%>
            <%--<th>Income/Expense</th>--%>
            <%--<th>Description</th>--%>
            <%--<th>Amount</th>--%>
            <%--<th>Submit</th>--%>
        <%--</tr>--%>
        <%--<c:forEach items="${w.categories}" var="cat">--%>
            <%--<tr>--%>
                <%--<td>${cat.name}</td>--%>
                <%--<td>${cat.categoryId}</td>--%>
                <%--<td>${cat.userId}</td>--%>
<%--&lt;%&ndash;long transactionId, TransactionType type, BigDecimal amount, LocalDateTime date, String description, long categoryId) {&ndash;%&gt;--%>
                <%--<form  method="post" action="transaction">--%>
                    <%--<td><input type="checkbox" name="isIncome" value="1"/></td>--%>
                    <%--<td><input type="text" name="description"/></td>--%>
                    <%--<td><input type="number" name="amount"/></td>--%>
                    <%--<input style="display: none;" type="hidden" name="categoryId" value = "${cat.categoryId}"/>--%>
                    <%--<td><input type="submit" name="submitsTrans" value="Add"/></td>--%>
                <%--</form>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>


<%--</c:forEach>--%>

<%--<form:form commandName="wallet" method="post" action="wallet_insert">--%>

<%--<div class="field-wrap">--%>
<%--<label>--%>
<%--Wallet Name<span class="req">*</span>--%>
<%--</label>--%>
<%--<form:input type="text" path="name" autocomplete="on"/>--%>
<%--</div>--%>
<%--<div class="field-wrap">--%>
<%--<label>--%>
<%--Amount<span class="req">*</span>--%>
<%--</label>--%>
<%--<form:input type="text" path="amount"/>--%>
<%--</div>--%>
<%--<button class="button button-block"/>--%>
<%--Insert</button>--%>
<%--</form:form>--%>

</table>
</body>
</html>
