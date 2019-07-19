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
	<h1>Insert Job information:</h1>
	<br />
	<br />
	Message Body:
	<br />
	<textarea style="font-size: 14pt;" rows="15" cols="70"
		form="postJobForm" name="messageBody"></textarea>
	<br />
	<form action="/JobPortal/PostJob" method="POST" id="postJobForm">
		Job Name: <input type="text" name="jobname"><br /> Poster
		Name: <input type="text" name="postername"><br /> Secret
		Password: <input type="text" name="password"><br /> Contact
		Phone: <input type="text" name="phone"><br /> <input
			type="submit" value="submit" />
	</form>

</body>
</html>