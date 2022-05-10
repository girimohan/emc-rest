<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Candidate</title>
</head>
<body>

<form class="create-form" action ="/savecandidate" method="post" enctype = "multipart/form-data">
		
		<label for="title" >Surname:</label> <br>
		<input class ="txtin" id="surname" type="text" name="surname" size="90" required/><br>
					
		<label for ="title">Firstname:</label><br>
		<input class ="txtin" id="firstname" type="text" name="firstname" size="90" required/><br>
		
	
		<label>Profession:</label><br>
		<input class ="txtin" id="title" type="text" name="profession" size="90" required/><br>
	
		<label>Age:</label><br>
		<input class="txtin" id="title" type="number"name="age" size="90" required/><br>
		
		<label for="party">Party:</label><br>

			<select name="party" id="party">
			  <c:forEach var="parties" items="${PARTIES_LIST}">
			  <option value="${parties.partyId}"> ${parties.party}</option>
			  
			  </c:forEach>
			</select>  <br>
	 
	 <label for="ideology">Ideology</label> <br>
		<textarea id="" name="ideology" rows="4" cols="50">
	</textarea> <br>
	
<label for="motive">Motive</label> <br>
<textarea id="motive" name="motive" rows="4" cols="50">
</textarea> <br>

<label for="kuva">Image</label> <br>
<input type="file" id="image" name="image" accept=".jpg" /> <br><br>
	
		<input class="btn btn-prim"  type="submit" value="Save" class="save"/>

	</form>

</body>
</html>