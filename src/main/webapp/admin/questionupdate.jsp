<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action='/admin/question/update?id=${requestScope.QUESTION.questionId}' method='post'>
Question:<input type='text' name='question' value ='${requestScope.QUESTION.question }'> </br></br>
<input type='submit' name='ok' value='OK'>
</form>
</body>
</html>