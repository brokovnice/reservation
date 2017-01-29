
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
	
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	
	<!-- Jquery UI -->
	<script type="text/javascript" src="static/js/jquery-ui.min.js"></script>
	<link href="static/css/jquery-ui.min.css" rel="stylesheet">
	
	
	
	<link href="static/css/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/style.css" rel="stylesheet">
	
	<!-- Fullcalendar -->
	<script type="text/javascript" src="static/js/moment.js"></script>
	<link href="static/css/fullcalendar.min.css" rel="stylesheet">
	<script type="text/javascript" src="static/js/fullcalendar.min.js"></script>
	<script type="text/javascript" src="static/js/cs.js"></script>
	
	<!-- Noty -->
	<script type="text/javascript" src="static/js/noty/packaged/jquery.noty.packaged.min.js"></script>
	
	<!-- Timepicker -->
	<script type="text/javascript" src="static/js/jquery.timepicker.min.js"></script>
	<link href="static/css/jquery.timepicker.css" rel="stylesheet">
	
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
  							
  							<c:forEach var="court" items="${courts}" >  								
								<li><a href="#" class="courtItem" data-courtId="${court.id}">${court.name}</a></li>
							</c:forEach>    							
  							</ul>
						</div> 
					</li>
					<li>
					<c:choose>
					 <c:when test="${empty userSession}">
					 	<a style="color:white;" class="btn btn-primary" href="login">Přihlásit se</a>
					 </c:when>
					 <c:otherwise>
					 	<a style="color:white;" class="btn btn-primary" href="logout">Odhlásit se (přihlášen jako: <% UserSession us = (UserSession)session.getAttribute("userSession"); out.print(us.getName() + " " + us.getSurname()); %>)</a>
					 </c:otherwise>
					</c:choose>
					</li>
					
				</ul>
			</div>
		</div>
	</div>
	<h1 class="text-center">Vyberte si kurt</h1>
		
		<div id='calendar'></div>
		
		<div id="reservationDialog" title="Rezervace" class="ui-dialog-titlebar">
    <form>
        <fieldset>
            <input type="hidden" id="hdnId" />
            <input type="hidden" id="hdnStart" />
            <input type="hidden" id="hdnEnd" />
            <label for="note">Hráč</label>
            <input type="text" name="note" id="note" class="text ui-widget-content ui-corner-all" required placeholder="Jméno příjmení"><br>
            <label for="start">Začátek</label>
            <input type="text" name="start" id="start" class="text ui-widget-content ui-corner-all" onchange="if (!(this.value.match(/^(1[0-9]|2[0-3]|0[8-9]):[0-5][0-9]$/))) this.value = '';" required placeholder="09:00"><br>
            <label for="end">Konec</label>
            <input type="text" name="end" id="end" class="text ui-widget-content ui-corner-all" onchange="if (!((this.value.match(/^([01]?[0-9]|2[0-1]):[0-5][0-9]$/)) || this.value.match(/^22:00$/))) this.value = '';" required placeholder="10:00">
       </fieldset>
        
    </form>
</div>
		
    <div class="modal"><!-- Place at bottom of page --></div>
<script>
$(document).ready(function() {
	$id=$(".courtItem:first").attr('data-courtId');
	
	  var fcSources = {
			  events: {
			        url: 'fillcalendar',
			        type: 'GET',
			        data: {
			        	courtId: $id
			        },
			        
			        error: function() {
			            alert('there was an error while fetching events!');
			        }
			    },
			  
	  };
	  
	    //nastavení paremterů dialogu pro rezervace (update, create)
	    $("#reservationDialog").dialog({
	        autoOpen: false,
	        modal: true,
	        position:   {
	            my: "center",
	            at: "center"
	                    },
	            width: "auto",
	        });
	    
	    
	    //nastavení timepickeru
	    $('#start').timepicker({
	        'minTime': '08:00',
	        'maxTime': '22:00',
	        'timeFormat': 'H:i'
	    });
	    
	    $('#end').timepicker({
	        'minTime': '08:00',
	         'maxTime': '22:00',
	         'timeFormat': 'H:i'
	    });  
	    

	     function displayNoty(text, type) {
	         noty({
	            text: text,
	            layout: 'center',
	            modal: true,
	            closeWith: ['click', 'backdrop'],
	            type: type
	        });
	     }
	  
	
	//alert(courtId);
	//obsluha vyberu kurtu	
	$(".courtItem").on("click",function(){		
		$("h1").text($(this).text());
		//$(".courtItem").removeClass("selected");
		//$(this).addClass("selected");
		//$(this)
		//$('#calendar').fullCalendar( 'removeEventSources' );
		//fcSources = {};
		$id = $(this).attr('data-courtId');
		reloadCalendar();

	});
	
	function reloadCalendar(){
		$('#calendar').fullCalendar( 'removeEventSources' );
		fcSources = {};
		fcSources = {
				  events: {
				        url: 'fillcalendar',
				        type: 'GET',
				        data: {
				        	courtId: $id
				        },
				        
				        error: function() {
				            alert('there was an error while fetching events!');
				        }
				    },
				  
		  };
		$('#calendar').fullCalendar( 'addEventSource', fcSources.events );
	};
	
	//prvni nacteni stranky
	$(".courtItem:first").trigger("click");
	
	/*function getData(start, end) {
      	//vyber kurtu
		$('#calendar').fullCalendar( 'removeEvents' );  
        
      	//zmena data v kalendari
    $.getJSON({link calendarAllJson!}, {'start': start, 'end': end}, function(payload) {
                
                for (var i in payload.data) {
                    if (payload.lengh !== 0){
                        var newEvent = new Object();
                        newEvent.id = payload.data[i].id;
                        newEvent.title = payload.data[i].title;
                        newEvent.start = payload.data[i].start;
                        newEvent.end = payload.data[i].end;
                        newEvent.color = payload.data[i].color;
                        newEvent.allDay = true;
                        newEvent.durationEditable = false;
                        newEvent.startEditable = false; 
                        $('#calendar').fullCalendar( 'renderEvent', newEvent );                        
                    }
                    
                }
            });
            
      };*/
      
	
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
    /*events: {
        url: 'fillcalendar',
    },*/

    eventSources: [ fcSources.events,],
    
    
    eventClick: function(calEvent, jsEvent, view) {
                   //alert(calEvent.id+calEvent.title+calEvent.start+calEvent.end);
            $("#hdnId").val(calEvent.id);
            $("#note").val(calEvent.title);
            $("#start").val(calEvent.start.format('HH:mm'));
            $("#end").val(calEvent.end.format('HH:mm'));
            $("#hdnStart").val(calEvent.start);
            $("#hdnEnd").val(calEvent.end);
                        
            var dateStart = new Date(calEvent.start);
            var dateEnd = new Date(calEvent.end);
            var timeStart = (dateStart.getHours()*60 + dateStart.getMinutes());
            var timeEnd = (dateEnd.getHours()*60 + dateEnd.getMinutes());
         
            $('#reservationDialog').dialog('option', 'title', 'Rezervace - '+calEvent.start.format('DD. MM. YYYY'));
            
            $("#reservationDialog").dialog(
                    'option',
                    'buttons', {                    
                    
                    "save":{
                        text:'Uložit',
                        className:'save',
                        click: function() {
                            if ($('#note').val() === '') {
                                displayNoty('Zadejte jméno hráče', 'warning');
                                return 'return';
                            } else if ($('#start').val() === '') {
                                displayNoty('Zadejte začátek rezervace', 'warning');
                                return;
                            } else if ($('#end').val() === '') {
                                displayNoty('Zadejte konec rezervace', 'warning');
                                return;
                            }
                            
                            var casStart = $("#start").val().split(":");
                            var casEnd = $("#end").val().split(":");
                            var timeStart2 = (parseInt(casStart[0])*60 + parseInt(casStart[1]));
                            var timeEnd2 = (parseInt(casEnd[0])*60 + parseInt(casEnd[1]));
                            var timeStart3 = (timeStart2 - timeStart);                            
                            var timeEnd3 = (timeEnd2 - timeEnd);                            
                            dateStart.setHours(dateStart.getHours() + (timeStart3/60>>0));
                            dateEnd.setHours(dateEnd.getHours() + (timeEnd3/60>>0));
                            dateStart.setMinutes(dateStart.getMinutes() + timeStart3%60);
                            dateEnd.setMinutes(dateEnd.getMinutes() + timeEnd3%60);
                            $("#hdnStart").val(dateStart);
                            $("#hdnEnd").val(dateEnd);
                            
                            $.ajax({
                                url: 'update-reservation',
                                type: 'GET',
                                contentType:'application/json',
                                data: { 
                                    id: $("#hdnId").val(),
                                    start: $("#hdnStart").val(),
                                    end: $("#hdnEnd").val(),
                                    note: $("#note").val(),
                                    //courtId: $('#changeKurtBtn').attr('value'),
                                },
                                //data: newEvent,
                                dataType: "json",
                                success:function(data) { 
                                    //if ()
                                    //alert(data.errorData);
                                   $('#reservationDialog').dialog('close');
                                   //$("#errorText").text(data.errorData);
                                   //$('#reservationDialogError').dialog('open');
                                   
                                   //refreshCalendar(data.errorData, data.errorType, $('#changeKurtBtn').attr('value'));
                                   reloadCalendar();
                                   displayNoty('Rezervace úspěšně modifikována','success');
                                   //alert("yes");
                                    //refreshCalendar($('#calendar').fullCalendar('getView').start, $('#calendar').fullCalendar('getView').end);
                                },
                                error: function (xhr, ajaxOptions, thrownError) {
                                    displayNoty('Vyskytla se chyba: '+xhr.status+' '+thrownError+' '+ajaxOptions,'error');
                                	//alert(xhr.status);
                                    //alert(thrownError);
                                    //alert(ajaxOptions);
                                  },
                                
                            });
                            }
                    },
                    "cancel":{
                        text:'Zrušit',
                        className:'cancel',
                        click: function() {
                            clearDialog();
                            $("#reservationDialog").dialog('close');
                            }
                    }
                
                    
                    
                    
                    
                });
            
            $('#reservationDialog').dialog('open');
            

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
