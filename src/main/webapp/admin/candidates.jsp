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

<div class ="datatable">
		
	<table id ="table_id" class="display">
		<thead>
		<tr>
	  		<th>Name</th>
	  		<th>Party</th>
	  		<th>Action</th>
  		</tr>
		</thead>
	
		<tbody>
		
		<c:forEach var="candidates" items="${CANDIDATES_LIST}">
	  	
		  	<tr>
		  		<td> ${candidates.firstname} ${candidates.surname}</td>
		  		<td> ${candidates.party.party}</td>
				<td>
				<a href=""><button class=" btn btn-prim">UPDATE</button></a>
		  		<a href="" onclick="return confirm('Are you sure you want to delete?');"><button class=" btn btn-danger">DELETE</button></a>
		  		</td>
		  	</tr>
  
  		</c:forEach>
  	
  		</tbody>
   	</table>
	</div>

</body>
</html>