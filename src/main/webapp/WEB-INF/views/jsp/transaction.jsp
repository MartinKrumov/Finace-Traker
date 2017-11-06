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
<c:set var="count" value="0" scope="page"/>

<div class="container" style="margin-top: 0 !important;">
    <form commandName="transaction" method="post" action="transactionInsert">
        <div class="field-wrap">
            <label>
                Amount<span class="req">*</span>
            </label>
            <input type="number" name="amount"/>
        </div>
        <div class="field-wrap">
            <label>
                Description<span class="req">*</span>
            </label>
            <input type="text" name="description"/>
        </div>
        <div class="field-wrap">
            <label>
                CategoryId<span class="req">*</span>
            </label>
            <select name="categoryId">
                <c:forEach items="${sessionScope.user.wallets}" var="w">
                    <c:if test="${count == 0}">
                        <c:forEach items="${w.categories}" var="cat">
                            <option value="${cat.categoryId}"> ${cat.name}</option>
                        </c:forEach>
                    </c:if>
                    <c:set var="count" value="${count + 1}" scope="page"/>
                </c:forEach>
            </select>
        </div>
        <div class="field-wrap">
            <label>
                WalletId<span class="req">*</span>
            </label>
            <select name="walletId">
                <c:forEach items="${sessionScope.user.wallets}" var="w">
                    <option value="${w.walletId}"> ${w.name}</option>
                </c:forEach>
            </select>
        </div>
        <button class="button button-block"/>
        Insert</button>
    </form>
    <div style="float:right;">
    <form method="post" action="addcategory">
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
            <input type="checkbox" name="income"/>
        </div>
        <button class="button button-block"/>
        Insert</button>
    </form>
    </div>
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
