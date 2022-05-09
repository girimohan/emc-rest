<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action='../add' method='post'>
Question:<input type='text' name='question' value=''> </br></br>
<input type='submit' name='ok' value='OK'>
</form>
<ol>
<c:forEach var="quest" items="${requestScope.questionlist }">
	<li>${quest} <a href='../deletequestion?id=${quest.questionId}'>Delete</a> <a href='../readtoupdatequestion?id=${quest.questionId}'>Update</a>
</c:forEach>
</ol>

</body>
</html>