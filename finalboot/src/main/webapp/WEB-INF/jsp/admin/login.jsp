
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
	
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	
	<!-- Jquery UI -->
	<script type="text/javascript" src="static/js/jquery-ui.min.js"></script>
	<link href="static/css/jquery-ui.min.css" rel="stylesheet">
	
	
	
	<link href="static/css/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/style.css" rel="stylesheet">
	
	
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
	
<form action="login-admin" method="post">
<div class="container">
    <div class="row">
        <div class="col-md-offset-5 col-md-3">
            <div class="form-login">
            <h1>Přihlašte se</h1>
            <input type="text" class="form-control input-sm chat-input" name="username" placeholder="uživatel" />
            </br>
            <input type="text" class="form-control input-sm chat-input" name="password" placeholder="heslo" />
            </br>
            <div class="wrapper">
            <span class="group-btn">     
            <button type="submit" class="btn btn-primary">Přihlásit se</button>
                
            </span>
            </div>
            </div>
        
        </div>
    </div>
</div>
</form>
	

</div>

		
		


		

</body>
</html>
