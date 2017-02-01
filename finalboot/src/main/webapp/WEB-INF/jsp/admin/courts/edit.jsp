
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
			<a href="/admin" class="navbar-brand">Rezervace hřiště - administrace</a>			
			<div class="navbar-collapse collapse">
			</div>
		</div>
	</div>
	
		
		
<div class="text-center" style="padding:50px 0">
	
	<c:if test="${not empty message}" >
<div class="alert alert-warning">
	
	${message}
	
</div>
	</c:if>  
</div>
<form style="max-width: 400px; margin:auto;" action="${action}/${court.id}" method="post" class="form-horizontal">
<h1 class="text-center">${title}</h1>
<div class="form-group">
    <label for="name">Název:</label>
    <input type="text" class="form-control" name="name" value="${court.name}" required>
  </div>
  
  <div class="form-group">
    <label for="street">Ulice:</label>
    <input type="text" class="form-control" name="street" value="${court.address.street}" required>
  </div>
  <div class="form-group">
    <label for="city">Město:</label>
    <input type="text" class="form-control" name="city" value="${court.address.city}" required>
  </div>
   <div class="form-group">
    <label for="postal_code">PSČ:</label>
    <input type="text" class="form-control" name="postal_code" value="${court.address.postal_code}" required>
  </div>
  <div class="radio">
  <label><input type="radio" name="active" value="1" <c:if test="${court.active == true || empty court}">checked="checked"</c:if> >Aktivní</label>
	</div>
	<div class="radio">
  <label><input type="radio" name="active" value="0" <c:if test="${court.active == false}">checked="checked"</c:if>>Neaktivní</label>
	</div>
  

	<button type="submit" class="btn btn-default">Uložit</button>
                
        
</form>




		
		


		

</body>
</html>
