
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<html>
<head>
	<script
			  src="https://code.jquery.com/jquery-2.2.4.min.js"
			  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
			  crossorigin="anonymous"></script>
	
	<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
	
	<!-- Jquery UI -->
	<script type="text/javascript" src="/static/js/jquery-ui.min.js"></script>
	<link href="/static/css/jquery-ui.min.css" rel="stylesheet">
	
	
	
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/style.css" rel="stylesheet">
	
	
</head>
<body>

<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="login-admin" class="navbar-brand">Přihlášení</a>			
			<div class="navbar-collapse collapse">
			</div>
		</div>
	</div>
	
		
		
<div class="text-center" style="padding:50px 0">
	
	<c:if test="${not empty message}" >
<div class="alert alert-danger">
	
	${message}
	
</div>
	</c:if>  
</div>
<form style="max-width: 400px; margin:auto;" action="/admin/users/edit/${user.id}" method="post" class="form-horizontal">
<h1 class="text-center">Editace uživatele</h1>
<div class="form-group">
    <label for="name">Jméno:</label>
    <input type="text" class="form-control" name="name" value="${user.name}">
  </div>
  
  <div class="form-group">
    <label for="name">Příjmení:</label>
    <input type="text" class="form-control" name="surname" value="${user.surname}">
  </div>
  
  

	<button type="submit" class="btn btn-default">Uložit</button>
                
        
</form>




		
		


		

</body>
</html>
