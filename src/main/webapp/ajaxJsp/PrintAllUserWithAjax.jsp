<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.dao.classes.UserDAO" %>
<%@ page session="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<table border=1>
    <tr>
        <th>User ID</th>
        <th>Password</th>
        <th>Blocked</th>
        <th>Rights</th>
        <th>First name</th>
        <th>Email</th>
        <th>Options</th>
    </tr>
    <%
        ResultSet set = UserDAO.selecttUsers();
        while ( set.next() ) {
            int user_id = set.getInt("user_id");%>
    <tr>
        <td><%= user_id%>
        </td>
        <td><%= set.getString("password")%>
        </td>
        <td><%= set.getInt("blocked")%>
        </td>
        <td><%= set.getInt("rights")%>
        </td>
        <td><%= set.getString("first_name")%>
        </td>
        <td><%= set.getString("email")%>
        </td>
        <td>
            <button style="color:white; background-color: black; border-radius: 1em;" id="optionsAdmin"
                    onclick="delUser(<%= user_id%>)">DEL
            </button>
        </td>
    </tr>
    <% } %>
</table>
</html>
