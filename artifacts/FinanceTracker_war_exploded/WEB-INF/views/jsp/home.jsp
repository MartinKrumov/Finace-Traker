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

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
        .row.content {
            height: 100%;
        }

        .leftcontainer {
            height: 100%;
            background-color: #2f4050;
        }

        /* Set gray background color and 100% height */
        body {
            background-color: #2f4050;
        }

        .sidenav {
            background-color: #2f4050;
            height: 100%;
            width: 25%;
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
                height: 100%;
                padding: 15px;
            }

            .row.content {
                height: 100%;
            }
        }

        #myheader {
            width: 100%;
            height: 4em;
            font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: medium;
            background-color: #0f2034;
            color: #aec8ff;
            clear: both;
        }

        #myheader h {
            margin-left: 30%;
            font-size: 3em;
        }
    </style>
</head>
<body>

<c:if test="${empty sessionScope.user.username}">
    <c:redirect url="index"/>
</c:if>

<div id="myheader">
    <h>
        <img src="https://previews.123rf.com/images/maxborovkov/maxborovkov1007/maxborovkov100700372/7385359-Abstract-modern-3d-FT-logo-Stock-Vector-logo-logos-design.jpg"
             width="60" height="60"/> FINANCE TRACKER
    </h>
</div>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li <c:if test="${param.section == 'section1'}">class="active"</c:if>><a
                    href="home?homepage=homepage">Home</a>
            </li>
            <li <c:if test="${param.section == 'section2'}">class="active" </c:if>><a
                    href="home?parameter=statistic&section=section2">Statistic</a>
            </li>
            <li <c:if test="${param.section == 'section3'}">class="active" </c:if>><a
                    href="home?parameter=transaction">Transaction</a>
            </li>
            <c:if test="${sessionScope.user.rights == 1}">
                <li <c:if test="${param.section == 'section4'}">class="active" </c:if>><a
                        href="printallusers"><span>Users</span></a>
                </li>
            </c:if>

            <li <c:if test="${param.section == 'section6'}">class="active" </c:if>><a href="home?parameter=budgets">Budgets</a>
            </li>
            <li <c:if test="${param.section == 'section6'}">class="active" </c:if>><a href="home?parameter=wallets">Wallets</a>
            </li>
            <li <c:if test="${param.section == 'section5'}">class="active" </c:if>><a
                    href="/logout?section=section5">Logout</a>
            </li>
            <%--<li><a href="logout"><span>Logout</span></a></li>--%>


            <%--<li <c:if test="${param.section == 'section7'}">class="active" </c:if>><a href="?section=section7">Photos</a>--%>
            <%--</li>--%>
            <%--<li <c:if test="${param.section == 'section8'}">class="active" </c:if>><a href="?section=section8">Photos</a>--%>
            <%--</li>--%>
        </ul>
        <%--<br>--%>

    </div>
</nav>
    <%--<div class="col-sm-9">--%>
    <div id="tableprint" style="background-color: rgba(224,255,190,0.73);  width: 100%;height: 100%">
        <%try {%>
        <c:if test="${param.parameter !=null }">
            <jsp:include page="${param.parameter}.jsp"/>
        </c:if>
        <%
            } catch ( Exception e ) {
                System.out.println("no found");
            }
        %>
        <c:if test="${param.homepage =='homepage' }">
            <%--<jsp:include page="${param.parameter}.jsp"/>--%>
            <img style="margin-top:-2em;" src="http://navigatefinancial.com.au/wp-content/uploads/2014/12/investment-stratedgy.jpg"width="100%" height="100%"/>
        </c:if>
    </div>
</div>


<%--</div>--%>

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
<%--<div class="container">--%>

<%--</div>--%>

<%--walllet--%>



<%--<c:set var="count" value="0" scope="page"/>--%>

<%--TRANSACTIONSS--%>
<%--<form commandName="transaction" method="post" action="transactionInsert">--%>
    <%--<div class="field-wrap">--%>
        <%--<label>--%>
            <%--Amount<span class="req">*</span>--%>
        <%--</label>--%>
        <%--<input type="number" name="amount"/>--%>
    <%--</div>--%>
    <%--<div class="field-wrap">--%>
        <%--<label>--%>
            <%--Description<span class="req">*</span>--%>
        <%--</label>--%>
        <%--<input type="text" name="description"/>--%>
    <%--</div>--%>
    <%--<div class="field-wrap">--%>
        <%--<label>--%>
            <%--CategoryId<span class="req">*</span>--%>
        <%--</label>--%>
        <%--<select name="categoryId">--%>
            <%--<c:forEach items="${wallets}" var="w">--%>
                <%--<c:if test="${count == 0}">--%>
                    <%--<c:forEach items="${w.categories}" var="cat">--%>
                        <%--<option value="${cat.categoryId}"> ${cat.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</c:if>--%>
                <%--<c:set var="count" value="${count + 1}" scope="page"/>--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
    <%--</div>--%>
    <%--<div class="field-wrap">--%>
        <%--<label>--%>
            <%--WalletId<span class="req">*</span>--%>
        <%--</label>--%>
        <%--<select name="walletId">--%>
            <%--<c:forEach items="${sessionScope.user.wallets}" var="w">--%>
                <%--<option value="${w.walletId}"> ${w.name}</option>--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
    <%--</div>--%>
    <%--<button class="button button-block"/>--%>
    <%--Insert</button>--%>
<%--</form>--%>


<%--CATEGORIII--%>




<%--<script type="text/javascript"--%>
        <%--src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
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
