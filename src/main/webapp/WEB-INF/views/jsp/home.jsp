<%@ page import="java.io.FileNotFoundException" %><%--
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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
    <%--<style>--%>
        <%--/* Set height of the grid so .sidenav can be 100% (adjust if needed) */--%>
        <%--.row.content {--%>
            <%--height: 1500px--%>
        <%--}--%>

        <%--/* Set gray background color and 100% height */--%>
        <%--.sidenav {--%>
            <%--background-color: #5aeec5;--%>
            <%--height: 100%;--%>
            <%--padding: 0;!important;--%>
            <%--float: left;!important;--%>
            <%--clear:both; !important;--%>
        <%--}--%>

        <%--/* Set black background color, white text and some padding */--%>
        <%--footer {--%>
            <%--background-color: #555;--%>
            <%--color: #60ff16;--%>
            <%--padding: 15px;--%>
        <%--}--%>

        <%--/* On small screens, set height to 'auto' for sidenav and grid */--%>
        <%--@media screen and (max-width: 767px) {--%>
            <%--.sidenav {--%>
                <%--height: auto;--%>
                <%--padding: 0;--%>
            <%--}--%>

            <%--.row.content {--%>
                <%--height: auto;--%>
            <%--}--%>
        <%--}--%>
    <%--</style>--%>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
        .row.content {
            height: 100%;
        }

        /* Set gray background color and 100% height */
        .sidenav {
            background-color: #8bb5b9;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: #60ff16;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }

            .row.content {
                height: auto;
            }
        }

        #myheader{
            width: 100%;
            height: 4em;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: medium;
            background-color: #31a2b9;
            color: black;
            clear:both;
        }
    </style>
</head>
<body>

<c:if test="${empty sessionScope.user.username}">
    <c:redirect url="index"/>
</c:if>
<%--<div id="header">--%>
    <%--<ul id="menu">--%>
        <%--<li><a href="${requestScope['javax.servlet.forward.request_uri']}?parameter=transaction"><span>Home</span></a>--%>
        <%--</li>--%>
        <%--<li>--%>
            <%--<a href="${requestScope['javax.servlet.forward.request_uri']}?parameter=statistic"><span>Statistics</span></a>--%>
        <%--</li>--%>
        <%--<li><a id="walletuser" href="${requestScope['javax.servlet.forward.request_uri']}?parameter=wallets" onclick=""><span>Wallets</span></a>--%>
        <%--</li>--%>
        <%--<c:if test="${sessionScope.user.rights == 1}">--%>
            <%--<li><a href="/printallusers"><span>List Users</span></a></li>--%>
        <%--</c:if>--%>
        <%--<li><a href="logout"><span>Logout</span></a></li>--%>
    <%--</ul>--%>
    <%--<div style="float: right; color: #00aa2b; margin-top: -3.5em; "><h1>${sessionScope.user.username}</h1>--%>
    <%--</div>--%>
<%--</div>--%>
<div id="myheader"> <h>FINANCE TRACKER</h></div>
<div class="row content">
    <div class="col-sm-3 sidenav">
        <h4>Dashboard</h4>
        <ul class="nav nav-pills nav-stacked">
            <li <c:if test="${param.section == 'section1'}">class="active"
            </c:if><a href="?section=section1">Home</a></li>
            <li <c:if test="${param.section == 'section2'}">class="active" </c:if>><a href="?parameter=statistic&section=section2">Statistic</a>
            </li>
            <li <c:if test="${param.section == 'section3'}">class="active" </c:if>><a href="?parameter=transaction&section=section3">Transaction</a>
            </li>
            <c:if test="${sessionScope.user.rights == 1}">
                <li <c:if test="${param.section == 'section4'}">class="active" </c:if>><a href="?parameter=printallusers&section=section5"><span>Users</span></a></li>
            </c:if>
            <li <c:if test="${param.section == 'section4'}">class="active" </c:if>><a href="?section=section4">Photos</a>
            </li>
            <li <c:if test="${param.section == 'section5'}">class="active" </c:if>><a href="?parameter=logout&section=section5">Logout</a>
            </li>
            <li <c:if test="${param.section == 'section6'}">class="active" </c:if>><a href="?section=section6">Photos</a>
            </li>


            <li><a href="logout"><span>Logout</span></a></li>


            <li <c:if test="${param.section == 'section7'}">class="active" </c:if>><a href="?section=section7">Photos</a>
            </li>
            <li <c:if test="${param.section == 'section8'}">class="active" </c:if>><a href="?section=section8">Photos</a>
            </li>
        </ul>
        <br>
    </div>

<%--<div class="row content">--%>
    <%--<div class="col-sm-3 sidenav">--%>
        <%--<h4>Dashboard</h4>--%>
        <%--<ul class="nav nav-pills nav-stacked">--%>
            <%--<li <c:if test="${param.section == 'section1'}">class="active"--%>
            <%--</c:if><a href="?section=section1">Home</a></li>--%>
            <%--<li <c:if test="${param.section == 'section2'}">class="active" </c:if>><a href="?section=section2">Friends</a>--%>
            <%--</li>--%>
            <%--<li <c:if test="${param.section == 'section3'}">class="active" </c:if>><a href="?section=section3">Family</a>--%>
            <%--</li>--%>
            <%--<li <c:if test="${param.section == 'section4'}">class="active" </c:if>><a href="?section=section4">Photos</a>--%>
            <%--</li>--%>
        <%--</ul>--%>
        <%--<br>--%>
    <%--</div>--%>
<%--</div>--%>

<div id="tableprint" style="float:right;"></div>
</div>
<%try{%>
<c:if test="${param.parameter !=null }">
    <jsp:include page="${param.parameter}.jsp"/>
</c:if>
<%}catch( Exception e){
    System.out.println("no found");
}
%>
walllet


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

<c:set var="count" value="0" scope="page"/>

TRANSACTIONSS
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
            <c:forEach items="${wallets}" var="w">
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
            <c:forEach items="${wallets}" var="w">
                <option value="${w.walletId}"> ${w.name}</option>
            </c:forEach>
        </select>
    </div>
    <button class="button button-block"/>
    Insert</button>
</form>


CATEGORIII


<form method="post" action="addcategory">
    <div class="field-wrap">
        <label>
            Wallet Name<span class="req">*</span>
        </label>
        <input type="text" name="name" />
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
