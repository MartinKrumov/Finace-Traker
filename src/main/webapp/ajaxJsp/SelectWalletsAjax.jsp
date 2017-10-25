<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.10.2017 г.
  Time: 15:36 ч.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.dao.classes.WalletDAO" %>
<%@ page session="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<table border =1 >
    <tr>
        <th>Wallet ID</th>
        <th>Name</th>
        <th>Amount</th>
        <th>User ID</th>
    </tr>
    <%
        ResultSet set = WalletDAO.selectUserWallets((long) request.getSession(false).getAttribute("user_id"));
        while ( set.next() ) {
            int user_id = set.getInt("wallet_id");%>
    <tr>
        <td><%= user_id%>
        </td>
        <td><%= set.getString("name")%>
        </td>
        <td><%= set.getInt("amount")%>
        </td>
        <td><%= set.getInt("user_id")%>
        </td>
    </tr>
    <% } %>
</table>
</html>
