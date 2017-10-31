<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.10.2017 г.
  Time: 17:04 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%--<%@ page session="false" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="css/jspstyle.css">
<html>
<head>

    <meta charset="UTF-8">
    <title>Finance Tracker</title>

</head>
<body>

<c:if test="${empty sessionScope.user.username}">
    <c:redirect url="index"/>
</c:if>
<div id="header">
    <ul id="menu">
        <li><a href="/home"><span>Home</span></a></li>
        <li><a href="/"><span>Tutorials</span></a></li>
        <li><a id="walletuser" href="${requestScope['javax.servlet.forward.request_uri']}?parameter=wallets" onclick=""><span>Wallets</span></a>
        </li>
        <c:if test="${sessionScope.user.rights == 1}">
            <li><a href="#" id="userslist" onclick=""><span>List Users</span></a></li>
        </c:if>
        <li><a href="logout"><span>Logout</span></a></li>
    </ul>
    <div style="float: right; color: #00aa2b; margin-top: -3.5em; "><h1>${sessionScope.user.username}</h1>
    </div>
</div>
<%--<h1>User blocked: ${sessionScope.user.blocked}</h1>--%>
<%--<h1>User rights: ${sessionScope.user.rights}</h1>--%>
<%--<c:if test="${sessionScope.user!= null }">--%>
<%--<p> user_id ${sessionScope.user.user_id}</p>--%>
<%--<p> username ${sessionScope.user.username}</p>--%>
<%--<p> user_email ${sessionScope.user.user_email}</p>--%>
<%--<p> user_rights ${sessionScope.user.user_rights}</p>--%>
<%--<p> user_blocked ${sessionScope.user.user_blocked}</p>--%>
<%--</c:if>--%>
<%--<c:set var="req" value="${pageContext.request}"/>--%>
<%--<c:set var="baseURL" value="${fn:replace(req.requestURL, req.requestURI, '')}" />--%>
<%--<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}"/>--%>
<%--<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>--%>
<%--<c:set var="pageUrl" value="${ baseURL }${ requestPath }${ not empty params?'?'+=params:'' }"/>--%>

<%--<c:out value="requestPath"/>--%>
<%--<c:out value="${requestScope['javax.servlet.forward.query_string']}"/><br/>--%>
<%--<c:out value="${requestScope['javax.servlet.forward.request_uri']}"/><br/>--%>
<%--<c:out value="${pageContext.request}"/><br/>--%>
<%--<c:out value="${pageContext.request}"/><br/>--%>

<c:if test="${param.parameter !=null }">
    <jsp:include page="${param.parameter}.jsp"/>
</c:if>
<div class="wrapper">

    <form action="wallet" method="post">
        <input type="text" name="wallet_name">
        <select name="categories" id="">
            <option value="1">option 1</option>
            <option value="2">option 1</option>
            <option value="3">option 1</option>
        </select>
        <input type="checkbox" name="check" value="Car" checked>
        <input type="submit" value="insert">
    </form>

</div>

<div id="tableprint"></div>

<script type="text/javascript"
        src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        var onDeleteClick = function (e) {
            e.preventDefault();
            var user_id = $(this).data('user-id');
//                this.getProperty('data-user-id')
            alert("u id " + user_id);
            return;
            var $parent = $(this).parents('tr');
            console.log(parent);
            $.ajax({
                url: 'DelUserFromAdmin',
                type: 'post',
                data: {del_user_id: user_id},
                success: function () {
                    $parent.hide();
                }
            })
        };

        $("#userslist").click(function () {
            $.ajax({
                url: 'ajaxJsp/PrintAllUserWithAjax.jsp',
                success: function (data) {
//                        alert("success");
//                    document.getElementById("tableprint").innerHTML = data;
                    $("#tableprint").html(data);
                    console.log($(".optionsAdmin").length);
                    $(".optionsAdmin").click(onDeleteClick);
                }
            });
        });

    });


    $(document).ready(function () {
        $("#walletuser").click(function () {
            $.ajax({
                url: 'ajaxJsp/SelectWalletsAjax.jsp',
                success: function (data) {
                    document.getElementById("tableprint").innerHTML = data;
                }
            });
        });
    });
</script>
</body>
</html>
