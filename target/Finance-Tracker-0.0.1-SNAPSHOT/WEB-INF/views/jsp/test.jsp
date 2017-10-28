<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo page</title>
</head>


<body>
	<h1> ${kekst} </h1>
	<p> Last added product: ${product.name}</p>
	<p> Last added product: ${myuser.username}</p>
	<p> Last added product: ${myuser.password}</p>
</body>
</html>