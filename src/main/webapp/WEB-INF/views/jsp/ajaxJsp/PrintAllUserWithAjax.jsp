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
</head>
<body>

<c:if test="${!empty allUsers && allUsers != null}">
    <div class="container">
        <table class="table">
            <c:forEach items="${allUsers}" var="u">
                <tr>
                    <td class="bg-success">${u.userId}</td>
                    <td>${u.username}</td>
                    <td>${u.email}</td>
                    <td>${u.firstName}</td>
                    <td>${u.lastName}</td>
                    <td>${u.blocked}</td>
                    <td>${u.rights}</td>
                    <td>${u.date}</td>
                    <%--data-id = "${u.userId}"--%>
                    <td><button class="userdel" data-id="${u.userId}"  >Del</button></td>
                </tr>

            </c:forEach>
        </table>
    </div>
</c:if>
<script type="text/javascript"
        src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

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
