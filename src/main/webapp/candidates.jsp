<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Setting that the core tag library can be used in this JSP file -->    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Candidates</title>
<style type="text/css">
.flex-container {
    display: flex;
}

.flex-child {
    flex: 1;
    margin-bottom:10px;
}  

.flex-child:first-child {
    margin-right: 10px;
    
} 

.flex-child img {
	max-width:250px;
	max-height:250px;
}
</style>
</head>
<body>
 <c:forEach var="candidate" items="${requestScope.CANDIDATES_LIST}">
	<div class="flex-container">
		<div class="flex-child">
			<h2>
				<b>${candidate.firstname} ${candidate.surname} </b> 
			</h2>
			<h3>${candidate.party.party}</h3>
			<p> <b>Ideology:</b> ${candidate.ideology}</p>
			<p> <b>Motive:</b> ${candidate.motive} </p>
		</div>
		<div class="flex-child">
			<c:choose>
				<c:when test="${not empty candidate.img}">
					<img src="http://127.0.0.1:8080/img/candidates/${candidate.img}" alt="${candidate.firstname} ${candidate.surname}">
				</c:when>
				<c:otherwise>
					<img src="http://127.0.0.1:8080/img/candidates/default.jpg" alt="default image">
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</c:forEach>
</body>
</html>