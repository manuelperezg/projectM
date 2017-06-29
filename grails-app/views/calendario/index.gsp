<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'evento.label', default: 'Evento preventivo')}" />
        <g:set var="entitiesName" value="${message(code: 'evento.label', default: 'Eventos preventivos')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

        <asset:stylesheet src="fullcalendar.print.min.css" media='print'/>

        <asset:stylesheet src="application_calendar.css"/>
		<asset:javascript src="application_calendar.js"/>

        <style>
			#calendar {
				max-width: 98%;
				margin: 0 auto;
			}
        </style>
        <script language="javascript">
			$(document).ready(function() {


				$('#calendar').fullCalendar({
					eventRender: function(event, element) {
						element.append( "<i class='fa fa-trash-o fa-2x' aria-hidden='true' title='¡Eliminar!' style='cursor: pointer; cursor: hand;'></i>" );
						element.find(".fa-trash-o").click(function() {
						   $('#calendar').fullCalendar('removeEvents',event._id);
						});
					},
					header: {
						left: 'prevYear,prev,next,nextYear today',
						center: 'title',
						right: 'month,agendaWeek,listDay'
					},
					views: {
						listDay: { buttonText: 'Día' },
					},
					defaultView: 'month',
					defaultDate: '2017-02-12',
					businessHours: true,
					navLinks: true, // can click day/week names to navigate views
					selectable: true,
					selectHelper: true,

					editable: true,
					eventLimit: true, // allow "more" link when too many events
					events: [
						{
							title: 'All Day Event',
							start: '2017-02-01'
						},
						{
							title: 'Long Event',
							start: '2017-02-07',
							end: '2017-02-10'
						},
						{
							id: 999,
							title: 'Repeating Event',
							start: '2017-02-09T16:00:00'
						},
						{
							id: 999,
							title: 'Repeating Event',
							start: '2017-02-16T16:00:00'
						},
						{
							title: 'Conference',
							start: '2017-02-11',
							end: '2017-02-13'
						},
						{
							title: 'Meeting',
							start: '2017-02-12T10:30:00',
							end: '2017-02-12T12:30:00'
						},
						{
							title: 'Lunch',
							start: '2017-02-12T12:00:00'
						},
						{
							title: 'Meeting',
							start: '2017-02-12T14:30:00'
						},
						{
							title: 'Happy Hour',
							start: '2017-02-12T17:30:00'
						},
						{
							title: 'Dinner',
							start: '2017-02-12T20:00:00'
						},
						{
							title: 'Birthday Party',
							start: '2017-02-13T07:00:00'
						},
						{
							title: 'Click for Google',
							url: 'http://google.com/',
							start: '2017-02-28'
						}
					]
				});

			});

			function jconfirm(pregunta,mensaje,id){
                swal({
                    title: pregunta,
                    text: mensaje,
                    type: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#DA291C',
                    confirmButtonText: 'Si, eliminar!',
                    cancelButtonText: 'No',
                    confirmButtonClass: 'btn btn-success',
                    cancelButtonClass: 'btn btn-danger',
                    buttonsStyling: true
                }).then(function() {
                     location.href = "${request.contextPath}/calendario/remove/"+id;
                });
            }

        </script>
    </head>
    <body>
		<g:render template="/layouts/menu" />
		<div class="breadcrumb-box">
		  <div class="container">
			<ul class="breadcrumb">
			  <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			  <li class="active"><g:message code="default.list.label" args="[entitiesName]" /></li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#list-eventos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
            <ul>
                <li><g:link class="create" action="create" controller="evento"><g:message code="default.new.label" args="['Evento']" /></g:link></li>
            </ul>
        </div>
        <div id="list-eventos" class="" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
			<div id="calendar"></div>
		</div>
    </body>
</html>
