<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Questions</title>
</head>
<body>
<h2>Answer Questions</h2>
<br>
<form method="post" action=" ">
	<c:set var="count" value="1" scope="page" />
	<c:forEach var="question" items="${requestScope.QUESTIONS_LIST}">
		<b><c:out value="${count}"/>: </b> ${question.question} <br> <br>
			<input type="radio" id="option1" name="option${count}" value="1">
			<label for="option${count}"> Strongly Agree</label><br> 
			
			<input type="radio" id="option2" name="qoption${count}" value="2">
			<label for="option${count}"> Agree</label> <br>
			
			<input type="radio" id="option3" name="qoption${count}" value="3">
			<label for="option${count}"> I can't say</label> <br>
			
			<input type="radio" id="option4" name="qoption${count}" value="4">
			<label for="option"> Disagree</label> <br>
			
			<input type="radio" id="option5" name="qoption${count}" value="5">
			<label for="option${count}"> Strongly Disagree</label> <br> <br>
			
			<c:set var="count" value="${count + 1}" scope="page"/>
	</c:forEach>

	<input type="submit" value="Submit" class="button">
</form>
</body>
</html>