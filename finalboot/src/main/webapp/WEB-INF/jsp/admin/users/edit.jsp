
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
<form style="max-width: 400px; margin:auto;" action="${action}/${user.id}" method="post" class="form-horizontal">
<h1 class="text-center">${title}</h1>
<div class="form-group">
    <label for="name">Jméno:</label>
    <input type="text" class="form-control" name="name" value="${user.name}" required>
  </div>
  
  <div class="form-group">
    <label for="surname">Příjmení:</label>
    <input type="text" class="form-control" name="surname" value="${user.surname}" required>
  </div>
  <div class="form-group">
    <label for="username">Uživatelské jméno:</label>
    <input type="text" class="form-control" name="username" value="${user.username}" required>
  </div>
  <div class="form-group">
    <label for="password">Heslo:</label>
    <input type="password" class="form-control" name="password" value="${user.password}" required>
  </div>
  <div class="form-group">
    <label for="email">Email:</label>
    <input type="email" class="form-control" name="email" value="${user.email}" required>
  </div>
  <div class="form-group">
    <label for="street">Ulice:</label>
    <input type="text" class="form-control" name="street" value="${user.address.street}" required>
  </div>
  <div class="form-group">
    <label for="city">Město:</label>
    <input type="text" class="form-control" name="city" value="${user.address.city}" required>
  </div>
    <div class="form-group">
    <label for=postal_code>PSČ:</label>
    <input type="text" class="form-control" name="postal_code" value="${user.address.postal_code}" required>
  </div>
  <div class="radio">
  <label><input type="radio" name="userType" value="player" <c:if test="${user.getRole().getUserType().ordinal() == 0 || empty user}">checked="checked"</c:if> >Hráč</label>
	</div>
	<div class="radio">
  <label><input type="radio" name="userType" value="admin" <c:if test="${user.getRole().getUserType().ordinal() == 1}">checked="checked"</c:if>>Administrátor</label>
	</div>
	<div class="radio">
  <label><input type="radio" name="userType" value="forbidden" <c:if test="${user.getRole().getUserType().ordinal() == 2}">checked="checked"</c:if>>Zakázán</label>
	</div>
	<br>
	<div class="radio">
  <label><input type="radio" name="ticketType" value="free" <c:if test="${user.getTicket().getTicketType().ordinal() == 0 || empty user}">checked="checked"</c:if> >Volná permice</label>
	</div>
	<div class="radio">
  <label><input type="radio" name="ticketType" value="paid" <c:if test="${user.getTicket().getTicketType().ordinal() == 1}">checked="checked"</c:if>>Jednotková permice</label>
	</div>
  

	<button type="submit" class="btn btn-default">Uložit</button>
                
        
</form>




		
		


		

</body>
</html>
