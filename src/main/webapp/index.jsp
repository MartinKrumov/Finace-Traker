<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transparent login form</title>


<%
long id =(long) request.getSession(false).getAttribute("id");
String username =(String) request.getSession(false).getAttribute("username");
%>

<%-- <jsp:scriptlet> --%>
<h1>user id <%=id%> </br></h1>
<h1>user name <%=username%> </br></h1>
</body>
</html>
