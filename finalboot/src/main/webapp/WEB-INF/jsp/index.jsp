
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
	<script
			  src="https://code.jquery.com/jquery-2.2.4.min.js"
			  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
			  crossorigin="anonymous"></script>
	
	<script type="text/javascript" src="bootstrap.min.js"></script>
	
	
	<link href="static/css/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/style.css" rel="stylesheet">
	
	<!-- Fullcalendar -->
	<script type="text/javascript" src="static/js/moment.js"></script>
	<link href="static/css/fullcalendar.min.css" rel="stylesheet">
	<script type="text/javascript" src="static/js/fullcalendar.min.js"></script>
	<script type="text/javascript" src="static/js/cs.js"></script>
	
	
</head>
<body>

<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/" class="navbar-brand">Rezervace hřiště</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li> 
						<div class="dropdown" >
  							<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Výběr kurtu<span class="caret"></span></button>
  							<ul class="dropdown-menu">
  							
  							<c:forEach var="court" items="${courts}">
								<li><a href="#">${court.name} ${court.active}</a></li>
							</c:forEach>    							
  							</ul>
						</div> 
					</li>
					<li><a href="#">Přihlásit se</a></li>
					
				</ul>
			</div>
		</div>
	</div>

		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.address.city}</td>
			</tr>
		</c:forEach>
		
		<div id='calendar'></div>
    <div class="modal"><!-- Place at bottom of page --></div>
<script>
$(document).ready(function() {
    $body = $("body");     
    
    //slouží k zobrazení loading gifu    
    $(document).on({
        ajaxStart: function() { $body.addClass("loading");    },
        ajaxStop: function() { $body.removeClass("loading"); }    
    });
    
    //nastavení kalendáře
    $('#calendar').fullCalendar({
        header: {
        left: 'today',
        center: 'prev title next',
        right: 'month'
    },
    
    minTime: '08:00:00',
    maxTime: '22:00:00',
    axisFormat: 'H:mm',
    slotDuration: '01:00:00',
    eventLimit: false, // for all non-agenda views. Aby se eventy shlukly
    selectable: false,
    eventOrder: "number,title",
   views: {
        week: { //jak má vypadat týdenní formát 
            titleFormat: 'D. M. YYYY'
        }
},
        
    allDaySlot: true, //vyhodit listu s celodenimi udalostmi
    editable: false,
    events: {
        url: 'fillcalendar',
    },
    eventClick: function(calEvent, jsEvent, view) {
        return false;
    },

    //zmena view - prepnuti tydne, mesicu..
    viewRender: function (view, element) {
             //refreshCalendar(String(view.start), String(view.end));
        //refreshCalendar(view.start.toISOString(), view.end.toISOString());
        
            /*getData(view.start.toISOString(),
                    view.end.toISOString());*/
          
        
        
        
        }
                        
    });
    
});


</script>


</body>
</html>
