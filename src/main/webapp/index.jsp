
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>

  <body onload="noBack();"
    onload="if (event.persisted) noBack();" onunload="">
<%-- <jsp:scriptlet> --%>
<h1>
	user id
	<%=id%>
	</br>
</h1>
<h1>
	user name
	<%=username%>
	</br>
</h1>

<a href="logout">Logout</a>
</body>
</html>
