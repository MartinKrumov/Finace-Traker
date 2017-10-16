<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	hetrereraer
	</br>
	<%
		String str = "pesho";
	%>


	<%@ page import="java.util.List" %>
	<%@ page import="java.util.ArrayList" %>
	<%
		// Get the global list of shared items
// 		List<String> sharedItems = new ArrayList<String>();
// 		synchronized (application) {
// 			sharedItems = (List<String>) application.getAttribute("result");
// 			if (sharedItems == null) {
// 				// 				sharedItems = new ArrayList<String>();
// 				application.setAttribute("items", sharedItems);
// 			}
// 		}
// 		for (String s : sharedItems) {
// 			out.print(s);
// 		}
// 		// Append the new item (if exists)
// 		String newItem = request.getParameter("item");
// 		if (newItem != null)
// 			sharedItems.add(newItem);
	%>
	<a href=""><%=		request.getSession(false).getAttribute("username")
%></a>

</body>
</html>