<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Candidate</title>
</head>
<body>
<h2>Update Candidate</h2>
<form class="create-form" action ="/admin/candidate/update?id=${requestScope.CANDIDATE_INFO.candidateId }" method="post" enctype = "multipart/form-data">
		
		<label for="title" >Surname:</label> <br>
		<input class ="txtin" id="surname" type="text" name="surname" value="${requestScope.CANDIDATE_INFO.surname }"size="90" required/><br>
					
		<label for ="title">Firstname:</label><br>
		<input class ="txtin" id="firstname" type="text" name="firstname" value="${requestScope.CANDIDATE_INFO.firstname }" size="90" required/><br>
		
	
		<label>Profession:</label><br>
		<input class ="txtin" id="title" type="text" name="profession" value="${requestScope.CANDIDATE_INFO.profession }" size="90" required/><br>
	
		<label>Age:</label><br>
		<input class="txtin" id="title" type="number"name="age" value="${requestScope.CANDIDATE_INFO.age }"size="90" required/><br>
		
		<label for="party">Party:</label><br>

			<select name="party" id="party">
			  <c:forEach var="parties" items="${PARTIES_LIST}">
			  <option value="${parties.partyId}" <c:if test="${parties.partyId==requestScope.CANDIDATE_INFO.party.partyId}"> selected </c:if>>${parties.party}</option>
			  
			  </c:forEach>
			</select>  <br>
	 
	 <label for="ideology">Ideology</label> <br>
	<textarea id="" name="ideology" rows="4" cols="50">${requestScope.CANDIDATE_INFO.ideology }</textarea> <br>
	
<label for="motive">Motive</label> <br>
<textarea id="motive" name="motive" rows="4" cols="50">
${requestScope.CANDIDATE_INFO.motive }
</textarea> <br>

<label for="Image">Image</label> <br>
<input type="file" id="image" name="image" accept="image/*" /> ${requestScope.CANDIDATE_INFO.img}

<input type="hidden" id="prevImg" name="prevImg" value="${requestScope.CANDIDATE_INFO.img}" /> 


<br><br>
	
		<input class="btn btn-prim"  type="submit" value="Update" class="save"/>

	</form>

</body>
</html>