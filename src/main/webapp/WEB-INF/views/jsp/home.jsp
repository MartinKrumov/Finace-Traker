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
        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?parameter=transaction"><span>Home</span></a></li>
        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?parameter=statistic"><span>Statistics</span></a></li>
        <li><a id="walletuser" href="${requestScope['javax.servlet.forward.request_uri']}?parameter=wallets" onclick=""><span>Wallets</span></a>
        </li>
        <c:if test="${sessionScope.user.rights == 1}">
            <li><a href="/printallusers" ><span>List Users</span></a></li>
        </c:if>
        <li><a href="logout"><span>Logout</span></a></li>
    </ul>
    <div style="float: right; color: #00aa2b; margin-top: -3.5em; "><h1>${sessionScope.user.username}</h1>
    </div>
</div>
<div id="tableprint"></div>


<c:if test="${param.parameter !=null }">
    <jsp:include page="${param.parameter}.jsp"/>
</c:if>
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
        <input type="text" name="categoryId"/>
    </div>
    <div class="field-wrap">
        <label>
            WalletId<span class="req">*</span>
        </label>
        <input type="text" name="walletId"/>
    </div>
    <button class="button button-block"/>
    Insert</button>
</form>


<script type="text/javascript"
        src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        var onDeleteClick = function (e) {
            e.preventDefault();
            var user_id = this.getProperty('data-id');
//                this.getProperty('data-id')
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
//        $(".userdel").click(function (e) {
////            var user_id = $(this).data("id");
//            var user_id = e;
//
////                this.getProperty('data-user-id')
//            alert("u id " + user_id);
//            return;
//            $.ajax({
//                url: 'PrintAllUserWithAjax',
//                success: function (data) {
////                    document.getElementById("tableprint").innerHTML = data;
//                    $("#tableprint").html(data);
//                    console.log($(".optionsAdmin").length);
//                    $(".optionsAdmin").click(onDeleteClick);
//                }
//            });
//        });
        $("#userslist").click(function () {
            $.ajax({
                url: 'PrintAllUserWithAjax',
                success: function (data) {
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
