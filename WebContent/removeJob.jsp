<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Remove Job</h1>
	<c:if test="${error != null}">
		<h1 style="color: red;">Incorrect ID or Password</h1>
		<br/>
		<br/>
	</c:if>
	<br />
	<form action="/JobPortal/RemoveJob" method="POST">
		JOD ID: <input type = "text" name = "id"><br/>
		Password: <input type = "text" name = "password"><br/>
		<input type = "submit" value = "submit"/>
	</form>
</body>
</html>