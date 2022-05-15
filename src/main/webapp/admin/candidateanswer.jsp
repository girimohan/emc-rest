<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h2>Answers</h2>
<br>
<c:choose>
<c:when test="${empty requestScope.CANDIDATE_ANSWERS}">
<h3>Not given any answers</h3>
<a href="/admin/candidate/questions?id=${requestScope.CANDIDATE_INFO.candidateId } "><button class=" btn btn-danger"> GIVE ANSWERS</button></a>
</c:when>
<c:otherwise>
  <div class ="datatable">
		
	<table id ="table_id" class="display">
		<thead>
		<tr>
	  		<th>S.No</th>
	  		<th>Question</th>
	  		<th>Answer</th>
	  		<th>Action</th>
  		</tr>
		</thead>
	
		<tbody>
   <c:set var="count" value="1" scope="page" />
	<c:forEach var="candidateAnswer" items="${requestScope.CANDIDATE_ANSWERS}">
	<tr>
	  <td>
		<b><c:out value="${count}"/> </b> 
		
		</td>
		<td>
		${candidateAnswer.question.question}
		</td>
		<td>
		<c:choose>
		
		<c:when test="${candidateAnswer.answer == '1' }">
			Strongly Agree
		</c:when>
		<c:when test="${candidateAnswer.answer == '2' }">
			Agree
		</c:when>
		<c:when test="${candidateAnswer.answer == '3' }">
			I can't say
		</c:when>
		<c:when test="${candidateAnswer.answer == '4' }">
			 Disagree
		</c:when>
		<c:otherwise>
			Strongly Disagree
		</c:otherwise>
			
		
		</c:choose>
		</td>
		
		<td>
		<a href="/admin/candidate/answer/delete?id=${candidateAnswer.id}" onclick="return confirm('Are you sure you want to delete?');"><button class=" btn btn-danger">DELETE</button></a>
		</td>
			
			<c:set var="count" value="${count + 1}" scope="page"/>
	</tr>
	</c:forEach>
	
	</tbody>
	</table>
	<a href="/admin/candidate/answers/edit?id=${candidateAnswer.id}"><button class=" btn btn-danger">EDIT ANSWERS</button></a>
	</div>
</c:otherwise>
</c:choose>
</body>
</html>