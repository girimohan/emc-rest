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


<c:if test="${MSG != null}">
         <p class="msg"><c:out value="${MSG}"></c:out><p>
      </c:if>  
      
<div class = "pg-m">
        <p class="breadcrumb"><a href=""><i class="fa-solid fa-arrow-left"></i> Dashboard</a></p>
        <h1 class="pg-heading">Questions<i class="fa-solid fa-question"></i></h1>
        <a href="/admin/questions/add"><button class=" btn addnew btn-prim"> Add Questions </button></a>
    </div>
    
<div class = "datatable">
	<table id = "table_id" class= "display">
	<thead>
	<tr>
	<th> Question </th>
	<th> Action </th>


	</tr>
	</thead>
	<tbody>
	
	<c:forEach var="questions" items="${QUESTIONS_LIST }">
	
		<tr>
		<td> ${questions.question } </td>
		<td>
		<a href = "" ><button class ="btn btn-prim"> UPDATE</button></a>
		<a href = "" onclick = "return confirm('Are you sure you want to delete?')"> <button class= "btn btn-danger">DELETE</button></a>
		</td>
		</tr>


	</c:forEach>


	</tbody>

		

	</table>
</div>

</body>

 

</html>

