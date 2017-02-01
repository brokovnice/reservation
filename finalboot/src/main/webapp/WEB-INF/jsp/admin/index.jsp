
<%@page import="finalppro.model.UserSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	
	<link href="/static/css/AdminLTE.css" rel="stylesheet">
	
	<!-- Noty -->
	<script type="text/javascript" src="/static/js/noty/packaged/jquery.noty.packaged.min.js"></script>
	
</head>
<body>

<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/admin" class="navbar-brand">Rezervace hřiště - administrace</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
				<li><a href="/admin/users">Správa uživatelů</a></li>
				<li><a href="/admin/courts">Správa kurtů</a></li>
				<li><a href="/admin/users">Permice</a></li>
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

	<c:if test="${not empty message}" >
<div class="alert alert-warning">
	
	${message}
	
</div>
	</c:if> 
	
	  <div class="row center-block">
      <a href="/admin/users">
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-aqua">
                <div class="inner">
                  <h3>${activePlayers}</h3>
                   <c:choose>
        <c:when test="${activePlayers == 0}"><p>Aktivních hráčů</p></c:when>
        <c:when test="${activePlayers == 1}"><p>Aktivní hráč</p></c:when>
        <c:when test="${activePlayers > 1 && activePlayers < 5}"><p>Aktivní hráči</p></c:when>
        <c:otherwise><p>Aktivních hráčů</p></c:otherwise>
    </c:choose>
                </div>
                <div class="icon">
                  <i class="ion ion-bag"></i>
                </div>
                
              </div>
            </div><!-- ./col -->
      </a>
      
      <a href="/admin/courts">
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3>${activeCourts}</h3>
                   <c:choose>
        <c:when test="${activeCourts == 0}"><p>Otevřených kurtů</p></c:when>
        <c:when test="${activeCourts == 1}"><p>Otevřený kurt</p></c:when>
        <c:when test="${activeCourts > 1 && activeCourts < 5}"><p>Otevřené kurty</p></c:when>
        <c:otherwise><p>Otevřených kurtů</p></c:otherwise>
    </c:choose>
                </div>
                <div class="icon">
                  <i class="ion ion-bag"></i>
                </div>
                
              </div>
            </div><!-- ./col -->
      </a>
      
      <a href="/">
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-yellow">
                <div class="inner">
                  <h3>${totalReservations}</h3>
                   <c:choose>
        <c:when test="${totalReservations == 0}"><p>Rezervací celkem</p></c:when>
        <c:when test="${totalReservations == 1}"><p>Rezervace celkem</p></c:when>
        <c:when test="${totalReservations > 1 && totalReservations < 5}"><p>Rezervace celkem</p></c:when>
        <c:otherwise><p>Rezervací celkem</p></c:otherwise>
    </c:choose>
                </div>
                <div class="icon">
                  <i class="ion ion-bag"></i>
                </div>
                
              </div>
            </div><!-- ./col -->
      </a>
      
      <a href="/admin/users">
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3>${forbiddenPlayers}</h3>
                   <c:choose>
        <c:when test="${forbiddenPlayers == 0}"><p>Uzamčených účtů</p></c:when>
        <c:when test="${forbiddenPlayers == 1}"><p>Uzamčený účet</p></c:when>
        <c:when test="${forbiddenPlayers > 1 && forbiddenPlayers < 5}"><p>Uzamčené účty</p></c:when>
        <c:otherwise><p>Uzamčených účtů</p></c:otherwise>
    </c:choose>
                </div>
                <div class="icon">
                  <i class="ion ion-bag"></i>
                </div>
                
              </div>
            </div><!-- ./col -->
      </a>
      
      
      </div>

</body>
</html>
