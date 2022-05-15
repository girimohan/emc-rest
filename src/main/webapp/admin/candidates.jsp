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
<main>
<div class="pg-m">

<c:url var="add" value="/admin/candidates/add">
	</c:url>
			
		<p class="breadcrumb"><a href=""><i class="fa-solid fa-arrow-left"></i> Dashboard</a></p>
        <h1 class="pg-heading"><i class="fa-solid fa-person"></i> Candidates</h1>
        <a href="${add}"><button class=" btn addnew btn-prim"> NEW CANDIDATE </button></a>
		
		</div>
<div class ="datatable">
		
	<table id ="table_id" class="display">
		<thead>
		<tr>
	  		<th>Name</th>
	  		<th>Party</th>
	  		<th>Action</th>
	  		<th>Survey</th>
  		</tr>
		</thead>
	
		<tbody>
		
		<c:forEach var="candidates" items="${CANDIDATES_LIST}">
	  	
		  	<tr>
		  		<td> ${candidates.firstname} ${candidates.surname}</td>
		  		<td> ${candidates.party.party}</td>
				<td>
				<a href=""><button class=" btn btn-prim">UPDATE</button></a>
		  		<a href="/admin/candidates/delete?id=${candidates.candidateId}" onclick="return confirm('Are you sure you want to delete?');"><button class=" btn btn-danger">DELETE</button></a>
		  		</td>
		  		<td>
				<a href="/admin/candidate/questions?id=${candidates.candidateId}"><button class=" btn btn-prim">QUESTIONS</button></a>
		  		<a href="/admin/candidate/answers?id=${candidates.candidateId}"><button class=" btn btn-danger">ANSWERS</button></a>
		  		</td>
		  	</tr>
  
  		</c:forEach>
  	
  		</tbody>
   	</table>
	</div>
</main>
</body>
</html>