
<%@page import="finalppro.service.UserService"%>
<%@page import="finalppro.dao.UserRepository"%>
<%@page import="finalppro.model.UserSession"%>
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
	<script type="text/javascript" src="static/js/jquery-ui.min.js"></script>
	<link href="/static/css/jquery-ui.min.css" rel="stylesheet">
	
	
	
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/style.css" rel="stylesheet">
	
	<link href="/static/css/AdminLTE.css" rel="stylesheet">
	
</head>
<body>

<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/admin" class="navbar-brand">Rezervace hřiště - administrace</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
				<li><a href="/admin/users">Správa uživatelů</a></li>
				<li><a href="/admin/courts">Správa kurtů</a></li>
				<li><a href="/admin/tickets">Permice</a></li>
					<li>
					<c:choose>
					 <c:when test="${empty userSession}">
					 	<a style="color:white;" class="btn btn-primary" href="/admin/login">Přihlásit se</a>
					 </c:when>
					 <c:otherwise>
					 	<a style="color:white;" id="login-text" class="btn btn-primary" href="/logout">Odhlásit se (přihlášen jako: <% UserSession us = (UserSession)session.getAttribute("userSession"); out.print(us.getName() + " " + us.getSurname()); %>)</a>
					 </c:otherwise>
					</c:choose>
					</li>
					<li>
					</li>
					
				</ul>
			</div>
		</div>
	</div>
	
		
		
<div class="text-center" style="padding:50px 0">
	
	<c:if test="${not empty message}" >
<div class="alert alert-warning">
	
	${message}
	
</div>
	</c:if>  
	<h1>Přehled kurtů</h1>
	
	<a href="/admin/courts/create"><button type="button" class="btn btn-primary">Přidat kurt</button></a>
	
</div>
<table class="table table-striped mytable ">
    <thead>
      <tr>
        <th>Název</th>
        <th>Ulice</th>
        <th>Aktivní</th>
        <th>Akce</th>
      </tr>
    </thead>
    <tbody>

<c:forEach var="court" items="${courts}">
      <tr>
        <td>${court.name}</td>
        <td>${court.address.street} ${court.address.city}</td>
        <td>${court.active}</td>
        <td>
        	<a href="/admin/courts/edit/${court.id}"><span class="glyphicon glyphicon-pencil"> </span></a>
        	<a href="/admin/courts/delete/${court.id}"><span class="glyphicon glyphicon-trash"> </span></a>
        </td>
      </tr>
</c:forEach>

    </tbody>
  </table>
</body>
</html>
