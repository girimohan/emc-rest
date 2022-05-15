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
<h4>CandidateId: ${requestScope.CANDIDATE_INFO.candidateId } </h4>
<h4>CandidateName:${requestScope.CANDIDATE_INFO.firstname } ${requestScope.CANDIDATE_INFO.surname } </h4>
<br>
<c:choose>
<c:when test="${empty requestScope.QUESTIONS_LIST}">
<h3>No New Questions to Answer</h3>
<a href="/admin/candidate/answers?id=${requestScope.CANDIDATE_INFO.candidateId }"><button class=" btn btn-danger"> VIEW ANSWERS</button></a>
</c:when>
<c:otherwise>
       
 
<form method="post" action="/admin/candidate/answer/insert?id=${requestScope.CANDIDATE_INFO.candidateId}">
	<c:set var="count" value="1" scope="page" />
	<c:forEach var="question" items="${requestScope.QUESTIONS_LIST}">
		<b><c:out value="${count}"/>: </b> ${question.question} <br> <br>
		    <input type="hidden" value="${question.questionId}" name ="questionId">
		    
			<input type="radio" id="option1" name="question_${question.questionId }" value="1">
			<label for="option${count}"> Strongly Agree</label><br> 
			
			<input type="radio" id="option2" name="question_${question.questionId }" value="2">
			<label for="option${count}"> Agree</label> <br>
			
			<input type="radio" id="option3" name="question_${question.questionId }" value="3">
			<label for="option${count}"> I can't say</label> <br>
			
			<input type="radio" id="option4" name="question_${question.questionId }" value="4">
			<label for="option"> Disagree</label> <br>
			
			<input type="radio" id="option5" name="question_${question.questionId }" value="5">
			<label for="option${count}"> Strongly Disagree</label> <br> <br>
			
			<c:set var="count" value="${count + 1}" scope="page"/>
	</c:forEach>

	<input type="submit" value="Submit" class="button">
</form>

</c:otherwise>
</c:choose>
</body>
</html>