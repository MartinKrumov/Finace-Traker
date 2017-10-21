<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link text="text/css" rel="stylesheet" href="css/jspstyle.css">
<html>
<head>
    <meta charset="UTF-8">

    <title>Transparent login form</title>


        <%
long id = (long) 0;
String username = (String) "";
if(request.getSession(false) != null){
	 id = (long) request.getSession(false).getAttribute("id");
	 username = (String) request.getSession(false).getAttribute("email");
	 %> v iff<%
}else{
// 	resffponse.setHeader("Refresh", "0; URL=http://localhost:8080/Finance-Tracker/index.jsp");
%> v else<%	
response.sendRedirect("./index.html");
}
%>


<body>
<header>
    <ul>
        <li><%=id%>
        </li>
        <li><%=username%>
        </li>

        <li><a href="logout">Logout</a></li>
    </ul>
</header>


</body>
</html>
