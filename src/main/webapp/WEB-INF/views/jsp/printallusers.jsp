<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4.11.2017 г.
  Time: 21:44 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
        .row.content {
            height: 100%;
        }

        body {
            background-color: #d5d6ee;
        }

        .leftcontainer {
            height: 100%;
            background-color: #2f4050;
        }

        /* Set gray background color and 100% height */
        body {
            background-color: #eaebff;
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

        .userdel {
            background-color: #0f2034;
            color: whitesmoke;
            border-radius: 2em;
        }
        table td,th{
            padding-left:8px;
        }
    </style>
</head>
<body>
<div>
    <c:if test="${!empty allUsers && allUsers != null}">
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
                    <%--<li <c:if test="${param.section == 'section6'}">class="active" </c:if>><a href="?section=section6">Photos</a>--%>
                    <%--</li>--%>


                    <%--<li><a href="logout"><span>Logout</span></a></li>--%>


                    <%--<li <c:if test="${param.section == 'section7'}">class="active" </c:if>><a href="?section=section7">Photos</a>--%>
                    <%--</li>--%>
                    <%--<li <c:if test="${param.section == 'section8'}">class="active" </c:if>><a href="?section=section8">Photos</a>--%>
                    <%--</li>--%>
            </ul>
                <%--<br>--%>

        </div>
    </nav>
    <div class="container" style="margin-top: 3em;">

        <table border=1 style="width: 90%; margin: 0 auto; height: auto; ">
            <tr style="height: 3em; ">
                <th>User ID</th>
                <th>Username</th>
                <th>email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Blocked</th>
                <th>Rights</th>
                <th>Date</th>
                <th>Options</th>
            </tr>
            <c:forEach items="${allUsers}" var="u">
                <tr class="bg-success" style="height: 2.5em;">
                    <td>${u.userId}</td>
                    <td>${u.username}</td>
                    <td>${u.email}</td>
                    <td>${u.firstName}</td>
                    <td>${u.lastName}</td>
                    <td>${u.blocked}</td>
                    <td>${u.rights}</td>
                    <td>${u.date}</td>
                    <td>
                        <button class="userdel" data-id="${u.userId} " onclick="">Del</button>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </div>
    </c:if>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script type="text/javascript">

        $(document).ready(function () {


            $("button").click(function () {
                var r = confirm("Delete user ?");
                if (confirm("Delete user ?") == true) {
                    var user_id = $(this).data("id");
                    alert("u id " + user_id);
                    $.ajax({
                        url: 'DelUserFromAdmin',
                        type: 'post',
                        data: {del_user_id: user_id},
                        success: function () {
                            alert("User Deleted !")
                            $parent.hide();
                        }
                    })
                }
            });


            $(".userdel").click(function () {
                var onDeleteClick = function (e) {
                    e.preventDefault();
                    var user_id = $(this).data("id");
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
            });
        });
    </script>
</body>
</html>
